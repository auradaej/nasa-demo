package com.judev.nasa_demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NasaEventResponse(List<NasaEvent> events) {

}
