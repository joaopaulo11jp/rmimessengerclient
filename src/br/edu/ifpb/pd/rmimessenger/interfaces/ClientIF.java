package br.edu.ifpb.pd.rmimessenger.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.swing.JTextArea;

public interface ClientIF extends Remote{
	public String getName() throws RemoteException;
	public void setName(String name) throws RemoteException;
	
	public void message(String msg) throws RemoteException;
	
	public void setUserList(Collection<String> users) throws RemoteException;
}
