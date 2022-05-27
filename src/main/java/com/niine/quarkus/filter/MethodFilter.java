package com.niine.quarkus.filter;

import java.util.Optional;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;


public class MethodFilter {
    @ServerRequestFilter(preMatching = true)
    public Optional<RestResponse<Void>> getFilter(ContainerRequestContext ctx) {
        // only allow GET methods for now
        System.out.println("MethodFilter.getFilter");
        if ("/hello".equals(ctx.getUriInfo().getPath()) && !HttpMethod.GET.equals(ctx.getMethod())) {
            return Optional.of(RestResponse.status(Response.Status.METHOD_NOT_ALLOWED));
        }
        return Optional.empty();
    }
}
