/**
 * @author Roshan Shrestha
 * This is interface for adding new user to the table and update the user password.
 */

package dao;
import bll.Register;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;


public interface RegisterDao extends Remote {
    void addUser(Register r) throws RemoteException;
    void updatePassword(String user_id, String user_password) throws RemoteException;
    ResultSet getUser(String user_id) throws RemoteException;

}
