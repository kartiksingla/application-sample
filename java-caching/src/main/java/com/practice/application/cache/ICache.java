package com.practice.application.cache;

public interface ICache<K, V> {
    void put(K key, V value);

    V get(K key);

    void removeCachedValue(K key);

}
