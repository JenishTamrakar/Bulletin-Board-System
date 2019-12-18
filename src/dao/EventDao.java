package dao;

import bll.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface EventDao extends Remote
{
    void addEventDet(Event ev) throws RemoteException;
    void updateEventDet(Event ev) throws RemoteException;
    void deleteEventDet(Event ev) throws RemoteException;
    ResultSet getEventDetails() throws RemoteException;
}
