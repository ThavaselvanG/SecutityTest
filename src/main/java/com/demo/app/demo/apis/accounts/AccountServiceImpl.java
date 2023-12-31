package com.demo.app.demo.apis.accounts;

import com.demo.app.demo.exceptionhandler.ResourceNotFound;
import com.demo.app.demo.jwt.JwtService;
import com.demo.app.demo.model.JwtResponse;
import com.demo.app.demo.model.RefreshToken;
import com.demo.app.demo.model.userlogin.UserLogin;
import com.demo.app.demo.utils.responsehandler.ApiResponse;
import com.demo.app.demo.utils.responsehandler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepo accountRepo;
    @Autowired
    @Qualifier("apiHandler")
    ResponseHandler responseHandler;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired AccountInfoRepo accountInfoRepo;

    @Override
    public ResponseEntity<ApiResponse> createUser(UserInfo userInfo) {
        if (accountRepo.findByUserName(userInfo.getUserName()).isPresent()) {
            throw new UsernameNotFoundException("Username already exits");
        }
        String password = userInfo.getPassword();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo info = accountRepo.save(userInfo);
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUserName(), password));
        RefreshToken response = jwtService.createToken(info.getUserName());
        return responseHandler.successResponse(jwtService.getTokenResponse(response));
    }

    @Override
    public ResponseEntity<ApiResponse> userLogin(UserLogin userLogin) {


        AccountInfo info=new AccountInfo();
        info.setAmount(100);
        info.setMobileNumber("100");
        info.setUserName("thavam");
        accountInfoRepo.save(info);
        if (accountRepo.findByUserName(userLogin.getUserName()).isEmpty()) {
            throw new ResourceNotFound("Username not found");
        }
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassWord()));
        RefreshToken response = jwtService.createToken(userLogin.getUserName());
        return responseHandler.successResponse(jwtService.getTokenResponse(response));
    }

    @Override
    public ResponseEntity<ApiResponse> refreshToken(JwtResponse jwtResponse) {
        RefreshToken refreshToken = jwtService.createRefreshToken(jwtResponse.getSessionId());
        return responseHandler.successResponse(jwtService.getTokenResponse(refreshToken));
    }

    @Override
    public ResponseEntity<ApiResponse> getAll() {
        return responseHandler.successResponse(accountRepo.findAll());
    }


}
