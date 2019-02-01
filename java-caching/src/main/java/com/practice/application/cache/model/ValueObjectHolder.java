package com.practice.application.cache.model;

import java.time.LocalDateTime;

public class ValueObjectHolder<V> {
    private V value;

    private LocalDateTime lastAccessTime;

    public ValueObjectHolder(V value) {
        this.value = value;
        lastAccessTime = LocalDateTime.now();
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime accessTime) {
        this.lastAccessTime = accessTime;
    }

    @Override
    public String toString() {
        return "ValueObjectHolder{" +
                "value=" + value +
                ", lastAccessTime=" + lastAccessTime +
                '}';
    }
}
