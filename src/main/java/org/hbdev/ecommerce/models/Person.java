package org.hbdev.ecommerce.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    private int id;
    @NonNull
    private String fullName;
    @NonNull
    private String phone;
    @NonNull
    private String email;
    @NonNull
    private String address;
}
