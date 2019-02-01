package com.practice.application.cache.api;

import com.practice.application.cache.ICache;
import com.practice.application.cache.impl.CacheInitializer;
import com.practice.application.cache.model.CacheType;
import com.practice.application.cache.model.CacheTypeVisitor;
import com.practice.application.cache.notification.IBroadcaster;
import com.practice.application.cache.notification.IListener;
import com.practice.application.cache.notification.impl.NotifierService;

public class CacheApiController<K, V> {

	private IBroadcaster<K> notifier;
	private ICache<K, V> cacheStore;

	public CacheApiController(CacheType cacheType) {
		this.cacheStore = initializeCacheStore(cacheType);
		this.notifier = new NotifierService<>();
		cacheStore.attachNotifierService(notifier);
	}

	public void attachListener(IListener<K> listener) {
		notifier.registerListener(listener);
	}

	private ICache<K, V> initializeCacheStore(CacheType cacheType) {
		CacheTypeVisitor<Integer, ICache<K, V>> cacheInitializer = new CacheInitializer<K, V>();
		return cacheType.visit(cacheInitializer, 10);
	}

	public ICache<K, V> getCacheStore() {
		return cacheStore;
	}

}
