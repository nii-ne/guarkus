package com.niine.quarkus.interceptor;

import java.io.IOException;
import java.util.UUID;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Provider

public class IOInterceptor implements ReaderInterceptor, WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {
        System.err.println("Before writing " + context.getEntity());
        context.proceed();
        System.err.println("After writing " + context.getEntity());
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context)
            throws IOException, WebApplicationException {
        String correlationId = context.getHeaders().getFirst("X-Correlation-Id");
        System.out.println("Before reading " + correlationId);
        if(correlationId == null || correlationId.isBlank()){
            context.getHeaders().add("X-Correlation-Id", UUID.randomUUID().toString());
        }

        System.out.println(context.getHeaders().getFirst("X-Correlation-Id"));

        System.err.println("Before reading " + context.getGenericType());
        Object entity = context.proceed();
        System.err.println("After reading " + entity);
        return entity;
    }
}
