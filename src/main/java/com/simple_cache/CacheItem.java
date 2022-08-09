package com.simple_cache;

import java.util.Date;

public class CacheItem<T> {

  private Date expireDateTime = new Date();
  private Date lastUpdateDateTime = new Date();
  private boolean evicted = false;
  private T cacheContent;
  private CacheFunctionalInterface<T> function;

  public Date getExpireDateTime() {
    return expireDateTime;
  }

  public void setExpireDateTime(Date expireDateTime) {
    this.expireDateTime = expireDateTime;
  }

  public Date getLastUpdateDateTime() {
    return lastUpdateDateTime;
  }

  public void setLastUpdateDateTime(Date lastUpdateDateTime) {
    this.lastUpdateDateTime = lastUpdateDateTime;
  }

  public boolean isEvicted() {
    return evicted;
  }

  public void setEvicted(boolean evicted) {
    this.evicted = evicted;
  }

  public T getCacheContent() {
    return cacheContent;
  }

  public void setCacheContent(T cacheContent) {
    this.cacheContent = cacheContent;
  }

  public CacheFunctionalInterface<T> getFunction() {
    return function;
  }

  public void setFunction(CacheFunctionalInterface<T> function) {
    this.function = function;
  }
}
