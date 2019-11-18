package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Main;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public LoginView() {
		setTitle("Campus Appraiser");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usuarioField = new JTextField();
		usuarioField.setBounds(116, 64, 180, 20);
		contentPane.add(usuarioField);
		usuarioField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(161, 189, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String user = usuarioField.getText(); 
					String passwd = passwordField.getText();
					File arquivo = new File("./login.txt");
					FileReader arq = new FileReader(arquivo);
				    BufferedReader reader = new BufferedReader(arq);
				    String read1 = reader.readLine();
				    String read2 = reader.readLine();
				    if(user.equals(read1) && passwd.equals(read2)) {			    	
				    	JOptionPane.showMessageDialog(null, "Logado com sucesso!","", JOptionPane.INFORMATION_MESSAGE);
				    	dispose();
				    	Main.callAvaliadorFrame();
				    	reader.close();
				    	arq.close();
				    } else if(user.equals("admin") && passwd.equals("admin")) {
				    	JOptionPane.showMessageDialog(null, "Logado com sucesso!","", JOptionPane.INFORMATION_MESSAGE);
				    	dispose();
				    	Main.callAdminFrame();
				   	} else if(user.equals("")) {
				   		reader.close();
				   		arq.close();
				   		throw new Exception();				   		
				    } else if(!user.equals(read1) || !passwd.equals(read2)) {
				    	reader.close();
				    	arq.close();
				    	throw new Exception();
				    }
				    reader.close();
				    arq.close();
				}
				catch(Exception loginerror) {
					JOptionPane.showMessageDialog(null, "Dados incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnLogin);
		
		JLabel lblLogin = new JLabel("Usu�rio");
		lblLogin.setBounds(116, 39, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(116, 109, 46, 14);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(116, 136, 180, 20);
		contentPane.add(passwordField);
	}
}
