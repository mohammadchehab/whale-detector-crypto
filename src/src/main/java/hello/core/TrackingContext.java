package hello.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import hello.business.chat.SlackService;
import hello.data.CryptoCurrencyRepository;
import hello.models.PriceExcelRow;
import hello.models.PriceSocketModel;

public class TrackingContext {

    private static List<CurrencyTracker> _trackers;
    private static Logger _logger =  LoggerFactory.getLogger("");

    public static List<CurrencyTracker> getTrackers (){

        return _trackers;
    }

    public static void initialize(CryptoCurrencyRepository _repository){
        _trackers = new ArrayList<CurrencyTracker>();
        _repository
            .findAll()
            .forEach((currency)->{
                _trackers.add(new CurrencyTracker(currency));
            });
    }

	public static void printPercentageToAll(SimpMessagingTemplate brokerMessagingTemplate) {
       
        _trackers
        .forEach((tracker)->{

            brokerMessagingTemplate.convertAndSend("/topic/greetings", 
            new  PriceSocketModel(tracker.getCurrency().getId(), 
            tracker.getCurrency().getName(), 
            tracker.getCurrentPrice(), 
            tracker.getTheLatestPercentage(),
            tracker.getTheLastFivePercentages()));


            if(tracker.getTheLatestPercentage() > 3){
                String message = "Price of %s is %s $ percent change is %s %% Time : (%s) for more info %s";
                String currencyCode = tracker.getCurrency().getId();

                new SlackService()
                .Send(String.format( message, 
                    tracker.getCurrency().getName(), 
                    tracker.getCurrentPrice(),
                    tracker.getTheLatestPercentage(),
                    Calendar.getInstance().getTime(),
                    "https://www.binance.com/trade.html?symbol=" + currencyCode + "_ETH https://www.cryptocompare.com/coins/" + currencyCode + "/overview"
                  )
                );
            }
        });
	}
}