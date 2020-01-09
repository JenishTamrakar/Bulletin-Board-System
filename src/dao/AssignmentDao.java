/**
 * @author Jenish Tamrakar
 * This is the interface for adding, updating and deleting the assignment details and retrieve assignment details.
 */

package dao;

import bll.Assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface AssignmentDao extends Remote
{
    void addAssDet(Assignment as) throws RemoteException;
    void updateAssDet(Assignment as) throws RemoteException;
    void deleteAssDet(Assignment as) throws RemoteException;
    ResultSet getAssignDetails() throws RemoteException;
    ResultSet getAssignDetailsByCourseAndLevel(String course, String level) throws RemoteException;
}
