package com.practice.application.timebasedcache;

import com.practice.application.cache.ICache;
import com.practice.application.cache.api.CacheApiController;
import com.practice.application.cache.impl.TimeToLiveCache;
import com.practice.application.cache.model.CacheType;
import com.practice.application.cache.notification.impl.UserListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

/**
 * Unit test for simple AppLauncher.
 */
public class TimeToLiveTest {

	private ICache<String, String> cache;

	private CacheApiController<String, String> cacheApi;

	@Before
	public void setup() throws InterruptedException {
		cacheApi = new CacheApiController<String, String>(CacheType.TIME_TO_LIVE);
		cache = cacheApi.getCacheStore();

		for (int i = 0; i < 8; i++) {
			Thread.sleep(100);
			cache.put("k_" + i, "v_" + i);
		}
		IntStream.range(0, 1).forEach(v -> cacheApi.attachListener(new UserListener<String>()));
	}

	@Test
	public void givenCacheMap_WhenNotAccess_ThenEvicted() throws InterruptedException {
		Thread.sleep(11000);
		String value = cache.get("k_0");
		Assert.assertNull(value);
		
		Thread.sleep(12000);
	}
	
	@Test
	public void givenCacheMap_WhenAccessedInBetween_ThenTTLGettingReset() throws InterruptedException {
		Thread.sleep(3000);
		String value = cache.get("k_1");
		Assert.assertNotNull(value);
		Thread.sleep(6000);
		value = cache.get("k_1");
		Assert.assertNotNull(value);
		Thread.sleep(2000);
		value = cache.get("k_0");
		Assert.assertNull(value);
		
		Thread.sleep(2000);
	}

}
