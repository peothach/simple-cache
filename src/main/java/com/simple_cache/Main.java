package com.simple_cache;

import java.util.Calendar;

public class Main {
  public static void main(String[] args) throws CacheItemNotFound {
    SimpleCache cache = SimpleCache.getInstance();
    cache.cacheItem("some.key", () -> {
      // implementation for cache value update
      // e.g. data retrieval from DB or thirdparty API
      return "value";
    }, Calendar.MINUTE, 5);

    String cacheValueCopy = cache.getCacheItem("some.key");
    System.out.println("Before evict with some.key: " + cacheValueCopy);
    cache.evict("some.key");
    System.out.println("After evict with some.key: " + cache.getCacheItem("some.key"));
  }
}
