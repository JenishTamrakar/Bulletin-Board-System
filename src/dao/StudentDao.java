/**
 * @author Rashim Joshi
 * This is the interface for adding, updating and deleting the student details as well as retrieve them.
 */

package dao;

import bll.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface StudentDao extends Remote
{
    void addStudent(Student s) throws RemoteException;
    void updateStudent(Student s) throws RemoteException;
    void deleteStudent(Student s) throws RemoteException;
    ResultSet getStudentRecords() throws RemoteException;
    ResultSet getProfile(String user_id) throws RemoteException;
    ResultSet getStudentRecordsByCourse() throws RemoteException;
    ResultSet getStudentRecordsByLevel() throws RemoteException;
}
