package dao;

import bll.Notice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NoticeDao extends Remote {
    void addNotice(Notice n)throws RemoteException;
}
