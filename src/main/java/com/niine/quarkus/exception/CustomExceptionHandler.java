package com.niine.quarkus.exception;

import com.niine.quarkus.model.response.CommonResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        var commonResponse = new CommonResponse<>();
        if (e instanceof ValidateException) {
            commonResponse = new CommonResponse<>("400-000", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE).entity(commonResponse).build();
        } else if (e instanceof DataNotFoundException) {
            commonResponse = new CommonResponse<>("404-000", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON_TYPE).entity(commonResponse).build();
        } else {
            commonResponse = new CommonResponse<>("500-000", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON_TYPE).entity(commonResponse).build();
        }
    }
}

