package edu.epam.fop.lambdas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test TriFunction declaration correctness")
class TriFunctionTest {

  @DisplayName("Tests that TriFunction exists")
  @Test
  void shouldExist() {
    findClass();
  }

  @DisplayName("Tests that TriFunction is an interface")
  @Test
  void shouldBeAnInterface() {
    Class<?> fiClass = findClass();
    assertTrue(fiClass.isInterface(), "TriFunction must be an interface");
  }

  @DisplayName("Tests that TriFunction is generic interface")
  @Test
  void shouldBeGeneralized() {
    Class<?> fiClass = findClass();
    TypeVariable<? extends Class<?>>[] typeVariables =
        assertDoesNotThrow(fiClass::getTypeParameters,
            "Failed to get type variables of TriFunction");
    assertEquals(4, typeVariables.length,
        "TriFunction must have all parameters and return type generalized");
    for (TypeVariable<? extends Class<?>> typeVariable : typeVariables) {
      Type[] bounds = typeVariable.getBounds();
      String tvName = typeVariable.getName();
      String message =
          "Type variable" + tvName + " of TriFunction must not have any specific boundary";
      assertEquals(1, bounds.length, message);
      assertEquals(Object.class.getCanonicalName(), bounds[0].getTypeName(), message);
    }
  }

  @DisplayName("Tests that TriFunction has apply(...) method (with proper signature)")
  @Test
  void shouldHaveApplyMethod() {
    Class<?> fiClass = findClass();
    findMethod(fiClass);
  }

  @DisplayName("Tests that TriFunction.apply method is properly generalized")
  @Test
  void shouldHaveGeneralizedMethod() {
    Class<?> fiClass = findClass();
    Method method = findMethod(fiClass);
    Class<?>[] parameterTypes = method.getParameterTypes();
    Type[] genericParameterTypes = method.getGenericParameterTypes();
    for (int i = 0; i < parameterTypes.length; i++) {
      String message = i + " parameter of TriFunction.apply must be generalized "
          + "without any specific boundary";
      assertEquals(Object.class, parameterTypes[i], message);
      assertNotEquals(parameterTypes[i].getCanonicalName(), genericParameterTypes[i].getTypeName(),
          message);
    }
    Type genericReturnType = method.getGenericReturnType();
    Class<?> returnType = method.getReturnType();
    String message = "return type of TriFunction.apply must be generalized "
        + "without any specific boundary";
    assertEquals(Object.class, returnType, message);
    assertNotEquals(returnType.getCanonicalName(), genericReturnType.getTypeName(), message);
  }

  @DisplayName("Tests that TriFunction.apply has all different generic types")
  @Test
  void shouldMethodHaveAllDifferentGenericTypes() {
    Class<?> fiClass = findClass();
    Method method = findMethod(fiClass);
    List<Type> types = new ArrayList<>() {{
      add(method.getGenericReturnType());
      addAll(List.of(method.getGenericParameterTypes()));
    }};
    assertThatAllGenericTypesAreDifferent(types);
  }

  @DisplayName("Tests that TriFunction is annotated with @FunctionInterface")
  @Test
  void shouldBeAnnotatedWithFunctionInterface() {
    Class<?> fiClass = findClass();
    FunctionalInterface annotation = fiClass.getDeclaredAnnotation(FunctionalInterface.class);
    assertNotNull(annotation, "TriFunction must be annotated with @FunctionInterface");
  }

  private static Class<?> findClass() {
    return assertDoesNotThrow(() -> Class.forName("edu.epam.fop.lambdas.TriFunction"),
        "TriFunction does not exist");
  }

  private static Method findMethod(Class<?> fiClass) {
    Method method = assertDoesNotThrow(() -> fiClass.getDeclaredMethod("apply",
            Object.class, Object.class, Object.class),
        "TriFunction.apply(...) method was not found");
    method.setAccessible(true);
    return method;
  }

  private static void assertThatAllGenericTypesAreDifferent(Collection<Type> types) {
    if (types.size() != Set.copyOf(types).size()) {
      fail("TriFunction.apply has reused type parameters");
    }
  }
}
