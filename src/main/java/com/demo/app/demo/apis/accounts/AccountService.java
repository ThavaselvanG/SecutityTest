package com.demo.app.demo.apis.accounts;

import com.demo.app.demo.model.JwtResponse;
import com.demo.app.demo.model.userlogin.UserLogin;
import com.demo.app.demo.utils.responsehandler.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<ApiResponse> createUser(UserInfo userInfo);

    ResponseEntity<ApiResponse> userLogin(UserLogin userLogin);

    ResponseEntity<ApiResponse> refreshToken(JwtResponse jwtResponse);

    ResponseEntity<ApiResponse> getAll();
}
