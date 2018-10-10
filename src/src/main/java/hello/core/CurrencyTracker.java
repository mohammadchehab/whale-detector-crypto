package hello.core;

import java.awt.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import hello.domain.CryptoCurrency;


public class CurrencyTracker {

    private final int NUMBER_PRECISION = 2;
    private final int _leadSniffsBeforeFirstBaseline = 6;
    private final CryptoCurrency _currency;

    // represents a sniffer or a tracker.
    private int _sniffer;

    // tracking the latest fields
    private double _currentPrice;
    private double _currentBaseline;
    private double _currentPercentage;

    // queues
    private IQueue<Double> _priceQueue = new QueueArray<Double>(_leadSniffsBeforeFirstBaseline);
    private IQueue<Double> _percentageQueue = new QueueArray<Double>(_leadSniffsBeforeFirstBaseline);
    

    public CurrencyTracker(CryptoCurrency currency) {
        this._sniffer = 0;
        this._currency = currency;
    }

    private double calculatePercentage( double price, double baseline ) {

        if(price == 0 || baseline == 0)
            return 0;

        double value = (baseline - price) / price;
        double percentage = (value * 100);
        return percentage;
    }

    public double getCurrentPrice (){
        return this.setPricision( this._currentPrice, 4);
    }

    private double setPricision( double value, int precision ) {

        Double toBeTruncated = new Double(value);
        Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
            .setScale(precision, RoundingMode.HALF_UP)
            .doubleValue();

        return truncatedDouble;
    }

    public double getTheLatestPercentage (){
       
        double percentage = this._currentPercentage;

        return this.setPricision(percentage, NUMBER_PRECISION);
    }

    public void sniff(double price) {

        this._priceQueue.enqueue(price);

        this._currentPrice = price;

        if (this._sniffer == _leadSniffsBeforeFirstBaseline - 1) {
            
            double headPrice = this._priceQueue.dequeue();
            this._currentBaseline = price;
            this._currentPercentage = this.calculatePercentage(headPrice, this._currentBaseline);
            this._percentageQueue.enqueue(this._currentPercentage);     

            if(this._percentageQueue.size() == _leadSniffsBeforeFirstBaseline)
                this._percentageQueue.dequeue();

            this._sniffer --;
         }
        
        this._sniffer++;
    }

	public CryptoCurrency getCurrency() {
		return this._currency;
	}

	public Double [] getTheLastFivePercentages() {
        Object [] tmp = this._percentageQueue.toArray();
        ArrayList<Double> result = new ArrayList<Double>();
        
        for(int i =0; i< tmp.length; i++){
            if(tmp[i] != null){
                result.add((double)tmp[i] );
            }
        }

        return result.toArray(new Double[ result.size() ]);
	}
}