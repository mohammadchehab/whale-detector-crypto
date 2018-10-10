package hello.business.chat;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class SlackService  implements IChatService{

    private final String _apiToken = "xoxp-350852130757-351764342455-350855950757-15f737eb2c459091dd4b46075f6c30c4";
    private final String _channel = "whaledetector";
    private final String _webhook = "https://hooks.slack.com/services/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";
    public SlackService() {}

    public void Send(String message){
        try{

            String query = String.format("token=%s&channel=%s&text=%s", 
            this._apiToken, 
            this._channel,
             message);

            Unirest.post("https://slack.com/api/chat.postMessage")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .body(query)
            .asString();
            

        } catch(UnirestException exception){

        } 
    }
}