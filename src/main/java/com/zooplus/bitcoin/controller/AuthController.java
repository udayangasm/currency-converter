package com.zooplus.bitcoin.controller;

import com.zooplus.bitcoin.domain.JwtRequest;
import com.zooplus.bitcoin.domain.JwtResponse;
import com.zooplus.bitcoin.security.JwtTokenUtil;
import com.zooplus.bitcoin.service.CurrencyService;
import com.zooplus.bitcoin.service.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CurrencyService currencyService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping({"/auth"})
    public String init(Model model) {

        logger.info("login user" );
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String createAuthenticationToken(@ModelAttribute JwtRequest authenticationRequest,Model model) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        currencyService.loadLandingPage(model);
        model.addAttribute("jwt",token);
        return "home";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
