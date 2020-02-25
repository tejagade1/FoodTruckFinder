package com.teja.foodtruckfinder.util;

/**
 * Utility class for methods that can be used to perform various kinds of verification.
 *
 */
public final class Verifier {
  private Verifier() {}

  /**
   * Verifies the incoming {@link Object object} is <b>not</b> {@code null}.
   *
   * @param <T> The <i>type</i> of {@code Object} being verified.
   * @param object The <i>object</i> to verify.
   * @param name The <i>name</i> of the <i>object</i> (ex. beginEffDtTm) being verified.
   * @return The incoming <i>object</i> itself, never {@code null}.
   * @throws IllegalArgumentException If the incoming <i>object</i> is {@code null}.
   */
  public static <T> T verifyNotNull(T object, String name) {
    if (object == null) {
      throw new IllegalArgumentException(name + " must not be null");
    }

    return object;
  }

  /**
   * Verifies the incoming {@link String string} is <b>not</b> {@code null} or empty.
   *
   * @param string The <i>string</i> to verify.
   * @param name The name of the <i>string</i> being verified.
   * @return The string itself, never null.
   * @throws IllegalArgumentException If the incoming <i>string</i> is null or empty.
   */
  public static String verifyNotNullOrEmpty(String string, String name) {
    if (verifyNotNull(string, name).isEmpty()) {
      throw new IllegalArgumentException(name + " must not be empty");
    }

    return string;
  }

  /**
   * Verifies the incoming {@link String string} is <b>not</b> {@code null} or empty.
   *
   * @param string The <i>string</i> to verify.
   * @param name The name of the <i>string</i> being verified.
   * @param message A message to be included in the exception.
   * @return The string itself, never null.
   * @throws IllegalArgumentException If the incoming <i>string</i> is null or empty.
   */
  public static String verifyNotNullOrEmpty(String string, String name, String message) {
    if (string == null) {
      throw new IllegalArgumentException(
          name + (message == null ? " must not be null" : (" - " + message)));
    } else if (string.isEmpty()) {
      throw new IllegalArgumentException(
          name + (message == null ? " must not be empty" : (" - " + message)));
    }

    return string;
  }
}
