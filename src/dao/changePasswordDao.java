package dao;

import bll.changePassword;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface changePasswordDao extends Remote {
    void updatePassword(changePassword cp)throws RemoteException;
}
