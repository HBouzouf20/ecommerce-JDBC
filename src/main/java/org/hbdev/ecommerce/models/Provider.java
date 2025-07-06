package org.hbdev.ecommerce.models;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class Provider extends Person {
    private String ICE;
    private String RIB;
    private String companyName;

    public Provider(int id, @NonNull String fullName, @NonNull String phone, @NonNull String email, @NonNull String address, String ICE, String RIB, String companyName) {
        super(id, fullName, phone, email, address);
        this.ICE = ICE;
        this.RIB = RIB;
        companyName = companyName;
    }

    public Provider(String ICE, String RIB, String companyName) {
        this.ICE = ICE;
        this.RIB = RIB;
        companyName = companyName;
    }

    public Provider(@NonNull String fullName, @NonNull String phone, @NonNull String email, @NonNull String address, String ICE, String RIB, String companyName) {
        super(fullName, phone, email, address);
        this.ICE = ICE;
        this.RIB = RIB;
        companyName = companyName;
    }
}
