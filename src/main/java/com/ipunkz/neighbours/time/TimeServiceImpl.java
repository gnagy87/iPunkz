package com.ipunkz.neighbours.time;

import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {

  @Override
  public long expiredOrNOt(Long finishedAt) {
    return java.time.Instant.now().getEpochSecond() - finishedAt;
  }
}
