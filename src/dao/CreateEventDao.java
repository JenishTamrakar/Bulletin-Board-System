package dao;

import bll.CreateEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CreateEventDao extends Remote {
    void addEvent(CreateEvent ce) throws RemoteException;
}
