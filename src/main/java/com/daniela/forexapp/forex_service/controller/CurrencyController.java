package com.daniela.forexapp.forex_service.controller;


import com.daniela.forexapp.forex_service.model.ConversionHistory;
import com.daniela.forexapp.forex_service.service.ConversionHistoryService;
import com.daniela.forexapp.forex_service.service.CurrencyLayerService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class CurrencyController {



  @Autowired
  private ConversionHistoryService conversionHistoryService;

  @Autowired
  private final CurrencyLayerService currencyLayerService;

  public CurrencyController(CurrencyLayerService currencyLayerService) {
    this.currencyLayerService = currencyLayerService;
  }

  @GetMapping("/conversionHistory")
  public Page<ConversionHistory> getConversionHistory(
    Pageable pageable,
    @RequestParam(required = false) String fromCurrency,
    @RequestParam(required = false) String toCurrency,
    @RequestParam(required = false) LocalDateTime startDate,
    @RequestParam(required = false) LocalDateTime endDate) {

    return conversionHistoryService.getFilteredHistory(fromCurrency, toCurrency, startDate, endDate, pageable);
  }

  @GetMapping("/exchangeRates")
  public Object getExchangeRates(@RequestParam String baseCurrency) {
    return currencyLayerService.getExchangeRates(baseCurrency);
  }

  @GetMapping("/convertCurrency")
  public Object convertCurrency(@RequestParam String fromCurrency,
                                @RequestParam String toCurrency,
                                @RequestParam @Min(value=0, message = "Amount must be greater than or equal to zero") double amount) {

    if(fromCurrency.equalsIgnoreCase(toCurrency)) {
      throw new IllegalArgumentException("Source and target currencise must be different");
    }
    return currencyLayerService.convertCurrency(fromCurrency, toCurrency, amount);
  }
}



