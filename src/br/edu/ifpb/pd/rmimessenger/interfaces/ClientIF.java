package br.edu.ifpb.pd.rmimessenger.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote{
	public String getName() throws RemoteException;
	public void setName(String name) throws RemoteException;
}
