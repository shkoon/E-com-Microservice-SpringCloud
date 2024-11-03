package org.sid.billingservice.controllers;

import lombok.AllArgsConstructor;
import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.service.CustomerRestClient;
import org.sid.billingservice.service.ProductRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bill")
@AllArgsConstructor
public class BillRestController {

    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;


    @GetMapping("/fullbill/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        Customer customer=customerRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItemList().forEach((p)->{
            Product product=productRestClient.findProductById(p.getProductId());
            p.setProduct(product);
        });

        return  bill;
    }

}
