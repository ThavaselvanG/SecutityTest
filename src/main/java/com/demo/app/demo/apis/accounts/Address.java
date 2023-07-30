package com.demo.app.demo.apis.accounts;

import com.demo.app.demo.customannotation.AddressTypeValidation;
import jakarta.persistence.*;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "user_address")
@Data
@Valid
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    @NotBlank(message = "Street number  must not be null or empty")
    private String streetNumber;
    @NotBlank(message = "Address line must not or empty")
    private String addressLineOne;
    private String addressLineTwo;
    @AddressTypeValidation
    @Column(name = "address_type", columnDefinition = "TINYINT", length = 2)
    private int addressType;
    private String state;
    @Digits(fraction = 0, integer = 6, message = "Pin code must be 6 digits only")
    private String pinCode;


}
