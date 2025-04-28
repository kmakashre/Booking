package com.example.Booking.service;

import com.example.Booking.dto.BillDTO;
import com.example.Booking.dto.ItemDTO;
import com.example.Booking.model.Bill;
import com.example.Booking.model.Item;
import com.example.Booking.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public BillDTO createBill(BillDTO billDTO) {
        Bill bill = new Bill();
        bill.setDate(billDTO.getDate());
        bill.setCustomerName(billDTO.getCustomerName());
        bill.setCustomerAddress(billDTO.getCustomerAddress());

        List<Item> items = billDTO.getItems().stream().map(itemDTO -> {
            Item item = new Item();
            item.setDescription(itemDTO.getDescription());
            item.setQuantity(itemDTO.getQuantity());
            item.setRate(itemDTO.getRate());
            item.setAmount(itemDTO.getQuantity() * itemDTO.getRate());
            return item;
        }).collect(Collectors.toList());

        bill.setItems(items);

        double totalAmount = items.stream().mapToDouble(Item::getAmount).sum();
        double cgst = totalAmount * 0.09;
        double sgst = totalAmount * 0.09;
        double grandTotal = totalAmount + cgst + sgst;

        bill.setTotalAmount(totalAmount);
        bill.setCgst(cgst);
        bill.setSgst(sgst);
        bill.setGrandTotal(grandTotal);

        Bill savedBill = billRepository.save(bill);

        billDTO.setId(savedBill.getId());
        billDTO.setTotalAmount(savedBill.getTotalAmount());
        billDTO.setCgst(savedBill.getCgst());
        billDTO.setSgst(savedBill.getSgst());
        billDTO.setGrandTotal(savedBill.getGrandTotal());
        return billDTO;
    }

    public BillDTO getBill(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        BillDTO billDTO = new BillDTO();
        billDTO.setId(bill.getId());
        billDTO.setDate(bill.getDate());
        billDTO.setCustomerName(bill.getCustomerName());
        billDTO.setCustomerAddress(bill.getCustomerAddress());
        billDTO.setTotalAmount(bill.getTotalAmount());
        billDTO.setCgst(bill.getCgst());
        billDTO.setSgst(bill.getSgst());
        billDTO.setGrandTotal(bill.getGrandTotal());

        List<ItemDTO> itemDTOs = bill.getItems().stream().map(item -> {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setDescription(item.getDescription());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setRate(item.getRate());
            itemDTO.setAmount(item.getAmount());
            return itemDTO;
        }).collect(Collectors.toList());

        billDTO.setItems(itemDTOs);
        return billDTO;
    }
}