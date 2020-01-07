package bll;

import java.io.Serializable;

public class Register implements Serializable {
    private String UID;
    private String password;
    private String userType;
    private static final long serialVersionUID = 1L;
    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
