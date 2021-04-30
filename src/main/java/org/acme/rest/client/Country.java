package org.acme.rest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    public String name;

    Country(String name) {
        this.name = name;
    }

}
