package com.practice.application.cache.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.practice.application.cache.ICache;
import com.practice.application.cache.model.ValueObjectHolder;
import com.practice.application.cache.notification.IBroadcaster;

public class TimeToLiveCache<K, V> implements ICache<K, V> {

	private final long timeToLiveSeconds;

	private final ConcurrentHashMap<K, ValueObjectHolder<V>> cacheMap;

	private IBroadcaster<K> notifier;

	public TimeToLiveCache(long timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
		cacheMap = new ConcurrentHashMap<>();
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

	public void cleanUpJob() {
		LocalDateTime time = LocalDateTime.now();
		cacheMap.keySet().forEach(key -> {
			ValueObjectHolder<V> valueObject = cacheMap.get(key);
			synchronized (cacheMap) {
				if (valueObject != null) {
					LocalDateTime lastAccessTime = valueObject.getLastAccessTime();
					long diff = ChronoUnit.SECONDS.between(lastAccessTime, time);
					if (diff >= this.timeToLiveSeconds) {
						this.removeCachedValue(key);
						Thread.yield();
					}
				}
			}
		});
		
	}

	@Override
	public void removeCachedValue(K key) {
		cacheMap.remove(key);
		notifier.fireEvent(key);
	}

	@Override
	public void attachNotifierService(IBroadcaster<K> notiferService) {
		this.notifier = notiferService;
	}
}
