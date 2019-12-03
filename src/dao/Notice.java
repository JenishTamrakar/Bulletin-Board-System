package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Notice extends Remote {
    void addNotice(Notice n)throws RemoteException;
}
