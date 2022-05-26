package com.niine.quarkus.resource;

import com.niine.quarkus.service.GreetingService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    public GreetingResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * curl -X GET http://localhost:8080/hello
     *
     * @return Hello from RESTEasy Reactive
     */

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    /**
     * curl -X GET http://localhost:8080/hello/quarkus
     *
     * @return Hello quarkus
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String hello(String name) {

        long executionStart = System.currentTimeMillis();
        String response = greetingService.greeting(name);

        long executionEnd = System.currentTimeMillis();
        System.out.println("Execution time: " + (executionEnd - executionStart) + " ms");
        return response;
    }
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String delete(String name) {
        return greetingService.delete(name);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete() {
        return greetingService.deleteAll();
    }

}