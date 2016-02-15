package br.edu.ifpb.pd.rmimessenger.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JTextArea;

import br.edu.ifpb.pd.rmimessenger.interfaces.ClientIF;

public class Client extends UnicastRemoteObject implements ClientIF{
	private String name;
	private JTextArea txtArea;
	
	private static final long serialVersionUID = 1L;
	
		public Client(String name) throws RemoteException{
			this.name = name;
		}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
		
	}

	@Override
	public void message(String msg) throws RemoteException {
		this.txtArea.append(msg);
	}

	@Override
	public void setTextArea(JTextArea textArea) throws RemoteException {
		this.txtArea = textArea;		
	}

}
