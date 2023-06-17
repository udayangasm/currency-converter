package com.zooplus.bitcoin.controller;

import com.zooplus.bitcoin.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService currencyService;

    @GetMapping({"/"})
    public String init(Model model) {

        logger.info("Initial landing page" );
        currencyService.loadLandingPage(model);
        return "home";
    }

    @GetMapping({"/convert"})
    public String convert(Model model, @RequestParam(value = "cryptoDropDownList") int index, @RequestParam(value = "ip", required = false, defaultValue = "request") String ip) {

        logger.info("Converting currency by use inputs" );
        currencyService.convertValue(model, index, ip);
        return "home";
    }
}