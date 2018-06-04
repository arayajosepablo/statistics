package com.challenge.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.statistics.domain.Statistic;
import com.challenge.statistics.domain.StatisticRecord;
import com.challenge.statistics.domain.Transaction;
import com.challenge.statistics.helper.QueueHandler;
import com.challenge.statistics.helper.ScheduledTask;

@Service
public class StatisticServiceImpl implements StatisticService {  

  private final ScheduledTask scheduler;
  private final QueueHandler queueHandler;
  
  @Autowired
  public StatisticServiceImpl(final ScheduledTask scheduler,
      final QueueHandler queueHandler) {
    this.scheduler = scheduler;
    this.queueHandler = queueHandler;
  }

  public void processTransaction(Transaction transaction) {
    this.scheduler.addAmount(transaction.getAmount());
  }

  public Statistic get() {
    Statistic statistic = new Statistic();
    statistic.setMax(Double.MIN_VALUE);
    statistic.setMin(Double.MAX_VALUE);
    
    Object[] array = queueHandler.get();
    for (int i = 0; i < array.length; i++) {
      statistic.addRecord((StatisticRecord) array[i]);
    }
    
    statistic.setMax(statistic.getMax() == Double.MIN_VALUE ? 0 : statistic.getMax());
    statistic.setMin(statistic.getMin() == Double.MAX_VALUE ? 0 : statistic.getMin());
    
    return statistic;
  }

}
