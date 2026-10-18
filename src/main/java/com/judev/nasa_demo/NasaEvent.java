package com.judev.nasa_demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NasaEvent(
        String id,
        String title,
        String description
) { }
