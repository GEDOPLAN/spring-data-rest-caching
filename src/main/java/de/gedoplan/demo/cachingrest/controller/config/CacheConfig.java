package de.gedoplan.demo.cachingrest.controller.config;

import org.cache2k.Cache;
import org.cache2k.CacheEntry;
import org.cache2k.event.CacheEntryCreatedListener;
import org.cache2k.event.CacheEntryRemovedListener;
import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class CacheConfig {


    @Bean
    public CacheManager cacheManager() {
        SpringCache2kCacheManager springCache2kCacheManager = new SpringCache2kCacheManager();
        springCache2kCacheManager.setAllowUnknownCache(false);


        springCache2kCacheManager.addCaches(
                c -> c.name("avgAge"),
                c -> c.name("users").entryCapacity(2).addAsyncListener(new CacheLogListener()));

        return springCache2kCacheManager;
    }

    private static class CacheLogListener implements CacheEntryCreatedListener, CacheEntryRemovedListener {

        @Override
        public void onEntryCreated(Cache cache, CacheEntry cacheEntry) {
            System.out.println(cache.getName() + " created " + " :: " + cacheEntry.getKey());
        }

        @Override
        public void onEntryRemoved(Cache cache, CacheEntry cacheEntry) {
            System.out.println(cache.getName() + " removed " + " :: " + cacheEntry.getKey());
        }
    }
}
