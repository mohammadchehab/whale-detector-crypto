package hello.business.plugins;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hello.business.cryptoservices.ICryptoService;
import hello.core.ICryptoPlatform;
import hello.domain.CryptoCurrency;
import hello.domain.CryptoCurrencyPrice;

@Service
public class Binance implements ICryptoPlatform{

    private String _base = "https://api.binance.com/"; 
    private double _basePrice = 0;
    private  Logger _logger = LoggerFactory.getLogger(Binance.class);
     
    @Autowired
    ICryptoService _cryptoService;
  
	@Override
	public List<CryptoCurrencyPrice> getPrices() {
         
        List<CryptoCurrencyPrice> result = new ArrayList<CryptoCurrencyPrice>();
        List<CryptoCurrency> allCurrencies = _cryptoService.getList();

        _basePrice = 0;

        try{
            HttpResponse<String> response = Unirest.get(this._base + "api/v3/ticker/price")
            .asString();

            //_logger.info("Finished Calling the API. Started Parsing");

            Date date = Calendar.getInstance().getTime();

            String jsonString = response.getBody().toString();
            JSONArray payload = new JSONArray(jsonString);
           
            for(int i = 0; i< payload.length(); i++){
                JSONObject o = payload.getJSONObject(i);
                
                String symbol = o.getString("symbol");
                double value = o.getDouble("price");

                if(symbol.equals("ETHUSDT"))
                    _basePrice = value;

                if(symbol.endsWith("ETH") || symbol.equals("ETHUSDT")){
                
                    String _symbol = symbol.equals("ETHUSDT") ? symbol.replace("USDT", "") : symbol.replace("ETH","");

                   // _logger.info("Quering the database for {}", _symbol);

                    CryptoCurrency currency = allCurrencies
                            .stream()
                            .filter(p -> p.getId().equalsIgnoreCase(_symbol))
                            .findAny()
                            .orElse(null);
                    
                    //_logger.info("Finished Querying the database for {}", _symbol);

                    if(currency == null)
                        continue;
                    
                    result.add(new CryptoCurrencyPrice(currency, value, date));
                    continue;
                }

                if(symbol.equals("BTCUSDT")){
                    CryptoCurrency currency = allCurrencies
                    .stream()
                    .filter(p -> p.getId().equalsIgnoreCase("BTC"))
                    .findAny()
                    .orElse(null);
                    
                    if(currency == null)
                        continue;

                    result.add(new CryptoCurrencyPrice(currency, value, date));
                
                    continue;
                }

                if(symbol.equals("GASBTC")){
                    
                    CryptoCurrency currency = allCurrencies
                    .stream()
                    .filter(p -> p.getId().equalsIgnoreCase("GAS"))
                    .findAny()
                    .orElse(null);
                    

                    if(currency == null)
                        continue;
                        
                    result.add(new CryptoCurrencyPrice(currency, value, date));
                
                    continue;
                }

            }
            
            result.forEach((currency)->{
                if(!currency.getCurrencyId().equals("ETH") && !currency.getCurrencyId().equals("BTC")){
                    double price = currency.getPrice() * _basePrice;
                    currency.setPrice(price);
                }
            });

        }
        catch(UnirestException e){
            return null;
        }

       // _logger.info("Parsing coins is done");

        return result;
    }
    
}