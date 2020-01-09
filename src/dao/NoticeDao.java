/**
 * @author Jenish Tamrakar
 * This is the interface for adding, updating and deleting the notice details as well as retrieve them.
 */

package dao;

import bll.Notice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface NoticeDao extends Remote {
    void addNotice(Notice n)throws RemoteException;
    void updateNotice(Notice n) throws RemoteException;
    void deleteNotice(Notice n) throws RemoteException;
    ResultSet getNoticeDetails() throws RemoteException;
}
