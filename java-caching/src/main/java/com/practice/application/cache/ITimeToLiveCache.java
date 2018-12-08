package com.practice.application.cache;

public interface ITimeToLiveCache<K, V> {
    void put(K key, V value);

    V get(K key);

    void removeCachedValue(K key);

    void cleanUpJob();
}
