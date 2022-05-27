package com.niine.quarkus.service;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;

@Singleton
public class RedisService {

    private final RedisClient redisClient;
    private final ReactiveRedisClient reactiveRedisClient;

    public RedisService(RedisClient client, ReactiveRedisClient reactiveRedisClient) {
        this.redisClient = client;
        this.reactiveRedisClient = reactiveRedisClient;
    }

    public String get(String key) {
        return redisClient.get(key).toString();
    }

    public void set(String key, Integer value) {
        redisClient.set(Arrays.asList(key, value.toString()));
    }

    public io.vertx.redis.client.Response increment(String key, Integer incrementBy) {
        return redisClient.incrby(key, incrementBy.toString());
    }

    public Uni<List<String>> keys() {
        return reactiveRedisClient
                .keys("*")
                .map(response -> {
                    List<String> result = new ArrayList<>();
                    for (Response r : response) {
                        result.add(r.toString());
                    }
                    return result;
                });
    }
    public io.vertx.redis.client.Response del(String key) {
        return redisClient.del(List.of(key));
    }

}
