package org.hbdev.ecommerce.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class Product {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private double price;
}
