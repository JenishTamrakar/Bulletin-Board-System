package bll;

public class ForexResponse {
    private Conversion Conversion;

    public Conversion getConversion ()
    {
        return Conversion;
    }

    public void setConversion (Conversion Conversion)
    {
        this.Conversion = Conversion;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Conversion = "+Conversion+"]";
    }
}
