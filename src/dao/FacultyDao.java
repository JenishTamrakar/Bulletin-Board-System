package dao;

import bll.Faculty;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface FacultyDao extends Remote
{
    void addFaculty(Faculty f) throws RemoteException;
    void updateFaculty(Faculty f) throws RemoteException;
    void deleteFaculty(Faculty f) throws RemoteException;
    ResultSet getFacultyRecords() throws RemoteException;
    ResultSet getProfile(String user_id) throws RemoteException;
    ResultSet getFacultyRecordsByCourse() throws RemoteException;
}
