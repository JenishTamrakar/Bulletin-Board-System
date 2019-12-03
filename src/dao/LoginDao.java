package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginDao extends Remote {
    Boolean checkUser(String user_id, String user_password) throws RemoteException;
}
