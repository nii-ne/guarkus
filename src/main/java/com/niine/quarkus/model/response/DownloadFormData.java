package com.niine.quarkus.model.response;

import java.io.File;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

public class DownloadFormData {
    public DownloadFormData() {
    }

    @RestForm
    String name;

    @RestForm
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    File file;
}
