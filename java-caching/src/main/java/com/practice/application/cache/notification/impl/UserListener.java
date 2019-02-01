package com.practice.application.cache.notification.impl;

import com.practice.application.cache.notification.IListener;

public class UserListener<K> implements IListener<K> {

	@Override
	public void onElementEvicted(K key) {
		System.out.println("Element evicted : " + key);
	}

}
