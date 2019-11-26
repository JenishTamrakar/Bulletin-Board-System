package BBS.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginRegister extends Remote {

    public void login(int uID, String password) throws RemoteException;

    public void register(int uID, String password, String confirmPassword) throws RemoteException;
}
