package com.example.Booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ScrapRecycleController {

    @GetMapping("/scrap") // Changed from "/" to "/scrap"
    public String home(Model model) {
        List<SocialMedia> socialMediaList = Arrays.asList(
                new SocialMedia("Facebook", "https://www.facebook.com/ScrapAndRecycle", "fa-brands fa-facebook"),
                new SocialMedia("Instagram", " ", "fa-brands fa-instagram"),
                new SocialMedia("Twitter", "https://www.twitter.com/ScrapAndRecycle", "fa-brands fa-twitter"),
                new SocialMedia("LinkedIn", "  ", "fa-brands fa-linkedin")
        );
        model.addAttribute("socialMediaList", socialMediaList);
        return "scrap"; // Use a different view if needed, or keep "index"
    }

    public static class SocialMedia {
        private String name;
        private String url;
        private String iconClass;

        public SocialMedia(String name, String url, String iconClass) {
            this.name = name;
            this.url = url;
            this.iconClass = iconClass;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public String getIconClass() {
            return iconClass;
        }
    }
}