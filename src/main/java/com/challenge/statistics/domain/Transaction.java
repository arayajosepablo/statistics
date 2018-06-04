package com.challenge.statistics.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class Transaction {
  
  private final Double amount;
  private final Long timestamp;

}
