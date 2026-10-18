package com.judev.nasa_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class DashboardController {

    private final NasaEventService service;

    public DashboardController(NasaEventService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String dashboard(Model model){
        model.addAttribute("events",service.getEvents());
        return "dashboard";
    }

}
