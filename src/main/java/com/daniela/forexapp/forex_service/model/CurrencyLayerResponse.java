package com.daniela.forexapp.forex_service.model;

import lombok.*;

import java.util.Map;


@Setter
@Getter

public class CurrencyLayerResponse {

  private boolean success;
  private long timestamp;
  private String source;
  private Map<String, Double> quotes;


}
