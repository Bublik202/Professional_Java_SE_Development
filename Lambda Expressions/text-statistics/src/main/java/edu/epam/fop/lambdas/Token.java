package edu.epam.fop.lambdas;

import java.util.Objects;

public record Token(String name, Type type) {

  public Token(String name) {
    this(name, Type.WORD);
  }

  public static Token token(String name, Type type) {
    return new Token(name, type);
  }

  public static Token token(String name) {
    return new Token(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!obj.getClass().equals(this.getClass())) {
      return false;
    }
    Token that = (Token) obj;
    return Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }

  enum Type {
    WORD,
    CUSTOM,
    NUMBER,
    CODE
  }
}
