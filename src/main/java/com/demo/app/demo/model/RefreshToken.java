package com.demo.app.demo.model;

import com.demo.app.demo.apis.accounts.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tokenId;
    private String sessionsId;
    private Instant expiryDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserInfo userInfo;
}
