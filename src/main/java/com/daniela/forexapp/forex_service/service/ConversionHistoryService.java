package com.daniela.forexapp.forex_service.service;

import com.daniela.forexapp.forex_service.model.ConversionHistory;
import com.daniela.forexapp.forex_service.repository.ConversionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversionHistoryService {

  @Autowired
  private  final ConversionHistoryRepository conversionHistoryRepository;

  public ConversionHistoryService(ConversionHistoryRepository conversionHistoryRepository) {
    this.conversionHistoryRepository = conversionHistoryRepository;
  }

  public void saveConversion(String fromCurrency, String toCurrency, double amount, double convertedAmount) {
    ConversionHistory conversionHistory = new ConversionHistory();
    conversionHistory.setFromCurrency(fromCurrency);
    conversionHistory.setToCurrency(toCurrency);
    conversionHistory.setAmount(amount);
    conversionHistory.setConvertedAmount(convertedAmount);
    conversionHistory.setTimestamp(LocalDateTime.now());
    conversionHistoryRepository.save(conversionHistory);

  }

  public Page<ConversionHistory> getFilteredHistory(
    String fromCurrency, String toCurrency, LocalDateTime startDate,
    LocalDateTime endDate, Pageable pageable) {
    return conversionHistoryRepository.findFilteredHistory(fromCurrency, toCurrency, startDate, endDate, pageable);
  }



}
