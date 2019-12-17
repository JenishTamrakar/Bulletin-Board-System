package dao;

import bll.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EventDao extends Remote
{
    void addEventDet(Event ev) throws RemoteException;
}
