package com.practice.application.cache;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TimeToLiveCache<K, V> implements ITimeToLiveCache<K, V> {

    private final long timeToLiveSeconds;

    private final ConcurrentHashMap<K, ValueObjectHolder<V>> cacheMap;

    public TimeToLiveCache(long timeToLiveSeconds, int cacheSize) {
        this.timeToLiveSeconds = timeToLiveSeconds;
        cacheMap = new ConcurrentHashMap<>(cacheSize);
        initializeCleanUp();
    }

    private void initializeCleanUp() {
        Runnable cleanerWorker = () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.err.println(e.getLocalizedMessage());
                }
                this.cleanUpJob();
            }
        };
        ExecutorService executor = Executors.newSingleThreadExecutor((r) -> {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setDaemon(true);
            return thread;
        });
        executor.submit(cleanerWorker);
    }

    @Override
    public void put(K key, V value) {
        cacheMap.put(key, new ValueObjectHolder<V>(value));
    }

    @Override
    public V get(K key) {
        ValueObjectHolder<V> valueObject = cacheMap.get(key);
        if (valueObject != null) {
            valueObject.setLastAccessTime(LocalDateTime.now());
            return valueObject.getValue();
        }
        return null;
    }

    @Override
    public void removeCachedValue(K key) {
        cacheMap.remove(key);
    }

    @Override
    public void cleanUpJob() {
        LocalDateTime time = LocalDateTime.now();
        cacheMap.keySet().forEach(key -> {
                    ValueObjectHolder<V> valueObject = cacheMap.get(key);
                    synchronized (cacheMap) {
                        if (valueObject != null) {
                            LocalDateTime lastAccessTime = valueObject.getLastAccessTime();
                            long diff = ChronoUnit.SECONDS.between(lastAccessTime, time);
                            if (diff > this.timeToLiveSeconds) {
                                this.removeCachedValue(key);
                                Thread.yield();
                            }
                        }
                    }
                }
        );
    }
}
