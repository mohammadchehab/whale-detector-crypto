package hello.core;

import java.util.List;
import hello.domain.CryptoCurrencyPrice;

public interface ICryptoPlatform {
    
    List<CryptoCurrencyPrice> getPrices();
}