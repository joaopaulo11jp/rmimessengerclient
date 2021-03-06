package br.edu.ifpb.pd.rmimessenger.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.ifpb.pd.rmimessenger.interfaces.ClientIF;
import br.edu.ifpb.pd.rmimessenger.interfaces.MessengerIF;
import java.awt.Label;
import javax.swing.ImageIcon;

public class MainScreen implements ActionListener{

	public JFrame frame;
	private JTextField textField;
	private Client client;
	private MessengerIF messenger;
	JTextArea textArea;
	JTextArea textArea_1;
	JTextArea textArea_2;
	JCheckBox chckbxPrivado;
	private JScrollPane pane;

	public MainScreen(ClientIF client,MessengerIF messenger) {
		this.client = (Client) client;
		this.messenger = messenger;
		initialize();
		try {
			this.client.setTextArea(textArea_1);
			this.client.setUserListArea(textArea_2);
			
			JLabel lblUsuriosOnline = new JLabel("Usuários Online");
			lblUsuriosOnline.setBounds(569, 12, 117, 15);
			frame.getContentPane().add(lblUsuriosOnline);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(MainScreen.class.getResource("/br/edu/ifpb/pd/rmimessenger/img/578220-800x600.jpg")));
			label.setBounds(0, 2, 698, 471);
			frame.getContentPane().add(label);
			this.frame.setTitle("RMIMessenger | Usuario: "+client.getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.client.refreshList();
	}

	private void initialize() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
				
		chckbxPrivado = new JCheckBox("Privado :");
		chckbxPrivado.setBounds(18, 381, 87, 23);
		frame.getContentPane().add(chckbxPrivado);
		
		textField = new JTextField();
		textField.setBounds(122, 383, 206, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(18, 412, 539, 32);
		frame.getContentPane().add(textArea);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(569, 419, 117, 25);
		btnEnviar.addActionListener(this);
		btnEnviar.setActionCommand("Send");
		frame.getContentPane().add(btnEnviar);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		//textArea_1.setOpaque(false);
		textArea_1.setBounds(12, 29, 545, 344);
		frame.getContentPane().add(textArea_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setBounds(571, 29, 115, 361);
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
