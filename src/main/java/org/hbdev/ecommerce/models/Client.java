package org.hbdev.ecommerce.models;

import lombok.*;
import org.hbdev.ecommerce.enums.Gender;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class Client extends Person {
    private Gender gender;
    public Client(String fullName, String phone, String email, String address, Gender gender) {
        super(fullName, phone, email, address);
        this.gender = gender;
    }

    public Client(int id, String fullName, String phone,  String email, String address, Gender gender) {
        super(id, fullName, phone, email, address);
        this.gender = gender;
    }

}
