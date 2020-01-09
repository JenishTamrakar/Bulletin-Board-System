package bll;

public class ForexResponse {


    private Conversion Conversion;
    //getter method for conversion data
    public Conversion getConversion ()
    {
        return Conversion;
    }

    //setter method for conversion data
    public void setConversion (Conversion Conversion)
    {
        this.Conversion = Conversion;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "ClassPojo [Conversion = "+Conversion+"]";
    }
}
