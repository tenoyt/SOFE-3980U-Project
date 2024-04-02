package com.ontariotechu.ticketbookingapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flight")
public class FlightController {

    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        // Add any necessary model attributes for the booking form
        model.addAttribute("bookingInfo", new BookingInfo());
        return "booking-form"; // Return the name of the HTML file (without extension)
    }

    @PostMapping("/book")
    public String bookFlight(BookingInfo bookingInfo, Model model) {
        // Process the submitted booking information
        // You can access form fields via the BookingInfo object
        
        // Example: bookingInfo.getFlightNumber(), bookingInfo.getPassengerName(), etc.
        
        // Add logic to handle booking and save data
        
        // Add success message or any necessary data to the model
        model.addAttribute("message", "Booking successful!");
        
        return "booking-success"; // Return the name of the HTML file for success page
    }

    @GetMapping("/view")
    public String viewBooking(Model model) {
        // Retrieve booking information from the database or any other source
        // Add booking information to the model
        
        // Example: model.addAttribute("bookings", bookingService.getAllBookings());
        
        return "view-booking"; // Return the name of the HTML file for viewing bookings
    }
}
