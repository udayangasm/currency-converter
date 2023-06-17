package com.zooplus.bitcoin.service;

import com.zooplus.bitcoin.controller.CurrencyController;
import com.zooplus.bitcoin.proxy.Proxy;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static com.zooplus.bitcoin.util.Constants.*;

@Service
public class CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private Proxy proxy;

    @Autowired
    private HttpServletRequest request;

    public void loadLandingPage(Model model) {

        logger.debug("Initial landing page with pre-defined data set");
        model.addAttribute(PRICE, "€6.99");
        model.addAttribute(CRYPTO_LIST, getCryptoCurrencyList());
    }


    public void convertValue(Model model, int index, String ip) {

        logger.debug("fetching current crypto currency values");
        String bitcoinValues = proxy.getBitCoinValues();
        String selectedCryptoCurrency = getCryptoCurrencyList().get(index);

        if (ip.equals(REQUEST)) {
            ip = request.getLocalAddr();
            logger.debug("request ip will be used: {}", ip);
        }

        try {

            JSONObject jsonObject = new JSONObject(bitcoinValues);
            JSONObject bitcoinRates = jsonObject.getJSONObject(RATES);
            BigDecimal selectedBitCoinValueInUSD = bitcoinRates.getBigDecimal(selectedCryptoCurrency);

            String localCurrency = proxy.getCurrencyByIp(ip);

            if (localCurrency.equals(UNDEFINED)) {
                localCurrency = DEFAULT_CURRENCY;
            }

            String allCurrencyExchangeRates = proxy.convertUsdToTargetCurrency(localCurrency);
            JSONObject localCurrencyExchangeRate = new JSONObject(allCurrencyExchangeRates);

            BigDecimal localValue = localCurrencyExchangeRate.getJSONObject(DATA).getBigDecimal(localCurrency);

            BigDecimal bitCoinValueInLocal = selectedBitCoinValueInUSD.multiply(localValue);
            String currencySymbol = Currency.getInstance(localCurrency).getSymbol(Locale.getDefault(Locale.Category.DISPLAY));

            model.addAttribute(PRICE, currencySymbol + " " + bitCoinValueInLocal);

        } catch (JSONException ex) {
            logger.error("Error : ", ex.getLocalizedMessage());
            throw ex;
        }
        model.addAttribute(CRYPTO_LIST, getCryptoCurrencyList());
    }

    private List<String> getCryptoCurrencyList() {

        List cryptoList = new ArrayList<>();

        cryptoList.add("ABC");
        cryptoList.add("BCH");
        cryptoList.add("BNB");
        cryptoList.add("BTC");
        cryptoList.add("CLOAK");
        cryptoList.add("DGD");
        cryptoList.add("DSH");
        cryptoList.add("GMX");
        cryptoList.add("GNO");
        cryptoList.add("GRWI");
        cryptoList.add("ICOS");
        cryptoList.add("MKR");
        cryptoList.add("PBT");
        cryptoList.add("REP");
        cryptoList.add("VERI");
        cryptoList.add("XRB");

        return cryptoList;
    }
}
