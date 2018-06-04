package com.challenge.statistics.helper;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;

import com.challenge.statistics.domain.StatisticRecord;

@Component
public class QueueHandler {
  
  private static ConcurrentLinkedQueue<StatisticRecord> queue = new ConcurrentLinkedQueue<>();
  
  private static final int QUEUE_SIZE = 60;

  public void add(StatisticRecord record) {
    synchronized (queue) {
      if (queue.size() > QUEUE_SIZE) {
        queue.poll();
      }
      queue.add(record);
    }
  }

  public Object[] get() {
    synchronized (queue) {
      return queue.toArray();
    }
  }

}
