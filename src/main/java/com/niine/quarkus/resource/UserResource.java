package com.niine.quarkus.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.niine.quarkus.model.response.User;
import com.niine.quarkus.model.response.Views;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @GET
    @Path("/public")
    public User userPublic() {
        return testUser();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Private.class)
    @GET
    @Path("/private")
    public User userPrivate() {
        return testUser();
    }

    private User testUser() {
        var user = new User();
        user.id = 1;
        user.name = "test";
        return user;
    }
}
