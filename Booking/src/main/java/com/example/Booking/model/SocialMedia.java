package com.example.Booking.model;

public class SocialMedia {
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