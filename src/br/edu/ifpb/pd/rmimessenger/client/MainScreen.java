package br.edu.ifpb.pd.rmimessenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.ifpb.pd.rmimessenger.interfaces.ClientIF;
import br.edu.ifpb.pd.rmimessenger.interfaces.MessengerIF;
import javax.swing.JTextPane;
import javax.swing.JList;

public class MainScreen implements ActionListener{

	public JFrame frame;
	private JTextField textField;
	private ClientIF client;
	private MessengerIF messenger;
	JTextArea textArea;
	JTextArea textArea_1;

	public MainScreen(ClientIF client,MessengerIF messenger) {
		this.client = client;
		this.messenger = messenger;
		initialize();
		try {
			this.client.setTextArea(textArea_1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JCheckBox chckbxPrivado = new JCheckBox("Privado :");
		chckbxPrivado.setBounds(8, 198, 87, 23);
		frame.getContentPane().add(chckbxPrivado);
		
		textField = new JTextField();
		textField.setBounds(103, 200, 206, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(18, 229, 291, 32);
		frame.getContentPane().add(textArea);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(321, 236, 117, 25);
		btnEnviar.addActionListener(this);
		btnEnviar.setActionCommand("Send");
		frame.getContentPane().add(btnEnviar);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(12, 12, 291, 178);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setBounds(321, 12, 115, 178);
		frame.getContentPane().add(textArea_2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Send")){
			try {
				messenger.sendPublicMessage(textArea.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
