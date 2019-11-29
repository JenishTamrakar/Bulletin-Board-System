package dao;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginDao extends Remote, Serializable {
    Boolean checkUser(String user_id, String user_password) throws RemoteException;
}
