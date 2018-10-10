package hello.startup;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import hello.business.cryptoservices.ICryptoService;
import hello.core.ICryptoPlatform;
import hello.core.TrackingContext;
import hello.domain.CryptoCurrency;
import hello.domain.CryptoCurrencyPrice;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private  Logger _logger = LoggerFactory.getLogger(AppStartupRunner.class);
  
    @Autowired
    ICryptoService _cryptoService;
    @Autowired 
    ICryptoPlatform _platform;
    @Autowired
    SimpMessagingTemplate brokerMessagingTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        _logger.info("Application started with option names : {}", args.getOptionNames());

        _cryptoService.initialize(true);

        while(true){

            _logger.info("Starting ---------------");
            
            doWork();

            _logger.info("done, now sleeping...");

            TimeUnit.SECONDS.sleep(10);

            _logger.info("Finished ----------------");
        }
    }

    private void doWork(){
        try {
            _logger.info("Fetching data from binance");
            List<CryptoCurrencyPrice> prices = _platform.getPrices();
            _logger.info("Finished reading from the api");

            //_logger.info("Finished Getting api call");
            _cryptoService.loadPrices(prices);
            _logger.info("Finished inserting prices");
            

            TrackingContext.printPercentageToAll(this.brokerMessagingTemplate);
            //_logger.info("Finished interval of 10 seconds");

           } catch (Exception e) {
            _logger.error( "Fata error parsing data from binance" , e);
            
        }
    }
}