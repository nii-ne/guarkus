package com.niine.quarkus.service;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

@ApplicationScoped
public class CounterBean {

    private final AtomicInteger counter = new AtomicInteger();

    public int get() {
        return counter.get();
    }

    @Scheduled(every = "10s")
    void increment() {
        counter.incrementAndGet();
        System.out.println("Every 10 seconds");
    }

    @Scheduled(cron = "1 * * * * ?")
    void cronJob(ScheduledExecution execution) {
        counter.incrementAndGet();
        System.out.println(execution.getScheduledFireTime());
    }

    @Scheduled(cron = "{cron.expr}")
    void cronJobWithExpressionInConfig() {
        counter.incrementAndGet();
        System.out.println("Cron expression configured in application.properties");
    }
}
