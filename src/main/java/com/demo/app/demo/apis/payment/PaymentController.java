package com.demo.app.demo.apis.payment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/payment")
public class PaymentController {


    @GetMapping("/test")
    public String laogin(String s) {
        return "I can User";
    }
}
