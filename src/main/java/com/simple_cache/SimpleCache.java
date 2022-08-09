package com.simple_cache;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SimpleCache {

  private HashMap<String, CacheItem<?>> cache = new HashMap<>();
  private static SimpleCache simpleCache;
  private final static Integer DEFAULT_EXPIRE_DURATION_MIN = 30;

  private SimpleCache() {}

  public static SimpleCache getInstance() {
    if (simpleCache == null) {
      return new SimpleCache();
    }
    return simpleCache;
  }

  public <T> T getCacheItem(String key) throws  CacheItemNotFound {
    CacheItem<T> cacheItem = (CacheItem<T>) cache.get(key);

    if (cacheItem == null) {
      throw new CacheItemNotFound();
    }

    if (cacheItem.isEvicted() || cacheItem.getExpireDateTime().compareTo(new Date()) < 0) {
      cache.remove(key);
      throw new CacheItemNotFound();
    }

    return cacheItem.getCacheContent();
  }

  public <T> T cacheItem(String key, CacheFunctionalInterface<T> function, Integer field, Integer amount) {
    Calendar expireDateTimeCalendar = Calendar.getInstance();
    expireDateTimeCalendar.add(field, amount);
    return this.<T> cacheItem(key, function, expireDateTimeCalendar.getTime());
  }

  private <T> T cacheItem(String key, CacheFunctionalInterface<T> function, Date expireDateTime) {
    CacheItem<T> cacheItem = (CacheItem<T>) cache.get(key);
    if (cacheItem == null) {
      cacheItem = new CacheItem<>();
      cacheItem.setFunction(function);
      cacheItem.setExpireDateTime(expireDateTime);
      cacheItem.setLastUpdateDateTime(new Date());
      cacheItem.setCacheContent(function.execute());
      cache.put(key, cacheItem);
    } else {
      if (cacheItem.isEvicted() || cacheItem.getExpireDateTime().compareTo(new Date()) < 0) {
        cacheItem.setExpireDateTime(expireDateTime);
        cacheItem.setLastUpdateDateTime(new Date());
        cacheItem.setCacheContent(function.execute());
      }
    }

    return cacheItem.getCacheContent();
  }

  public void evict(String key) {
    if (cache.get(key) != null) {
      cache.get(key).setEvicted(true);
    }
  }
}
