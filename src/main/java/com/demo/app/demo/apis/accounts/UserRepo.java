package com.demo.app.demo.apis.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo>  findByFullName(String name);
    Optional<UserInfo>  findByUserName(String name);
}
