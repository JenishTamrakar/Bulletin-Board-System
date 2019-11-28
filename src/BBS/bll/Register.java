package BBS.bll;

import java.io.Serializable;

public class Register implements Serializable {
    private String UID;
    private String password;

  public  String getUID(){
      return UID;
  }
    public void setUID(String UID) {
        this.UID = UID;
    }

  public String getPassword(String  password){

      return  password;
  }
  public void  setPassword(String password){

      this.password = password;
  }


}
