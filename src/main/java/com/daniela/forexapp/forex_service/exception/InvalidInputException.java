package com.daniela.forexapp.forex_service.exception;

public class InvalidInputException extends RuntimeException {
  public InvalidInputException(String message) {
    super(message);
  }
}