package com.example.Booking.util;

import com.example.Booking.dto.BillDTO;
import com.example.Booking.dto.ItemDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;


import java.io.ByteArrayOutputStream;

public class PdfGenerator {

    public static byte[] generateBillPdf(BillDTO bill) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Title
        document.add(new Paragraph("Scrap Sell Bill")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold());

        // Bill Details
        document.add(new Paragraph("Date: " + bill.getDate()));
        document.add(new Paragraph("Customer Name: " + bill.getCustomerName()));
        document.add(new Paragraph("Customer Address: " + bill.getCustomerAddress()));
        document.add(new Paragraph(" "));

        // Items Table
        Table table = new Table(new float[]{1, 3, 2, 2, 2});
        table.addHeaderCell("S.No");
        table.addHeaderCell("Item Description");
        table.addHeaderCell("Quantity (kg)");
        table.addHeaderCell("Rate (per kg)");
        table.addHeaderCell("Amount");

        int index = 1;
        for (ItemDTO item : bill.getItems()) {
            table.addCell(String.valueOf(index++));
            table.addCell(item.getDescription());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getRate()));
            table.addCell(String.valueOf(item.getAmount()));
        }

        document.add(table);

        // Totals
        document.add(new Paragraph("Total Amount: " + bill.getTotalAmount() + " INR")
                .setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph("CGST (9%): " + bill.getCgst() + " INR")
                .setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph("SGST (9%): " + bill.getSgst() + " INR")
                .setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph("Grand Total: " + bill.getGrandTotal() + " INR")
                .setTextAlignment(TextAlignment.RIGHT));

        document.close();
        return baos.toByteArray();
    }
}