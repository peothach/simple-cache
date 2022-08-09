package com.simple_cache;

@FunctionalInterface
public interface CacheFunctionalInterface<T> {
  T execute();
}
