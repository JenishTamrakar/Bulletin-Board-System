/**
 * @author Roshan Shrestha
 * This is the interface for checking the user credentials for the login to the system.
 */

package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface LoginDao extends Remote {
    ResultSet checkUser(String user_id, String user_password) throws RemoteException;
}
