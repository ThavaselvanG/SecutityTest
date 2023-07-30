package com.demo.app.demo.repository;

import com.demo.app.demo.apis.accounts.UserInfo;
import com.demo.app.demo.model.RefreshToken;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    //@EntityGraph(value = "UserInfo.userInfo", type = EntityGraph.EntityGraphType.FETCH)
    Optional<RefreshToken> findByUserInfoUserId(long id);
}
