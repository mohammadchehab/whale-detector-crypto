package hello.models;


public class PriceSocketModel extends PriceExcelRow {

    private Double [] percentages;

    public PriceSocketModel(String currencyCode, 
    String currencyName,
    double price,
    double percentage,
    Double [] percentages ){
        super(currencyCode, currencyName, price, percentage, null);
        this.percentages = percentages;
    }

	/**
	 * @return the percentages
	 */
	public Double[] getPercentages() {
		return percentages;
	}

	/**
	 * @param percentages the percentages to set
	 */
	public void setPercentages(Double[] percentages) {
		this.percentages = percentages;
	}
}