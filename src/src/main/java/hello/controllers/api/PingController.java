package hello.controllers.api;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hello.business.cryptoservices.ICryptoService;
import hello.controllers.api.models.Response;


@RestController
public class PingController {

    @Autowired
    private ICryptoService _cryptoService;
   
    @RequestMapping("/ping")
    public Response  ping() {
        return new Response(true, String.format("%s", Calendar.getInstance().getTime()));
    }
}