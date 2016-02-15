package br.edu.ifpb.pd.rmimessenger.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JTextArea;

public interface ClientIF extends Remote{
	public String getName() throws RemoteException;
	public void setName(String name) throws RemoteException;
	
	public void setTextArea(JTextArea textArea) throws RemoteException;
	
	public void message(String msg) throws RemoteException;
}
