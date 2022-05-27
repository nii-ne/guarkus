package com.niine.quarkus.exception;

import com.niine.quarkus.model.response.CommonResponse;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class CustomExceptionHandler {
    @ServerExceptionMapper
    public RestResponse<CommonResponse> mapException(RuntimeException e) {
        if (e instanceof DataNotFoundException) {
            return RestResponse.status(Response.Status.NOT_FOUND, new CommonResponse<>("404-000", e.getMessage()));
        } else if (e instanceof ValidateException) {
            return RestResponse.status(Response.Status.BAD_REQUEST, new CommonResponse<>("400-000", e.getMessage()));
        } else {
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, new CommonResponse<>("500-000", e.getMessage()));
        }
    }
}

