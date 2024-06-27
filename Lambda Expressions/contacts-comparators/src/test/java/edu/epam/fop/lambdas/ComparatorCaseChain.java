package edu.epam.fop.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class ComparatorCaseChain<E> {

  public enum NullsPosition {
    FIRST, LAST, UNSUPPORTED
  }

  public record CompareLayer<E>(Supplier<E> entitySupplier, NullsPosition nullsPosition,
                                UnaryOperator<E> lesserSetter, UnaryOperator<E> greaterSetter,
                                UnaryOperator<E> nullSetter) {

    private static final String COMPARING_FAILURE_MSG = "{%s} Expect to throw [%s] when comparing [%s] with [%s]";
    private static final String COMPARING_SUCCESS_MSG = "{%s} Expect [%s] to be %s [%s]";

    private Stream<Arguments> arguments(boolean hasUnsupportedNullsInChain,
        Comparator<E> comparator, String comparatorName) {
      Objects.requireNonNull(entitySupplier, "Base entity supplier must not be null");
      Objects.requireNonNull(nullsPosition, "Nulls position must not be null");
      Objects.requireNonNull(lesserSetter, "Setter must not be null");
      Objects.requireNonNull(greaterSetter, "Setter must not be null");
      Objects.requireNonNull(nullSetter, "Setter must not be null");

      var builder = Stream.<Arguments>builder();
      E lesser = lesserSetter.apply(entitySupplier.get());
      E greater = greaterSetter.apply(entitySupplier.get());
      E nullified = nullSetter.apply(entitySupplier.get());

      if (!hasUnsupportedNullsInChain) {
        builder.add(testCase(comparatorName, comparator, lesser, lesser, 0, null));
      }

      if (!lesser.equals(greater)) {
        builder
            .add(testCase(comparatorName, comparator, lesser, greater, -1, null))
            .add(testCase(comparatorName, comparator, greater, lesser, 1, null));
      }

      int cmpNullToObjResult = switch (nullsPosition) {
        case FIRST -> -1;
        case LAST -> 1;
        case UNSUPPORTED -> 0;
      };
      if (nullsPosition == NullsPosition.UNSUPPORTED) {
        builder
            .add(testCase(comparatorName, comparator, nullified, greater, null, NullPointerException.class))
            .add(testCase(comparatorName, comparator, greater, nullified, null, NullPointerException.class));
        if (!hasUnsupportedNullsInChain) {
            builder.add(testCase(comparatorName, comparator, nullified, nullified, null, NullPointerException.class));
        }
      } else {
        builder
            .add(testCase(comparatorName, comparator, nullified, greater, cmpNullToObjResult, null))
            .add(testCase(comparatorName, comparator, greater, nullified, -cmpNullToObjResult, null));
        if (!hasUnsupportedNullsInChain) {
            builder.add(testCase(comparatorName, comparator, nullified, nullified, 0, null));
        }
      }

      return builder.build();
    }

    private Arguments testCase(String comparatorName, Comparator<E> comparator, E leftValue, E rightValue,
        Integer expectedSign, Class<? extends Throwable> expectedErrorType) {
      int sign = expectedSign == null ? 0 : expectedSign;
      String name = expectedErrorType == null
          ? String.format(COMPARING_SUCCESS_MSG, comparatorName, leftValue, relationString(sign), rightValue)
          : String.format(COMPARING_FAILURE_MSG, comparatorName, expectedErrorType, leftValue, rightValue);
      return Arguments.arguments(name, comparator, leftValue, rightValue, sign, expectedErrorType);
    }

    private String relationString(int sign) {
      return switch ((int) Math.signum(sign)) {
        case -1 -> "less than";
        case 0 -> "equal to";
        case 1 -> "greater than";
        default -> throw new IllegalStateException("Unexpected sign value");
      };
    }
  }

  private final Supplier<E> entitySupplier;
  private final NullsPosition nullsPosition;
  private final List<Function<Supplier<E>, CompareLayer<E>>> layerCreators;

  public ComparatorCaseChain(Supplier<E> entitySupplier, NullsPosition nullsPosition) {
    this.entitySupplier = entitySupplier;
    this.nullsPosition = nullsPosition;
    this.layerCreators = new ArrayList<>();
  }

  public <V> ComparatorCaseChain<E> add(NullsPosition nullsPosition,
      BiConsumer<E, V> setter, V lesserValue, V greaterValue) {
    this.layerCreators.add(sup -> new CompareLayer<>(sup, nullsPosition,
              toOperator(e -> setter.accept(e, lesserValue)),
              toOperator(e -> setter.accept(e, greaterValue)),
              toOperator(e -> setter.accept(e, null))
          ));
    return this;
  }

  private static <E> UnaryOperator<E> toOperator(Consumer<E> consumer) {
    return e -> {
      consumer.accept(e);
      return e;
    };
  }

  public ComparatorCaseChain<E> add(ComparatorCaseChain<E> chain) {
    return add(chain, Function.identity(), (_1, _2) -> {}, () -> null);
  }

  public <D> ComparatorCaseChain<E> add(ComparatorCaseChain<D> chain, Function<E, D> propertyGetter,
      BiConsumer<E, D> propertySetter, Supplier<D> propertySupplier) {
    chain.layerCreators.stream()
        .map(f -> f.apply(() -> null))
        .forEachOrdered(layer -> this.layerCreators.add(sup -> new CompareLayer<>(
            sup,
            layer.nullsPosition,
            nested(propertyGetter, propertySetter, propertySupplier, layer.lesserSetter),
            nested(propertyGetter, propertySetter, propertySupplier, layer.greaterSetter),
            nested(propertyGetter, propertySetter, propertySupplier, layer.nullSetter)
        )));
    return this;
  }

  private static <E, D> UnaryOperator<E> nested(Function<E, D> propertyGetter, BiConsumer<E, D> propertySetter,
      Supplier<D> propertySupplier, UnaryOperator<D> propertyCaseSetter) {
    return e -> {
      if (propertyGetter.apply(e) == null) {
        propertySetter.accept(e, propertySupplier.get());
      }
      propertyCaseSetter.compose(propertyGetter).apply(e);
      return e;
    };
  }

  public Stream<Arguments> build(Comparator<E> comparator, String comparatorName) {
    var builder = Stream.<Arguments>builder();
    var hasUnsupportedNullsLayer = hasUnsupportedNullsLayer();
    new CompareLayer<>(entitySupplier, nullsPosition, e -> e, e -> e, e -> null)
        .arguments(hasUnsupportedNullsLayer, comparator, comparatorName)
        .forEachOrdered(builder::add);
    Supplier<E> layerSupplier = entitySupplier;
    for (Function<Supplier<E>, CompareLayer<E>> layerCreator : layerCreators) {
      var layer = layerCreator.apply(layerSupplier);
      layer.arguments(hasUnsupportedNullsLayer, comparator, comparatorName).forEachOrdered(builder::add);
      final Supplier<E> tmpSupplier = layerSupplier;
      layerSupplier = () -> layer.lesserSetter.apply(tmpSupplier.get());
    }
    final Set<String> uniqueNames = new HashSet<>();
    return builder.build()
        .filter(args -> uniqueNames.add((String) args.get()[0]));
  }

  private boolean hasUnsupportedNullsLayer() {
    return layerCreators.stream()
        .map(f -> f.apply(() -> null))
        .map(CompareLayer::nullsPosition)
        .anyMatch(NullsPosition.UNSUPPORTED::equals);
  }
}
