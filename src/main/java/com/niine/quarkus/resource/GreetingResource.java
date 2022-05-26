package com.niine.quarkus.resource;

import com.niine.quarkus.model.request.FormData;
import com.niine.quarkus.model.response.DownloadFormData;
import com.niine.quarkus.service.GreetingService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.ResponseHeader;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestHeader;
import org.jboss.resteasy.reactive.RestMatrix;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

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
     * @return Hello from RESTEasy Reactive
     */

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    /**
     * curl -X GET http://localhost:8080/hello/quarkus
     * @return Hello quarkus
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}/{nickname}/{age}/{location}")
    public String hello(String age, String name, @PathParam("location") String local, @RestPath("nickname") String aka) {
        System.out.println("age: " + age);
        System.out.println("name: " + name);
        System.out.println("local: " + local);
        System.out.println("nickname: " + aka);

        return greetingService.greeting(name);
    }
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}/{age:\\d+}")
    @GET
    public String personalisedHello(String name, int age) {
        return "Hello " + name + " is your age really " + age + "?";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{type}")

    @ResponseStatus(201)
    @ResponseHeader(name = "X-Code", value = "See twelve earths")
    public String allParams(@RestPath String type,
                            @RestQuery String age,
                            @RestHeader("X-Correlation-ID") String correlationId,
                            @RestForm String smell,
                            @RestCookie String level,
                            @RestMatrix String variant) {
        return type + "/" + age  + "/" + correlationId + "/" + smell + "/" + level+ "/" + variant;
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    public String form(@MultipartForm FormData formData) {
        return formData.getFile().fileName() + ": "+ formData.getDescription();
    }

    @GET
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @Path("/download")
    public DownloadFormData download() {
        return new DownloadFormData();
    }

}