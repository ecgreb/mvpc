package com.example.ecgreb.mvpc.model;

public class InputValidator {

  /**
   * Input validation response type.
   */
  public class Response {
    private final boolean success;
    private final ErrorType errorType;

    private Response(boolean success, ErrorType errorType) {
      this.success = success;
      this.errorType = errorType;
    }

    public boolean isSuccess() {
      return success;
    }

    public ErrorType getErrorType() {
      return errorType;
    }
  }

  /**
   * Input validation error codes.
   */
  public enum ErrorType {
    NULL,
    EMPTY,
    INVALID
  }

  /**
   * Verify email input.
   *
   * @param email String to be validated as potential email.
   * @return
   */
  public Response validateEmail(String email) {
    if (email == null) {
      return new Response(false, ErrorType.NULL);
    }

    if (email.equals("")) {
      return new Response(false, ErrorType.EMPTY);
    }

    if (!email.contains("@")) {
      return new Response(false, ErrorType.INVALID);
    }

    return new Response(true, null);
  }

  /**
   * Verify password input.
   *
   * @param password String to be validated as potential password.
   * @return
   */
  public Response validatePassword(String password) {
    if (password == null) {
      return new Response(false, ErrorType.NULL);
    }

    if (password.equals("")) {
      return new Response(false, ErrorType.EMPTY);
    }

    if (password.length() < 4) {
      return new Response(false, ErrorType.INVALID);
    }

    return new Response(true, null);
  }
}
