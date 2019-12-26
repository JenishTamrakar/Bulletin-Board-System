package bll;

public class Currency {
    private String TargetBuy;

    private String TargetSell;

    private String BaseValue;

    private String BaseCurrency;

    private String Date;

    private String TargetCurrency;

    public String getTargetBuy ()
    {
        return TargetBuy;
    }

    public void setTargetBuy (String TargetBuy)
    {
        this.TargetBuy = TargetBuy;
    }

    public String getTargetSell ()
    {
        return TargetSell;
    }

    public void setTargetSell (String TargetSell)
    {
        this.TargetSell = TargetSell;
    }

    public String getBaseValue ()
    {
        return BaseValue;
    }

    public void setBaseValue (String BaseValue)
    {
        this.BaseValue = BaseValue;
    }

    public String getBaseCurrency ()
    {
        return BaseCurrency;
    }

    public void setBaseCurrency (String BaseCurrency)
    {
        this.BaseCurrency = BaseCurrency;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public String getTargetCurrency ()
    {
        return TargetCurrency;
    }

    public void setTargetCurrency (String TargetCurrency)
    {
        this.TargetCurrency = TargetCurrency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TargetBuy = "+TargetBuy+", TargetSell = "+TargetSell+", BaseValue = "+BaseValue+", BaseCurrency = "+BaseCurrency+", Date = "+Date+", TargetCurrency = "+TargetCurrency+"]";
    }
}
