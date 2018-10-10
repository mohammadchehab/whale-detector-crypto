package hello.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import hello.domain.CryptoCurrency;


public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency,String>{ 

    @Query(value="{}",  fields= "{'_id':1, '_name': 1}" )
    List<CryptoCurrency> getAllCurrenciesWithoutPrices();
}