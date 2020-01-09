/**
 * @author Susan Tamrakar
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package dao;

import bll.CreateEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CreateEventDao extends Remote {
    void addEvent(CreateEvent ce) throws RemoteException;
}
