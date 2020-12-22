package com.qaiware.poster.errors;

import static java.lang.String.format;

public class BusinessRuleException extends RuntimeException {

  public BusinessRuleException(
      final String message,
      final Object... arguments) {
    super(format(message, arguments));
  }

  public BusinessRuleException(
      final Exception exception,
      final String message,
      final Object... arguments) {
    super(format(message, arguments), exception);
  }

  /**
   * @param condition - if false will throw the BusinessRuleException
   * @param message   - message to use, could be with string formatting
   * @param arguments - to the string formatting from the message
   * @throws BusinessRuleException
   */
  public static void isValid(final boolean condition,
      final String message,
      final Object... arguments) {
    if (!condition) {
      throw new BusinessRuleException(message, arguments);
    }
  }
}
