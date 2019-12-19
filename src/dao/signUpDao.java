package dao;

import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface signUpDao {
    ResultSet checkID(String user_id)throws RemoteException;
}
