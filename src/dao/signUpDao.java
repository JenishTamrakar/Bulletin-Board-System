/**
 * @author Roshan Shrestha
 * This is the interface for checking the user credentials for registering the user.
 */

package dao;

import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface signUpDao {
    ResultSet checkID(String user_id)throws RemoteException;
}
