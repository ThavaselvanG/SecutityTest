package com.demo.app.demo.model.userlogin;

import com.demo.app.demo.customannotation.ValidPassword;
import lombok.Data;

@Data
public class UserLogin {
    private String userName;
    @ValidPassword
    private String passWord;

}
