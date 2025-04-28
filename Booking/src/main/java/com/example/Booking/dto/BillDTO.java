package com.example.Booking.dto;// src/main/java/com/scraps/dto/BillDTO.java

import java.time.LocalDate;
import java.util.List;

public class BillDTO {
    private Long id;
    private LocalDate date;
    private String customerName;
    private String customerAddress;
    private double totalAmount;
    private double cgst;
    private double sgst;
    private double grandTotal;
    private List<ItemDTO> items;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public double getCgst() { return cgst; }
    public void setCgst(double cgst) { this.cgst = cgst; }
    public double getSgst() { return sgst; }
    public void setSgst(double sgst) { this.sgst = sgst; }
    public double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(double grandTotal) { this.grandTotal = grandTotal; }
    public List<ItemDTO> getItems() { return items; }
    public void setItems(List<ItemDTO> items) { this.items = items; }
}