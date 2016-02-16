package br.edu.ifpb.pd.rmimessenger.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import javax.swing.JTextArea;

import br.edu.ifpb.pd.rmimessenger.interfaces.ClientIF;

public class Client extends UnicastRemoteObject implements ClientIF{
	private String name;
	private JTextArea txtArea;
	private JTextArea userList;
	private Collection<String> usersNow;
	
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


	public void setTextArea(JTextArea textArea) throws RemoteException {
		this.txtArea = textArea;		
	}
	

	public void setUserListArea(JTextArea userList) throws RemoteException {
		this.userList = userList;		
	}

	@Override
	public void setUserList(Collection<String> users) throws RemoteException {
		if(usersNow == null) this.usersNow = users;
		else{
			this.usersNow = users;
			this.refreshList();
		}
	}
	
	public void refreshList(){
		this.userList.setText("");
	
		for(String user : this.usersNow){
			this.userList.setText(this.userList.getText()+user+"\n");
		}
	}

}
