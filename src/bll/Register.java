package bll;

import java.io.Serializable;

public class Register implements Serializable {
    //private data member
    private String UID;
    private String password;
    private String userType;
    private static final long serialVersionUID = 1L;

    //getter method for user ID
    public String getUID() { return UID; }

    //setter method for user ID
    public void setUID(String UID) {
        this.UID = UID;
    }

    //getter method for user type
    public String getUserType() {
        return userType;
    }

    //setter method for user type
    public void setUserType(String userType) {
        this.userType = userType;
    }

    //setter method for user password
    public String getPassword() {
        return password;
    }

    //getter method for user password
    public void setPassword(String password) {
        this.password = password;
    }

}
