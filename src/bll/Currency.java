package bll;

public class Currency {
    //private data member
    private String TargetBuy;
    private String TargetSell;
    private String BaseValue;
    private String BaseCurrency;
    private String Date;
    private String TargetCurrency;

    //getter method for target buy
    public String getTargetBuy ()
    {
        return TargetBuy;
    }

    //setter method for target buy
    public void setTargetBuy (String TargetBuy)
    {
        this.TargetBuy = TargetBuy;
    }

    //getter method for target sell
    public String getTargetSell ()
    {
        return TargetSell;
    }

    //setter method for target sell
    public void setTargetSell (String TargetSell)
    {
        this.TargetSell = TargetSell;
    }

    //getter method for base value
    public String getBaseValue ()
    {
        return BaseValue;
    }

    //setter method for base value
    public void setBaseValue (String BaseValue)
    {
        this.BaseValue = BaseValue;
    }

    //getter method for base currency
    public String getBaseCurrency ()
    {
        return BaseCurrency;
    }

    //setter method for base currency
    public void setBaseCurrency (String BaseCurrency)
    {
        this.BaseCurrency = BaseCurrency;
    }

    //getter method for date
    public String getDate ()
    {
        return Date;
    }

    //setter method for date
    public void setDate (String Date)
    {
        this.Date = Date;
    }

    //getter method for target currency
    public String getTargetCurrency ()
    {
        return TargetCurrency;
    }

    //setter method for target currency
    public void setTargetCurrency (String TargetCurrency)
    {
        this.TargetCurrency = TargetCurrency;
    }

    /**
     * return all the data member
     * @return
     */
    @Override
    public String toString()
    {
        return "ClassPojo [TargetBuy = "+TargetBuy+", TargetSell = "+TargetSell+", BaseValue = "+BaseValue+", BaseCurrency = "+BaseCurrency+", Date = "+Date+", TargetCurrency = "+TargetCurrency+"]";
    }
}
