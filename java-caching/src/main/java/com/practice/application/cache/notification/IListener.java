package com.practice.application.cache.notification;

public interface IListener<K> {

	public void onElementEvicted(K key);
	
}
