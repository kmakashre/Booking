package com.example.Booking.controller;

import com.example.Booking.dto.BillDTO;
import com.example.Booking.service.BillService;
import com.example.Booking.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping
    public ResponseEntity<BillDTO> createBill(@RequestBody BillDTO billDTO) {
        BillDTO savedBill = billService.createBill(billDTO);
        return ResponseEntity.ok(savedBill);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> getBill(@PathVariable Long id) {
        BillDTO bill = billService.getBill(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generateBillPdf(@PathVariable Long id) throws Exception {
        BillDTO bill = billService.getBill(id);
        byte[] pdfBytes = PdfGenerator.generateBillPdf(bill);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "bill_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}