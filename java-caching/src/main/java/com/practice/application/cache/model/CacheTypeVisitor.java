package com.practice.application.cache.model;

public interface CacheTypeVisitor<IN, OUT> {

	OUT visitTimeToLive(IN in);

	OUT visitSizeBased(IN in);

}
