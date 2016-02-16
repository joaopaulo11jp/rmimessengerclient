package br.edu.ifpb.pd.rmimessenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.ifpb.pd.rmimessenger.interfaces.ClientIF;
import br.edu.ifpb.pd.rmimessenger.interfaces.MessengerIF;

public class MainScreen implements ActionListener{

	public JFrame frame;
	private JTextField textField;
	private Client client;
	private MessengerIF messenger;
	JTextArea textArea;
	JTextArea textArea_1;
	JTextArea textArea_2;
	JCheckBox chckbxPrivado;

	public MainScreen(ClientIF client,MessengerIF messenger) {
		this.client = (Client) client;
		this.messenger = messenger;
		initialize();
		try {
			this.client.setTextArea(textArea_1);
			this.client.setUserListArea(textArea_2);
			this.frame.setTitle("RMIMessenger | Usuario: "+client.getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.client.refreshList();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
				
		chckbxPrivado = new JCheckBox("Privado :");
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
		
		textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setBounds(321, 12, 115, 178);
		frame.getContentPane().add(textArea_2);
		
		frame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					messenger.exitMessenger(client);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Send")){
			try {
				if(!chckbxPrivado.isSelected())
					messenger.sendPublicMessage(client.getName(),textArea.getText());
				else{
					int result = messenger.sendPrivateMessage(client.getName(),textField.getText(), textArea.getText());
					if(result == 0)throw new Exception("Usuario nao encontrado");
					else if(result == -1) throw new Exception("Nao e possivel enviar para voce mesmo");
				}
				
				textArea.setText("");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (Exception ex){
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
			}
		}
	}
}
