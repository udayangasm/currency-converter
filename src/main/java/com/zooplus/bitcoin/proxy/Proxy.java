package com.zooplus.bitcoin.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Proxy {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.bitcoin.url}")
    private String bitcoinUrl;

    @Value("${api.currency-converter.url}")
    private String currencyConverterUrl;

    @Value("${api.currency-by-ip.url}")
    private String currencyByIpUrl;

    public Proxy() {
    }

    public String getBitCoinValues() {
        return restTemplate.getForObject(bitcoinUrl, String.class);
    }

    public String convertUsdToTargetCurrency( String targetCurrency) {
        return restTemplate.getForObject(currencyConverterUrl+targetCurrency, String.class);
    }

    public String getCurrencyByIp(String ip) {
        return restTemplate.getForObject(currencyByIpUrl.replace("{{ip}}",ip), String.class);
    }
}