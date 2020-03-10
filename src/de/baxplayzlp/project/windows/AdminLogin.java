package de.baxplayzlp.project.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AdminLogin {

	private JFrame frmGuardAdmin;
	private JTextField txtUsername;
	private JTextField pwd;
	private JLabel lblFalschesPasswort;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		EventQueue.invokeLater(() -> {
            try {
                AdminLogin window = new AdminLogin();
                window.frmGuardAdmin.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the application.
	 */
	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuardAdmin = new JFrame();
		frmGuardAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage(AdminLogin.class
				.getResource("/com/sun/deploy/uitoolkit/impl/fx/ui/resources/image/graybox_error.png")));
		frmGuardAdmin.setTitle("Guard - Admin Login");
		frmGuardAdmin.setType(Type.POPUP);
		frmGuardAdmin.setResizable(false);
		frmGuardAdmin.setBounds(100, 100, 450, 300);
		frmGuardAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGuardAdmin.getContentPane().setLayout(null);

		JLabel lblGurardLucas = new JLabel("Guard - Admin Login");
		lblGurardLucas.setBounds(10, 11, 235, 34);
		lblGurardLucas.setForeground(new Color(204, 51, 0));
		lblGurardLucas.setFont(new Font("Calibri", Font.BOLD, 27));
		frmGuardAdmin.getContentPane().add(lblGurardLucas);

		txtUsername = new JTextField();
		txtUsername.setBounds(10, 74, 240, 20);
		frmGuardAdmin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 56, 117, 14);
		frmGuardAdmin.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 105, 66, 14);
		frmGuardAdmin.getContentPane().add(lblPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
            if (txtUsername.getText().equalsIgnoreCase("admin")) {

                if (pwd.getText().equals("root")) {
                    frmGuardAdmin.dispose();
                    Usermanager.launch();
                }
            } else if(pwd.getText().length() > 0 && txtUsername.getText().length()> 0) {
                
                lblFalschesPasswort.setText("Falscher Login!");
                lblFalschesPasswort.setIcon(new ImageIcon(AdminLogin.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-error.png")));
                
            }else {
                lblFalschesPasswort.setText("Bitte Login eigegeben!");
                lblFalschesPasswort.setIcon(new ImageIcon(AdminLogin.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-error.png")));
                
            }

        });
		btnLogin.setBounds(10, 149, 89, 23);
		frmGuardAdmin.getContentPane().add(btnLogin);
		
		pwd = new JTextField();
		pwd.setForeground(Color.WHITE);
		pwd.setBounds(10, 118, 240, 20);
		frmGuardAdmin.getContentPane().add(pwd);
		pwd.setColumns(10);
		
		lblFalschesPasswort = new JLabel("Falsches Passwort!");
		lblFalschesPasswort.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFalschesPasswort.setText(" ");
		lblFalschesPasswort.setIcon(null);
		lblFalschesPasswort.setBounds(109, 153, 203, 34);
		frmGuardAdmin.getContentPane().add(lblFalschesPasswort);
		
		lblFalschesPasswort.setText(" ");
		
		
	}
}
