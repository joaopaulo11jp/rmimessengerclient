package br.edu.ifpb.pd.rmimessenger.client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.ifpb.pd.rmimessenger.interfaces.ClientIF;
import br.edu.ifpb.pd.rmimessenger.interfaces.MessengerIF;


public class StartScreen implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(118, 334, 117, 25);
		btnEntrar.addActionListener(this);
		btnEntrar.setActionCommand("Open");
		frame.getContentPane().add(btnEntrar);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 290, 131, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(66, 292, 49, 15);
		frame.getContentPane().add(lblNome);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(66, 265, 31, 15);
		frame.getContentPane().add(lblIp);
		
		textField = new JTextField();
		textField.setBounds(116, 263, 131, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(StartScreen.class.getResource("/br/edu/ifpb/pd/rmimessenger/img/male.png")));
		label_1.setBounds(116, 32, 139, 227);
		frame.getContentPane().add(label_1);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(StartScreen.class.getResource("/br/edu/ifpb/pd/rmimessenger/img/bg_chat.jpg")));
		label.setBounds(0, 0, 348, 473);
		frame.getContentPane().add(label);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Open")){
			try {
	            MessengerIF messenger = (MessengerIF) Naming.lookup("rmi://localhost/chat");
	            ClientIF client = new Client(textField_1.getText());
	            if(!messenger.joinMessenger(client)) throw new Exception("Usuario ja existente!");
	            new MainScreen(client,messenger).frame.setVisible(true);
	            frame.dispose();
	        } catch (NotBoundException ex) {
	            Logger.getLogger(StartScreen.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (MalformedURLException ex) {
	            Logger.getLogger(StartScreen.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (RemoteException ex) {
	            Logger.getLogger(StartScreen.class.getName()).log(Level.SEVERE, null, ex);
	        }  catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
	        }
			
		}
	}
}
