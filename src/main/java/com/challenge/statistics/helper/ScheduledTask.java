package com.challenge.statistics.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.challenge.statistics.domain.StatisticRecord;

@Component
public class ScheduledTask {
  
  private final QueueHandler queueHandler;
  
  private static final int TIME_RECORD_PERIOD = 1000;
  
  private final Object lockObj = new Object();
  private static StatisticRecord currentItem = new StatisticRecord();
  
  @Autowired
  public ScheduledTask(final QueueHandler queueHandler) {
    this.queueHandler = queueHandler;
  }

  @Scheduled(fixedRate = TIME_RECORD_PERIOD)
  private void task() {
    StatisticRecord record;
    synchronized (lockObj) {
      record = new StatisticRecord(currentItem);
      currentItem = new StatisticRecord();
    }
    queueHandler.add(record);
  }

  public void addAmount(double amount) {
      currentItem.addAmount(amount);
  }

}
