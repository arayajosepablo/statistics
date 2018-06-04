package com.challenge.statistics.service;

import com.challenge.statistics.domain.Statistic;
import com.challenge.statistics.domain.Transaction;

public interface StatisticService {
  
  void processTransaction(Transaction transaction);
  
  Statistic get();

}
