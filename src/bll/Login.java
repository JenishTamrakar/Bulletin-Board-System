package bll;

import java.io.Serializable;

public class Login implements Serializable {
    private String user_id;
    private  String user_password;
    private static final long serialVersionUID = 1L;
    public String getUser_id(){
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String password) {

        this.user_password = password;
    }
}
