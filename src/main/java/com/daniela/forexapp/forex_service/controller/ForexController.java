package com.daniela.forexapp.forex_service.controller;

import com.daniela.forexapp.forex_service.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/forex")
public class ForexController {

  @Autowired
  private ForexService forexService;

  @GetMapping("/rate")
  public ResponseEntity<String> getExchangeRate(
    @RequestParam String sourceCurrency,
    @RequestParam String targetCurrency) {
    BigDecimal exchangeRate = forexService.getExchangeRate(sourceCurrency,targetCurrency);
    String response = String.format("The exchange rate form %s to %s is %.2f", sourceCurrency, targetCurrency, exchangeRate);
    return ResponseEntity.ok(response);
  }


}
