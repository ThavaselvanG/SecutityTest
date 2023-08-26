package com.demo.app.demo.apis.accounts;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_info")
@Data
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    private int amount;
    private String mobileNumber;
    private String userName;

  //  @OneToMany(cascade = CascadeType.ALL)
  //  @JoinColumn(name = "userId", referencedColumnName = "userId")
  //  private UserInfo userInfo;
}
