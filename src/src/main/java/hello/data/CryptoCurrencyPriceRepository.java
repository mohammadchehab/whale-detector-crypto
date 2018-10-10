package hello.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import hello.domain.CryptoCurrencyPrice;

public interface CryptoCurrencyPriceRepository extends MongoRepository<CryptoCurrencyPrice,String>{

    @Query(value = "{'_currencyId': ?0}")
    List<CryptoCurrencyPrice> findByCurrency(String currencyId);
}