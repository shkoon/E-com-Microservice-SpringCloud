package org.sid.billingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billingservice.model.Customer;

import java.util.Date;
import java.util.List;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class Bill {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Date billDate;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItemList;
    @Transient
    private Customer customer;

    public double getTotale(){
        int somme=0;
        for(ProductItem p:productItemList){
            somme+=p.getAmount();
        }
        return somme;
    }

}
