package com.practice.application.cache.notification.impl;

import java.util.ArrayList;
import java.util.List;

import com.practice.application.cache.notification.IBroadcaster;
import com.practice.application.cache.notification.IListener;

public class NotifierService<K> implements IBroadcaster<K> {

	private List<IListener<K>> listeners;

	private Object lock = new Object();

	public NotifierService() {
		listeners = new ArrayList<>();
	}

	@Override
	public void registerListener(IListener<K> listener) {
		if (null == listener)
			throw new NullPointerException("Empty observer is not allowed");
		listeners.add(listener);
	}

	@Override
	public void removeListener(IListener<K> listener) {
		synchronized (lock) {
			listeners.remove(listener);
		}
	}

	@Override
	public void fireEvent(final K key) {
		synchronized (lock) {
			listeners.forEach(listener -> listener.onElementEvicted(key));
		}
	}
}
