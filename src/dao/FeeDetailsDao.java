package dao;

import bll.FeeDetails;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FeeDetailsDao extends Remote
{
        void addFeeDet(FeeDetails fd) throws RemoteException;
}
