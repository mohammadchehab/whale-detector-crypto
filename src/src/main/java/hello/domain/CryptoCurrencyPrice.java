package hello.domain;
import java.util.Date;
import org.springframework.data.annotation.Id;

public class CryptoCurrencyPrice {
   
    @Id
    private int _id;
    private double _price;
    private Date _date;
    private double _percentageChange;
    private String _currencyId;
    
    
    public CryptoCurrencyPrice() {}

    public CryptoCurrencyPrice(CryptoCurrency currency, double price, Date date){
        this._price = price;
        this._date = date;
        this._currencyId = currency.getId();
    }

    public double getPrice() {
        return _price;
    }
    
    public void setPrice(double _price) {
        this._price = _price;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }

    public double getPercentageChange() {
        return _percentageChange;
    }

    public void setPercentageChange(double _percentageChange) {
        this._percentageChange = _percentageChange;
    }

	public void setId(int id) {
        this._id = id;
	}

	public String getCurrencyId() {
		return _currencyId;
	}

	public void setCurrencyId(String _currencyId) {
		this._currencyId = _currencyId;
	}
   
}