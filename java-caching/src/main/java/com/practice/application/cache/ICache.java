package com.practice.application.cache;

import com.practice.application.cache.notification.IBroadcaster;

public interface ICache<K, V> {
	void put(K key, V value);

	V get(K key);

	void removeCachedValue(K key);

	void attachNotifierService(IBroadcaster<K> notiferService);

}
