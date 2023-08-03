package com.demo.app.demo.apis.payment;

import com.demo.app.demo.apis.accounts.AccountService;
import com.demo.app.demo.utils.responsehandler.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/admin")
public class PaymentController {
    @Autowired
    AccountService accountService;


    @GetMapping("/getUsers")
    @Secured("ADMIN")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> geatUser() {
        return accountService.getAll();
    }
}
