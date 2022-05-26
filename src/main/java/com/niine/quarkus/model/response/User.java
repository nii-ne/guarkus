package com.niine.quarkus.model.response;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    @JsonView(Views.Private.class)
    public int id;

    @JsonView(Views.Public.class)
    public String name;
}
