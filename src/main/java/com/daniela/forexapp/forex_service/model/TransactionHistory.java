package com.daniela.forexapp.forex_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;


@Entity
public class TransactionHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String sourceCurrency;
  private String targetCurrency;
  private BigDecimal sourceAmount;
  private BigDecimal targetAmount;

  public TransactionHistory(Long id, String sourceCurrency, String targetCurrency, BigDecimal sourceAmount, BigDecimal targetAmount) {
    this.id = id;
    this.sourceCurrency = sourceCurrency;
    this.targetCurrency = targetCurrency;
    this.sourceAmount = sourceAmount;
    this.targetAmount = targetAmount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSourceCurrency() {
    return sourceCurrency;
  }

  public void setSourceCurrency(String sourceCurrency) {
    this.sourceCurrency = sourceCurrency;
  }

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  public BigDecimal getSourceAmount() {
    return sourceAmount;
  }

  public void setSourceAmount(BigDecimal sourceAmount) {
    this.sourceAmount = sourceAmount;
  }

  public BigDecimal getTargetAmount() {
    return targetAmount;
  }

  public void setTargetAmount(BigDecimal targetAmount) {
    this.targetAmount = targetAmount;
  }
}
