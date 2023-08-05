package com.demo.app.demo.apis.accounts;

import jakarta.persistence.*;

@Entity
@Table(name = "account_info")
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    private int amount;
    private String mobileNumber;
    private String userName;

}
