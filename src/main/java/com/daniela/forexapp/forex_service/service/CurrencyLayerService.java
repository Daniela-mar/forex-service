package com.daniela.forexapp.forex_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class CurrencyLayerService {

  @Value("${api.access.key}")
  private  String apiKey;

  @Autowired
  private  ConversionHistoryService conversionHistoryService;

  private static final String API_URL = "http://api.currencylayer.com/";

  @Autowired
  private final RestTemplate restTemplate;

  public CurrencyLayerService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Object getExchangeRates(String baseCurrency) {
    String url = UriComponentsBuilder.fromHttpUrl(API_URL + "live")
      .queryParam("access_key", apiKey)
      .queryParam("source", baseCurrency)
      .build().toUriString();

    return restTemplate.getForObject(url, Object.class);
  }

  public Object convertCurrency(String fromCurrency, String toCurrency, double amount) {
    try {
      String url = UriComponentsBuilder.fromHttpUrl(API_URL + "convert")
        .queryParam("access_key", apiKey)
        .queryParam("from", fromCurrency)
        .queryParam("amount", amount)
        .build().toUriString();

      Map<String, Object> response = restTemplate.getForObject(url, Map.class);

      if (response == null || !response.containsKey("success") || !(boolean) response.get("success")) {
        String errorMessage = response != null && response.containsKey("error")
          ? ((Map<String, Object>) response.get("error")).get("info").toString()
          : "Unknown error occurred while calling the CurrencyLayer API.";
        throw new IllegalStateException(errorMessage);
      }


      double conversionRate = (double) response.get("info.rate");
      double convertedAmount = amount * conversionRate;


      conversionHistoryService.saveConversion(fromCurrency, toCurrency, amount, convertedAmount);


      return Map.of(
        "from", fromCurrency,
        "to", toCurrency,
        "amount", amount,
        "convertedAmount", convertedAmount,
        "rate", conversionRate
      );

    } catch (Exception ex) {
      throw new IllegalArgumentException("error while connecting to CurrencyLayerApi: " + ex.getMessage());
    }
  }
}





