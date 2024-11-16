package com.daniela.forexapp.forex_service.exception;

public class CurrencyNotFoundException extends RuntimeException {
  public CurrencyNotFoundException(String message) {
    super(message);
  }
}
