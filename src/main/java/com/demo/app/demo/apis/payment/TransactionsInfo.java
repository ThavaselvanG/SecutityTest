package com.demo.app.demo.apis.payment;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "trans_info")
public class TransactionsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tarnsId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private TransStatus transStatus;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updateDate;
    private String senderNumber;
    private String receiverNumber;
    private int transAMount;


}
