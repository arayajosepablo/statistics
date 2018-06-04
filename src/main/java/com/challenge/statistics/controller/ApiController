package com.challenge.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.statistics.domain.Transaction;
import com.challenge.statistics.service.StatisticService;

@RestController
public class ApiController {
  
  private final StatisticService service;
  
  @Autowired
  public ApiController(final StatisticService service) {
    this.service = service;
  }
  
  @RequestMapping(value = "/statistics", method = {RequestMethod.GET})
  public ResponseEntity<Object> getStatistics() {
      return new ResponseEntity<>(this.service.get(), HttpStatus.OK);
  }
  
  @RequestMapping(value = "/transactions", method = {RequestMethod.POST})
  public ResponseEntity<?> transactions(@RequestBody Transaction transactionRequest) {

      if ((System.currentTimeMillis() - transactionRequest.getTimestamp()) < 60000) {
          this.service.processTransaction(transactionRequest);
          return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
      }
      return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }

}
