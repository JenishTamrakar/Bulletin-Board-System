/**
 * @author Roshan Shrestha
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

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
