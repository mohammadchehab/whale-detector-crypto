package hello.business.cryptoservices;

import java.util.List;

import hello.domain.CryptoCurrency;
import hello.domain.CryptoCurrencyPrice;

public interface ICryptoService {
    
    void initialize(boolean forceRecreate);

    CryptoCurrency getByKey(String key);

    void loadPrices(List<CryptoCurrencyPrice> list);

    List<String> getCurrencyCodesByParts(int pageSize);

	List<CryptoCurrency> getAllCurrencies();

    List<CryptoCurrency> getList();

	List<CryptoCurrencyPrice> getPricesOfCurrency(CryptoCurrency currency);
}