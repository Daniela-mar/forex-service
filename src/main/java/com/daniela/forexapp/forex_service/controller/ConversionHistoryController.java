package com.daniela.forexapp.forex_service.controller;

import com.daniela.forexapp.forex_service.model.ConversionHistory;
import com.daniela.forexapp.forex_service.service.ConversionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@Validated
public class ConversionHistoryController {

  @Autowired
  private final ConversionHistoryService conversionHistoryService;

  public ConversionHistoryController(ConversionHistoryService conversionHistoryService) {
    this.conversionHistoryService = conversionHistoryService;
  }

  @GetMapping("/conversionHistory")
  public Page<ConversionHistory> getConversionHistory(
    Pageable pageable,
    @RequestParam(required = false) String fromCurrency,
    @RequestParam(required = false) String toCurrency,
    @RequestParam(required = false) String startDate,
    @RequestParam(required = false) String endDate) {

    LocalDateTime start = null, end = null;

    try {
      if(startDate != null) start = LocalDateTime.parse(startDate);
      if(endDate != null) end = LocalDateTime.parse(endDate);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid date fromat");
    }
    return conversionHistoryService.getFilteredHistory(fromCurrency, toCurrency, start, end, pageable);

  }

}
