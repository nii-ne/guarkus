package com.niine.quarkus.service;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import javax.enterprise.context.ApplicationScoped;

import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class GreetingService {

    @CacheResult(cacheName = "hello-cache")
    public String greeting(String name) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Hello " + name;
    }

    @CacheInvalidate(cacheName = "hello-cache")
    public String delete(@CacheKey String name) {
        return "Delete " + name;
    }

    @CacheInvalidateAll(cacheName = "hello-cache")
    public String deleteAll() {
        return "Delete All";
    }
}
