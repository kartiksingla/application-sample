package com.practice.application.cache.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.practice.application.cache.ICache;
import com.practice.application.cache.notification.IBroadcaster;

public final class SizedBasedCache<K, V> implements ICache<K, V> {

	private ConcurrentHashMap<K, V> cacheMap;

	private ConcurrentLinkedQueue<K> recentReferences;

	private int cacheMaxSize;

	private ReadWriteLock lock;

	private Lock readLock;

	private Lock writeLock;

	private IBroadcaster<K> notifier;

	public SizedBasedCache(final int cacheSize) {
		this.cacheMaxSize = cacheSize;
		this.cacheMap = new ConcurrentHashMap<>();
		this.recentReferences = new ConcurrentLinkedQueue<>();
		initializeLocks();
	}

	private void initializeLocks() {
		this.lock = new ReentrantReadWriteLock();
		this.readLock = lock.readLock();
		this.writeLock = lock.writeLock();
	}

	@Override
	public void put(K key, V value) {
		writeLock.lock();
		try {
			if (cacheMap.contains(key))
				recentReferences.remove(key);

			while (recentReferences.size() >= cacheMaxSize) {
				K evictedElement = recentReferences.poll();
				cacheMap.remove(evictedElement);
				notifier.fireEvent(evictedElement);
			}
			cacheMap.put(key, value);
			recentReferences.add(key);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public V get(K key) {
		readLock.lock();
		V value = null;
		try {
			value = cacheMap.get(key);
			if (null != value) {
				recentReferences.remove(key);
				recentReferences.add(key);
			}
		} finally {
			readLock.unlock();
		}
		return value;
	}

	@Override
	public void removeCachedValue(K key) {
		readLock.lock();
		try {
			cacheMap.remove(key);
			recentReferences.remove(key);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public void attachNotifierService(IBroadcaster<K> notiferService) {
		this.notifier = notiferService;
	}

}