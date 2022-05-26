package com.niine.quarkus.service;

import com.niine.quarkus.properties.GreetingProperties;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GreetingService {
    @Inject
    GreetingProperties greetingProperties;



    public String greeting(String name) {
        return greetingProperties.message() + " " + name + greetingProperties.suffix();
    }
}
