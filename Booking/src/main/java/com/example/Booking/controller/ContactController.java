package com.example.Booking.controller;

import com.example.Booking.model.ContactMessage;
import com.example.Booking.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitContactForm(@Valid @RequestBody ContactMessage contactMessage, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        logger.info("Received form data: {}", contactMessage);

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            response.put("status", "error");
            response.put("errors", errors);
            logger.error("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            contactMessageRepository.save(contactMessage);
            response.put("status", "success");
            response.put("message", "Successfully message sent");
            logger.info("Message saved successfully: {}", contactMessage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to save message: " + e.getMessage());
            logger.error("Error saving message", e);
            return ResponseEntity.status(500).body(response);
        }
    }
}