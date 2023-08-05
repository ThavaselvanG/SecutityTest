package com.demo.app.demo.apis.accounts;

import com.demo.app.demo.apis.accounts.AccountRepo;
import com.demo.app.demo.apis.accounts.Roles;
import com.demo.app.demo.apis.accounts.UserInfo;
import com.demo.app.demo.exceptionhandler.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepo accountRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFound {
        UserInfo user = accountRepo.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFound("User Not Found"));
        return new User(user.getUserName(), user.getPassword(), getRole(user.getRole().name()));
    }

    private Collection<? extends GrantedAuthority> getRole(String s) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(s));
        return list;
    }
}
