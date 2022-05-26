package com.niine.quarkus.properties;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;
import java.util.Optional;
@StaticInitSafe
@ConfigMapping(prefix = "greeting")
public interface GreetingProperties {

    String message();

     Optional<String> name();
     @WithName("others")
     String suffix();
}