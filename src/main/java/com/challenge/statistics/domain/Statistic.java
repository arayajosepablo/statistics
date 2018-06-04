package com.challenge.statistics.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class Statistic {
  
  private double sum;
  private double avg;
  private double max;
  private double min;
  private long count;
  
  public void addRecord(StatisticRecord record) {
    this.count = this.count + record.getCount();
    this.sum = this.sum + record.getSum();
    if (this.max < record.getMax()) {
        this.max = record.getMax();
    }
    if (this.min > record.getMin()) {
        this.min = record.getMin();
    }
    if (this.sum == 0) {
        this.avg = 0;
    } else {
        this.avg = this.sum / this.count;
    }
  }

}
