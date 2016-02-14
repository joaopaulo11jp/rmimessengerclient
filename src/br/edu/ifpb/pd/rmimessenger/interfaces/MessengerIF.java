package br.edu.ifpb.pd.rmimessenger.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerIF extends Remote{
	public void joinMessenger(ClientIF client) throws RemoteException;
	public void sendPublicMessage(String msg) throws RemoteException;
	public void sendPrivateMessage(String user,String msg) throws RemoteException;
}
