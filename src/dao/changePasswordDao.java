/**
 * @author Rashim Joshi
 * This is the interface for updating the user password.
 */

package dao;

import bll.changePassword;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface changePasswordDao extends Remote {
    void updatePassword(changePassword cp)throws RemoteException;
}
