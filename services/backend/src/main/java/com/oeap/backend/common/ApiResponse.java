package com.oeap.backend.common;

public class ApiResponse<T> {
  private final boolean success;
  private final String code;
  private final String message;
  private final T data;

  private ApiResponse(boolean success, String code, String message, T data) {
    this.success = success;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(true, ErrorCode.OK.name(), "OK", data);
  }

  public static <T> ApiResponse<T> failure(ErrorCode errorCode, String message) {
    return new ApiResponse<>(false, errorCode.name(), message, null);
  }

  public boolean isSuccess() {
    return success;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}
