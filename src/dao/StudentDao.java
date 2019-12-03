package dao;

import bll.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentDao extends Remote
{
    void addStudent(Student s) throws RemoteException;
}
