package BBS.dao;
import BBS.bll.Register;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RegisterDao extends Remote {
    void addStudent(Register r) throws RemoteException;
}
