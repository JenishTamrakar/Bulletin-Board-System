package dao;

import bll.FeeDetails;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface FeeDetailsDao extends Remote
{
        void addFeeDet(FeeDetails fd) throws RemoteException;
        void updateFeeDet(FeeDetails fd) throws RemoteException;
        void deleteFeeDet(FeeDetails fd) throws RemoteException;
        ResultSet getFeeDetails() throws RemoteException;
        ResultSet getProfile(String user_id) throws RemoteException;
        ResultSet getFeeDetailsByCourse(String course_name) throws RemoteException;
}
