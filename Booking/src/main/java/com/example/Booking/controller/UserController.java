package com.example.Booking.controller;

import com.example.Booking.model.ContactMessage;
import com.example.Booking.model.SocialMedia;
import com.example.Booking.model.User;
import com.example.Booking.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            user = new User();
            session.setAttribute("currentUser", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("contactForm", new ContactMessage());
        List<SocialMedia> socialMediaList = Arrays.asList(
                new SocialMedia("Facebook", "https://www.facebook.com/ReScrap4/", "fab fa-facebook-f"),
                new SocialMedia("Instagram", "https://www.instagram.com/rescrap2025/", "fab fa-instagram"),
                new SocialMedia("Twitter", "https://x.com/re_scrap", "fab fa-twitter"),
                new SocialMedia("LinkedIn", "https://www.linkedin.com/in/re-scrap-26a981361/", "fab fa-linkedin-in")
        );
        model.addAttribute("socialMediaList", socialMediaList);
        return "index";
    }

    @PostMapping("/mobile")
    public String processMobileForm(@ModelAttribute User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("currentUser");
        if (sessionUser == null) {
            sessionUser = new User();
            session.setAttribute("currentUser", sessionUser);
        }

        // Validate input
        if (user.getName() == null || user.getMobile() == null || user.getName().isEmpty() || user.getMobile().isEmpty()) {
            model.addAttribute("error", "Name and mobile number are required!");
            model.addAttribute("user", sessionUser);
            model.addAttribute("contactForm", new ContactMessage());
            return "index";
        }

        if (!user.getMobile().matches("\\d{10}")) {
            model.addAttribute("error", "Mobile number must be 10 digits!");
            model.addAttribute("user", sessionUser);
            model.addAttribute("contactForm", new ContactMessage());
            return "index";
        }

        // Update session user
        sessionUser.setName(user.getName());
        sessionUser.setMobile(user.getMobile());

        // Save to database
        userRepository.save(sessionUser);

        return "redirect:/address";
    }

    @GetMapping("/address")
    public String showAddressForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            user = new User();
            session.setAttribute("currentUser", user);
        }
        model.addAttribute("user", user);
        return "address";
    }

    @PostMapping("/address")
    public String processAddressForm(@ModelAttribute User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("currentUser");
        if (sessionUser == null) {
            sessionUser = new User();
            session.setAttribute("currentUser", sessionUser);
        }

        // Validate input
        if (user.getAddress() == null || user.getPincode() == null || user.getLandmark() == null ||
                user.getAddress().isEmpty() || user.getPincode().isEmpty() || user.getLandmark().isEmpty()) {
            model.addAttribute("error", "Address, pincode, and landmark are required!");
            model.addAttribute("user", sessionUser);
            return "address";
        }

        if (!user.getPincode().matches("\\d{6}")) {
            model.addAttribute("error", "Pincode must be 6 digits!");
            model.addAttribute("user", sessionUser);
            return "address";
        }

        // Update session user
        sessionUser.setAddress(user.getAddress());
        sessionUser.setPincode(user.getPincode());
        sessionUser.setLandmark(user.getLandmark());

        // Save to database
        userRepository.save(sessionUser);

        return "redirect:/schedule";
    }

    @GetMapping("/schedule")
    public String showScheduleForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            user = new User();
            session.setAttribute("currentUser", user);
        }
        model.addAttribute("user", user);
        return "schedule";
    }

    @PostMapping("/schedule")
    public String processScheduleForm(@ModelAttribute User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("currentUser");
        if (sessionUser == null) {
            sessionUser = new User();
            session.setAttribute("currentUser", sessionUser);
        }

        // Validate input
        if (user.getPreferredDate() == null || user.getPreferredTime() == null || user.getNotes() == null || user.getNotes().isEmpty()) {
            model.addAttribute("error", "Preferred date, time, and notes are required!");
            model.addAttribute("user", sessionUser);
            return "schedule";
        }

        // Validate date is not in the past
        if (user.getPreferredDate().isBefore(java.time.LocalDate.now())) {
            model.addAttribute("error", "Preferred date cannot be in the past!");
            model.addAttribute("user", sessionUser);
            return "schedule";
        }

        // Update session user
        sessionUser.setPreferredDate(user.getPreferredDate());
        sessionUser.setPreferredTime(user.getPreferredTime());
        sessionUser.setNotes(user.getNotes());

        // Save to database
        userRepository.save(sessionUser);

        return "redirect:/sell";
    }

    @GetMapping("/sell")
    public String showSellForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            user = new User();
            session.setAttribute("currentUser", user);
        }
        model.addAttribute("user", user);
        return "sell";
    }

    @PostMapping("/sell")
    public String processSellForm(@ModelAttribute User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("currentUser");
        if (sessionUser == null) {
            sessionUser = new User();
            session.setAttribute("currentUser", sessionUser);
        }

        // Validate input
        if (user.getMaterialType() == null || user.getWeight() == null || user.getDescription() == null ||
                user.getMaterialType().isEmpty() || user.getWeight() <= 0 || user.getDescription().isEmpty()) {
            model.addAttribute("error", "Material type, weight, and description are required, and weight must be greater than 0!");
            model.addAttribute("user", sessionUser);
            return "sell";
        }

        // Update session user
        sessionUser.setMaterialType(user.getMaterialType());
        sessionUser.setWeight(user.getWeight());
        sessionUser.setDescription(user.getDescription());

        // Save to database
        userRepository.save(sessionUser);

        // Clear session after successful booking
        session.removeAttribute("currentUser");

        // Add success message for confirmation page
        model.addAttribute("message", "Booking Successfully Completed!");

        return "confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmation(Model model, HttpSession session) {
        // Ensure message is available if accessed directly
        if (!model.containsAttribute("message")) {
            model.addAttribute("message", "Booking Successfully Completed!");
        }
        return "confirmation";
    }
}