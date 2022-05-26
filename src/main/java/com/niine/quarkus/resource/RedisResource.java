package com.niine.quarkus.resource;

import com.niine.quarkus.service.RedisService;
import io.smallrye.mutiny.Uni;
import io.vertx.redis.client.Response;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/redis")
public class RedisResource {
    private final RedisService redisService;

    public RedisResource(RedisService redisService) {
        this.redisService = redisService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @ResponseStatus(RestResponse.StatusCode.OK)
    public Uni<List<String>> get() {
        return redisService.keys();
    }
    @GET
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    @ResponseStatus(RestResponse.StatusCode.OK)
    public String get(String key) {
        return redisService.get(key);
    }

    @POST
    @Path("/{key}/{value:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    public String set(String key, Integer value) {
        redisService.set(key, value);
        return "OK";
    }
    @PUT
    @Path("/{key}/{value:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @ResponseStatus(RestResponse.StatusCode.OK)
    public Response increment(String key, Integer value) {
        return redisService.increment(key, value);
    }

    @DELETE
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    @ResponseStatus(RestResponse.StatusCode.OK)
    public Uni<Void> delete(@PathParam("key") String key) {
        return redisService.del(key);
    }
}
