package dao;

import bll.Faculty;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FacultyDao extends Remote
{
    void addStudent(Faculty f) throws RemoteException;
}
