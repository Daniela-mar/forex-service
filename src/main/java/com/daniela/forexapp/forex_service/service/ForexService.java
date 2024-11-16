package com.daniela.forexapp.forex_service.service;

import com.daniela.forexapp.forex_service.exception.CurrencyNotFoundException;
import com.daniela.forexapp.forex_service.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ForexService {

  @Autowired
  private TransactionHistoryRepository transactionHistoryRepository;

  @Autowired
  private FixerIOService fixerIOService;

  public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {
    if(!isValidCurrency(sourceCurrency) || !isValidCurrency(targetCurrency)) {
      throw new CurrencyNotFoundException("Invalid currency: " + sourceCurrency + " or " + targetCurrency);
    }
    return fixerIOService.getExchangeRate(sourceCurrency, targetCurrency);

  }


}
