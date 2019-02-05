package com.practice.application.cache.impl;

import com.practice.application.cache.ICache;
import com.practice.application.cache.model.CacheTypeVisitor;

public class CacheInitializer<K, V> implements CacheTypeVisitor<Integer, ICache<K, V>> {

	@Override
	public ICache<K, V> visitTimeToLive(Integer timeInSec) {
		return new TimeToLiveCache<K, V>(timeInSec);
	}

	@Override
	public ICache<K, V> visitSizeBased(Integer cacheSize) {
		return new SizedBasedCache<K, V>(cacheSize);
	}

}
