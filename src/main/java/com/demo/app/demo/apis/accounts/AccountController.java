package com.demo.app.demo.apis.accounts;

import com.demo.app.demo.model.JwtResponse;
import com.demo.app.demo.model.userlogin.UserLogin;
import com.demo.app.demo.utils.responsehandler.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/authenticate")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserInfo userInfo) {
        return accountService.createUser(userInfo);
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponse> userLogin(@Valid @RequestBody UserLogin userLogin) {
        return accountService.userLogin(userLogin);
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<ApiResponse> refreshToken(@Valid @RequestBody JwtResponse jwtResponse) {
        return accountService.refreshToken(jwtResponse);
    }


}
