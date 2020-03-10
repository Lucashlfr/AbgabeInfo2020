package de.baxplayzlp.project.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ErrorWindow {

	private JFrame frmGuardError;

	/**
	 * Launch the application.
	 */
	public static void launch(String error){
		EventQueue.invokeLater(() -> {
            try {
                ErrorWindow window = new ErrorWindow(error);
                window.frmGuardError.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the application.
	 */
	public ErrorWindow(String error) {
		initialize(error);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String error) {
		frmGuardError = new JFrame();
		frmGuardError.setType(Type.POPUP);
		frmGuardError.setTitle("Guard - Error");
		frmGuardError.setResizable(false);
		frmGuardError.setBounds(100, 100, 450, 300);
		frmGuardError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGuardError.getContentPane().setLayout(null);
		
		
		
		JLabel lblGurardLucas = new JLabel("Guard - Fehler");
		lblGurardLucas.setBounds(10, 11, 235, 34);
		lblGurardLucas.setForeground(new Color(204, 51, 0));
		lblGurardLucas.setFont(new Font("Calibri", Font.BOLD, 27));
		frmGuardError.getContentPane().add(lblGurardLucas);
		
		JLabel lblBitteLoggeDich = new JLabel("Diese Aktion konnte nicht ausgef\u00FChrt werden\r\n");
		lblBitteLoggeDich.setHorizontalAlignment(SwingConstants.LEFT);
		lblBitteLoggeDich.setIcon(new ImageIcon(ErrorWindow.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
		lblBitteLoggeDich.setVerticalAlignment(SwingConstants.TOP);
		lblBitteLoggeDich.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblBitteLoggeDich.setBounds(20, 52, 318, 89);
		frmGuardError.getContentPane().add(lblBitteLoggeDich);
		
		JLabel lblErstEinBevor = new JLabel("Grund:");
		lblErstEinBevor.setBounds(56, 74, 248, 28);
		lblErstEinBevor.setFont(new Font("Calibri", Font.PLAIN, 15));
		frmGuardError.getContentPane().add(lblErstEinBevor);
		
		JButton btnVerstanden = new JButton("Verstanden");
		btnVerstanden.addActionListener(arg0 -> frmGuardError.dispose());
		btnVerstanden.setFont(new Font("Calibri", Font.BOLD, 20));
		btnVerstanden.setBounds(30, 127, 200, 60);
		frmGuardError.getContentPane().add(btnVerstanden);
		
		JLabel lblFehler = new JLabel(error);
		lblFehler.setForeground(Color.RED);
		lblFehler.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFehler.setBounds(56, 102, 291, 14);
		frmGuardError.getContentPane().add(lblFehler);
	}
}
