package org.hbdev.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    @NonNull
    private Client client;
    @NonNull
    private Product product;
    @NonNull
    private int quantity;

    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }
}
