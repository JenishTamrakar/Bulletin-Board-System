/**
 * @author Roshan Shrestha
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

public class Conversion {

    //array for storing currency data
    private Currency[] Currency;

    //getter method for currency
    public Currency[] getCurrency ()
    {
        return Currency;
    }

    //setter method for currency
    public void setCurrency (Currency[] Currency)
    {
        this.Currency = Currency;
    }

    /**
     * return currency value in String from int
     * @return
     */
    @Override
    public String toString()
    {
        return "ClassPojo [Currency = "+Currency+"]";
    }
}
