package dao;

import bll.Assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AssignmentDao extends Remote
{
    void addAssDet(Assignment as) throws RemoteException;
}
