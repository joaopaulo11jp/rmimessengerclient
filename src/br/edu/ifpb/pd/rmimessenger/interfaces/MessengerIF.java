package br.edu.ifpb.pd.rmimessenger.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerIF extends Remote{
	public boolean joinMessenger(ClientIF client) throws RemoteException;
	public void sendPublicMessage(String fromUser,String msg) throws RemoteException;
	public boolean sendPrivateMessage(String fromUser,String user,String msg) throws RemoteException;
}
