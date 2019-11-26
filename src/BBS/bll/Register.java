package BBS.bll;

public class Register {
    private int UID;
    private String password;

    public Register() {}

    public Register(int UID, String password)
    {
        this.UID = UID;
        this.password = password;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
