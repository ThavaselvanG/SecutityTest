package com.demo.app.demo.apis.accounts;

import com.demo.app.demo.customannotation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "user_info")
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Size(max = 20, message = "Username should be maximum 20 character")
    @Column(name = "user_name", unique = true, length = 20)
    @Size(min = 5,max = 20,message = "Username should be min 5 character")
    private String userName;
    @Size(min = 10, max = 10, message = "Mobile number should be 10 digits")
    private String mobileNumber;
    @Email(message = "Invalid email address")
    private String email;
    @Size(max = 30, message = "Fullname should be maximum 50 character")
    @Column(name = "full_name")
    private String fullName;
    @ValidPassword
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private List<@Valid Address> addressList;


}
