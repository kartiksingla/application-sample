package com.practice.application.sizebasedcache;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.practice.application.cache.ICache;
import com.practice.application.cache.api.CacheApiController;
import com.practice.application.cache.model.CacheType;
import com.practice.application.cache.notification.impl.UserListener;

/**
 * Unit test for simple AppLauncher.
 */
public class SizeBasedCacheTest {

	private ICache<String, String> cache;

	private CacheApiController<String, String> cacheApi;

	@Before
	public void setup() {
		cacheApi = new CacheApiController<String, String>(CacheType.SIZE_BASED);
		cache = cacheApi.getCacheStore();
		
		for (int i = 0; i < 8; i++) {
			cache.put("k_" + i, "v_" + i);
		}
		IntStream.range(0,1).forEach(v-> cacheApi.attachListener(new UserListener<String>()));
	}

	@Test
	public void givenCache_WhenAccessedInBetween_ThenElementNotEvictedAfterFirstThreshold() throws InterruptedException {
		String value = cache.get("k_3");
		Assert.assertNotNull(value);
		
		IntStream.range(8, 11).forEach(i -> cache.put("k_" + i, "v_" + i));
		cache.removeCachedValue("k_9");
		Assert.assertNull(cache.get("k_9"));
		IntStream.range(11, 13).forEach(i -> cache.put("k_" + i, "v_" + i));
		Assert.assertNotNull(cache.get("k_3"));
		Assert.assertNull(cache.get("k_0"));
		Assert.assertNull(cache.get("k_1"));
		Assert.assertNotNull(cache.get("k_2"));
	}

}
