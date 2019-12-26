package bll;

public class Conversion {
    private Currency[] Currency;

    public Currency[] getCurrency ()
    {
        return Currency;
    }

    public void setCurrency (Currency[] Currency)
    {
        this.Currency = Currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Currency = "+Currency+"]";
    }
}
