package hello.business.cryptoservices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import hello.core.CurrencyTracker;
import hello.core.TrackingContext;
import hello.data.CryptoCurrencyPriceRepository;
import hello.data.CryptoCurrencyRepository;
import hello.data.ISequenceService;
import hello.domain.CryptoCurrency;
import hello.domain.CryptoCurrencyPrice;

@Service
public class CryptoService implements ICryptoService{

    @Autowired
	private CryptoCurrencyRepository _cryptoCurrencyRepository;

	@Autowired
    private CryptoCurrencyPriceRepository _cryptoCurrencyPriceRepository;
    
    @Autowired
    private ISequenceService _sequenceService;

	@Override
	public void initialize(boolean forceRecreate) {

        if(forceRecreate)
        {
            _cryptoCurrencyPriceRepository.deleteAll();
            _cryptoCurrencyRepository.deleteAll();
            _sequenceService.reset();
        }
 
        if(_cryptoCurrencyRepository.findAll().size() > 0){
            TrackingContext.initialize(_cryptoCurrencyRepository);
            return;
        }

        _cryptoCurrencyRepository.insert(new ArrayList<CryptoCurrency>() {
            private static final long serialVersionUID = 1L;
		{
      
            add(new CryptoCurrency("TRX","Tron"));
            add(new CryptoCurrency("ADA","Cardano"));
            add(new CryptoCurrency("XLM","Stellar Lumens"));
            add(new CryptoCurrency("VEN","VeChain"));
            add(new CryptoCurrency("OMG","OmiseGO"));
            add(new CryptoCurrency("ETH","Ethereum"));
            add(new CryptoCurrency("NEO","NEO"));
            add(new CryptoCurrency("ONT","Ontology"));
            add(new CryptoCurrency("XRP","Ripple"));
            add(new CryptoCurrency("GAS","NeoGas"));
            add(new CryptoCurrency("BTC","Bitcoin"));
            add(new CryptoCurrency("BCPT","BlockMason Credit Protocol"));
            add(new CryptoCurrency("HSR","Hshare"));
            add(new CryptoCurrency("OAX","openANX"));
            add(new CryptoCurrency("DNT","district0x"));
            add(new CryptoCurrency("MCO","MONACO"));
            add(new CryptoCurrency("ICN","ICONOMI"));
            add(new CryptoCurrency("ZRX","0x"));
            add(new CryptoCurrency("LTC","Litecoin"));
            add(new CryptoCurrency("WTC","Walton"));
            add(new CryptoCurrency("LRC","Loopring"));
            add(new CryptoCurrency("YOYO","YOYOW"));
            add(new CryptoCurrency("QTUM","Qtum"));
            add(new CryptoCurrency("STRAT","Stratis"));
            add(new CryptoCurrency("SNGLS","SingularDTV"));
            add(new CryptoCurrency("KNC","KyberNetwork"));
            add(new CryptoCurrency("BQX","ETHOS"));
            add(new CryptoCurrency("SNM","SONM"));
            add(new CryptoCurrency("FUN","FunFair"));
            add(new CryptoCurrency("LINK","ChainLink"));
            add(new CryptoCurrency("EOS","EOS"));
            add(new CryptoCurrency("MDA","Moeda Loyalty Points"));
            add(new CryptoCurrency("IOTA","MIOTA"));
            add(new CryptoCurrency("SUB","Substratum"));
            add(new CryptoCurrency("ETC","Ehereum Classic"));
            add(new CryptoCurrency("MTL","Metal"));
            add(new CryptoCurrency("MTH","Monetha"));
            add(new CryptoCurrency("ENG","Enigma"));
            add(new CryptoCurrency("AST","AirSwap"));
            add(new CryptoCurrency("DASH","Dash"));
            add(new CryptoCurrency("BTG","Bitcoin Gold"));
            add(new CryptoCurrency("EVX","Everex"));
            add(new CryptoCurrency("REQ","Request Network"));
            add(new CryptoCurrency("VIB","Viberate"));
            add(new CryptoCurrency("POWR","PowerLedger"));
            add(new CryptoCurrency("ARK","Ark"));
            add(new CryptoCurrency("SNT","Status"));
            add(new CryptoCurrency("MOD","Modum"));
            add(new CryptoCurrency("ENJ","EnjinCoin"));
            add(new CryptoCurrency("STORJ","Storj"));
            add(new CryptoCurrency("BNT","Bancor"));
            add(new CryptoCurrency("KMD","Komodo"));
            add(new CryptoCurrency("RCN","Ripio Credit Network"));
            add(new CryptoCurrency("NULS","Nuls"));
            add(new CryptoCurrency("RDN","Raiden Network Token"));
            add(new CryptoCurrency("XMR","Monero"));
            add(new CryptoCurrency("DLT","Agrello"));
            add(new CryptoCurrency("AMB","Ambrosus"));
            add(new CryptoCurrency("BAT","Basic Attention Token"));
            add(new CryptoCurrency("ZEC","Zcash"));
            add(new CryptoCurrency("ARN","Aeron"));
            add(new CryptoCurrency("GVT","Genesis Vision"));
            add(new CryptoCurrency("CDT","Blox"));
            add(new CryptoCurrency("GXS","Gxshares"));
            add(new CryptoCurrency("POE","Po.et"));
            add(new CryptoCurrency("QSP","Quantstamp"));
            add(new CryptoCurrency("BTS","BitShares"));
            add(new CryptoCurrency("LSK","Lisk"));
            add(new CryptoCurrency("XZC","Zcoin"));
            add(new CryptoCurrency("TNT","Tierion"));
            add(new CryptoCurrency("FUEL","Etherparty"));
            add(new CryptoCurrency("MANA","Decentraland"));
            add(new CryptoCurrency("BCD","Bitcoin Diamond"));
            add(new CryptoCurrency("DGD","DigxDAO"));
            add(new CryptoCurrency("ADX","AdEx"));
            add(new CryptoCurrency("BCC","Bitcoin Cash"));
            add(new CryptoCurrency("PPT","Populous"));
            add(new CryptoCurrency("CMT","CyberMiles"));
            add(new CryptoCurrency("CND","Cindicator"));
            add(new CryptoCurrency("LEND","EthLend"));
            add(new CryptoCurrency("WABI","WaBi"));
            add(new CryptoCurrency("SBTC","Super Bitcoin"));
            add(new CryptoCurrency("BCX","BitcoinX"));
            add(new CryptoCurrency("WAVES","Waves"));
            add(new CryptoCurrency("TNB","Time New Bank"));
            add(new CryptoCurrency("GTO","Gifto"));
            add(new CryptoCurrency("ICX","ICON"));
            add(new CryptoCurrency("OST","OST"));
            add(new CryptoCurrency("ELF","aelf"));
            add(new CryptoCurrency("AION","AION"));
            add(new CryptoCurrency("ZIL","Zilliqa"));
            add(new CryptoCurrency("LUN","Lunyr"));
            add(new CryptoCurrency("NCASH","Nucleus Vision"));
            add(new CryptoCurrency("SYS","Syscoin"));
            add(new CryptoCurrency("BRD","Bread"));
            add(new CryptoCurrency("VIA","Viacoin"));
            add(new CryptoCurrency("NANO","NANO"));
            add(new CryptoCurrency("CLOAK","CloakCoin"));
            add(new CryptoCurrency("IOST","IOStoken"));
            add(new CryptoCurrency("QLC","QLINK"));
            add(new CryptoCurrency("INS","INS Ecosystem"));
            add(new CryptoCurrency("WAN","Wancoin"));
            add(new CryptoCurrency("CHAT","ChatCoin"));
            add(new CryptoCurrency("STORM","Storm"));
            add(new CryptoCurrency("POA","POA Network"));
            add(new CryptoCurrency("VIBE","VIBE"));
            add(new CryptoCurrency("RPX","Red Pulse"));
            add(new CryptoCurrency("NEBL","Nebilo"));
            add(new CryptoCurrency("BLZ","Bluzelle"));
            add(new CryptoCurrency("AE","Aeternity"));
            add(new CryptoCurrency("STEEM","Steem"));
            add(new CryptoCurrency("GRS","Groestlcoin"));
            add(new CryptoCurrency("WPR","WePower"));
            add(new CryptoCurrency("RLC","iExecRLC"));
            add(new CryptoCurrency("XEM","NEM"));
            add(new CryptoCurrency("EDO","Eidoo"));
            add(new CryptoCurrency("NAV","NAV Coin"));
            add(new CryptoCurrency("WINGS","WINGS"));
            add(new CryptoCurrency("TRIG","Triggers"));
            add(new CryptoCurrency("APPC","AppCoins"));
            add(new CryptoCurrency("PIVX","PIVX"));    
        }});

        TrackingContext.initialize(_cryptoCurrencyRepository);
	}

