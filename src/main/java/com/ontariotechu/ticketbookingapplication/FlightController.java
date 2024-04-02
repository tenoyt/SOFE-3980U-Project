package com.ontariotechu.ticketbookingapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin("http://localhost:8080/")
@RestController
public class FlightController {
    @GetMapping("/")
    public RedirectView index() {
        // Redirect to the static index.html page
        return new RedirectView("index.html");
    }
}
