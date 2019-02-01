package com.practice.application.timebasedcache;

import com.practice.application.cache.ICache;
import com.practice.application.cache.impl.TimeToLiveCache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple AppLauncher.
 */
public class TimeToLiveTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        assertTrue(true);
    }

    private ICache<String,String> cache;
    @Before
    public void setup()
    {
        cache = new TimeToLiveCache<>(7,10);
        for(int i = 0; i< 5; i++)
        {
            cache.put("k_" + i,"v_"+i);
        }
    }
    @Test
    public void givenCacheMap_WhenAccessedInBetween_ThenTTLGettingReset() throws InterruptedException {
        Thread.sleep(3000);
        String value = cache.get("k_1");
        Assert.assertNotNull(value);
        Thread.sleep(6000);
        value = cache.get("k_1");
        Assert.assertNotNull(value);
        Thread.sleep(10000);
        value = cache.get("k_1");
        Assert.assertNull(value);
    }

}
