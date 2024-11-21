package com.daniela.forexapp.forex_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ConversionHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String fromCurrency;
  private String toCurrency;
  private double amount;
  private double convertedAmount;
  private LocalDateTime timestamp;

  public ConversionHistory() {}

  public ConversionHistory(String fromCurrency, String toCurrency, double amount, double convertedAmount, LocalDateTime timestamp) {
    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
    this.amount = amount;
    this.convertedAmount = convertedAmount;
    this.timestamp = timestamp;
  }


}
