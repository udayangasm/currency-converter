package com.zooplus.bitcoin.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new User("kiyon", "$2a$10$AazCdVKRfHijORUNFB8y8O6VuvJ4HoU.wjCiLDAEeZCFGiEp6hkdS", new ArrayList<>());
    }
}