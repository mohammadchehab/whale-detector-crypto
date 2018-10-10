package hello.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import hello.business.cryptoservices.ICryptoService;
import hello.controllers.api.models.Response;
import hello.domain.CryptoCurrency;
import hello.domain.CryptoCurrencyPrice;
import hello.models.PriceExcelRow;

@Controller
public class ScafoldingController{

    @Autowired
    private ICryptoService _cryptoService;

    /*
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public CryptoCurrency greeting(Response message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new CryptoCurrency("key", "value");
    }
    */
    
	@GetMapping("/export")
	public void export(HttpServletResponse response) {

        List<CryptoCurrency> currencylist = _cryptoService.getList();
        
		String[] headers = new String [] {"Currency Name", "Code", "Price USD", "Percentage Change", "Date"};
        
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + Calendar.getInstance().getTime() + ".csv\"");
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

            csvWriter.writeHeader(headers);
            
            for (CryptoCurrency currency : currencylist) {
                for (CryptoCurrencyPrice price : _cryptoService.getPricesOfCurrency(currency)) {
                    csvWriter.write(
                            new PriceExcelRow(currency.getId(), currency.getName(), price.getPrice(),
                                    price.getPercentageChange(), price.getDate()),
                            new String[] { "currencyCode", "currencyName", "price", "percentage", "date" });
                }
            }
            
            csvWriter.close();

        } catch (IOException e) {

        }
	}
}