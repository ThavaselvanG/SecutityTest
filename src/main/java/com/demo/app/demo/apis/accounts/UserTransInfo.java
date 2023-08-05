package com.demo.app.demo.apis.accounts;

import com.demo.app.demo.apis.payment.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_trans_info")
public class UserTransInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transInfoId;
    private String receiverNumber;
    private int paidAmount;
    private int totalAmount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private UUID transactionId;
    @UpdateTimestamp
    private Instant transactionDate;


}
