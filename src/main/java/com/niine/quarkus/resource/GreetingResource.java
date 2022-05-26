package com.niine.quarkus.resource;

import com.niine.quarkus.service.GreetingService;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {
    /**
     * Annotation Injection of the GreetingService
     *
     * @Inject
     * GreetingService greetingService;
     */


    /**
     * Constructor Injection
     */
    private final GreetingService greetingService;

    @ConfigProperty(name = "greeting.message")
    String message;

    @ConfigProperty(name = "greeting.suffix", defaultValue="!")
    String suffix;

    @ConfigProperty(name = "greeting.name")
    Optional<String> name;
    public GreetingResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * curl -X GET http://localhost:8080/hello
     * @return Hello from RESTEasy Reactive
     */

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + " " + name.orElse("world") + suffix;
    }

    /**
     * curl -X GET http://localhost:8080/hello/quarkus
     * @return Hello quarkus
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String hello(String name) {
        return greetingService.greeting(name);
    }

}