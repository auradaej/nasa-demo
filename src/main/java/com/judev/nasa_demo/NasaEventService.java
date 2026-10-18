package com.judev.nasa_demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class NasaEventService {
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public NasaEventService(ObjectMapper objectMapper) {
        this.restClient = RestClient.builder().build();
        this.objectMapper = objectMapper;
    }

    public List<NasaEvent> getEvents() {
        System.out.println(this.getClass() + " started obtaining NASA events.");

        // the API sometimes returns the wrong content type but it is JSON every time
        byte[] response = restClient.get()
                .uri("https://eonet.gsfc.nasa.gov/api/v3/events?status=open&limit=20&days=10")
                .retrieve()
                .body(byte[].class);

        // parse to JSON
        if (response == null ) return List.of();

        try {
            ObjectMapper mapper = objectMapper;
            NasaEventResponse jsonResponse = mapper.readValue(response, NasaEventResponse.class);

            if (jsonResponse == null || jsonResponse.events() == null) {
                return List.of();
            }

            System.out.println(this.getClass() + " finished obtaining NASA events. Result: " + jsonResponse.events().size());
            return jsonResponse.events();

        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }

    }
}
