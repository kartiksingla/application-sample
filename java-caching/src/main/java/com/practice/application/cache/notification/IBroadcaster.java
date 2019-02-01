package com.practice.application.cache.notification;

public interface IBroadcaster<K> {

	public void registerListener(IListener<K> listener);

	public void removeListener(IListener<K> listener);

	public void fireEvent(K key);
}
