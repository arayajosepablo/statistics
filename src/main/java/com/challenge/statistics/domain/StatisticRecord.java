package com.challenge.statistics.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class StatisticRecord {
  
  private volatile double sum;
  private volatile double max;
  private volatile double min;
  private volatile long count;
  private final Object locker = new Object();
  
  public StatisticRecord(final double sum, 
      final double max, 
      final double min, 
      final long count) {
    this.sum = sum;
    this.max = max;
    this.min = min;
    this.count = count;
  }
  
  public StatisticRecord(StatisticRecord other) {
    this(other.getSum(), other.getMax(), other.getMin(), other.getCount());
  }
  
  public void incCount() {
    synchronized (locker) {
        count++;
    }
  }
  
  public void addAmount(double amount) {
    synchronized (locker) {
      sum = sum + amount;
      if (max < amount) {
        max = amount;
      }
      if (min > amount) {
        min = amount;
      }
      count++;
    }
  }

}
