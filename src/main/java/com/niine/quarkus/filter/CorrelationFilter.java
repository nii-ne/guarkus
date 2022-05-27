package com.niine.quarkus.filter;

import java.util.List;
import java.util.UUID;
import javax.ws.rs.container.ContainerRequestContext;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
public class CorrelationFilter {
    @ServerRequestFilter(priority = 1)
    public void preMatchingFilter(ContainerRequestContext requestContext) {
        System.out.println("preMatchingFilter");
        List<String> correlations = requestContext.getHeaders().get("X-Correlation-Id");
        if(null == correlations || correlations.isEmpty())
            requestContext.getHeaders().add("X-Correlation-Id", UUID.randomUUID().toString());
    }
}