    public List<String> getCurrencyCodesByParts(int pageSize){
        List<String> result = new ArrayList<String>();
        List<String> set =  _cryptoCurrencyRepository.findAll()
        .stream()
        .map(x-> x.getId())
        .collect(Collectors.toList());
        
        int total = set.size();
        int iteration = (int)Math.ceil( total / pageSize );

        for (int i = 0; i <= iteration; i++) {
            try {
                int next = ((pageSize * i) + pageSize);

                String str = set.subList(i * pageSize, pageSize > total ? total : next > total ? (total) : next)
                        .stream().collect(Collectors.joining(","));
                result.add(str);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
       
        return result;
    }

	@Override
	public CryptoCurrency getByKey(String key) {
        Optional<CryptoCurrency> optional = this._cryptoCurrencyRepository.findById(key);
        return optional.isPresent() ?  optional.get() :  null;
  	}

    @Override
	public void loadPrices(List<CryptoCurrencyPrice> list) {
		list.forEach((price)->{
            CurrencyTracker tracker = TrackingContext.getTrackers()
            .stream()
            .filter(p -> p.getCurrency().getId().equalsIgnoreCase(price.getCurrencyId()))
            .findAny()
            .orElse(null);

            if(tracker == null)
                return;
            
            tracker.sniff(price.getPrice());  
             
            price.setId(_sequenceService.getNextSequence("CryptoCurrencyPrice"));
            price.setPercentageChange(tracker.getTheLatestPercentage());
            _cryptoCurrencyPriceRepository.insert(price);
        });
	}

	@Override
	public List<CryptoCurrency> getAllCurrencies() {
        return _cryptoCurrencyRepository.findAll();
	}

	@Override
	public List<CryptoCurrency> getList() {
       
        return _cryptoCurrencyRepository.getAllCurrenciesWithoutPrices();
	}

	@Override
	public List<CryptoCurrencyPrice> getPricesOfCurrency(CryptoCurrency currency) {
		return _cryptoCurrencyPriceRepository.findByCurrency( currency.getId());
	}
}