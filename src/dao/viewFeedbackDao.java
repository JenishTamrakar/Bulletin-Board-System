/**
 * @author Rashim Joshi
 * This is the interface to add feedback details and retrieve them.
 */

package dao;

import bll.viewFeedback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface viewFeedbackDao extends Remote {
    void addFeedback(viewFeedback vf) throws RemoteException;
    ResultSet getFeedback()throws RemoteException;

}
