package de.baxplayzlp.project.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Window.Type;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Toolkit;

public class CreditWindow {

	private JFrame frmGuardCredits;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		EventQueue.invokeLater(() -> {
            try {
                CreditWindow window = new CreditWindow();
                window.frmGuardCredits.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the application.
	 */
	public CreditWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuardCredits = new JFrame();
		frmGuardCredits.setIconImage(Toolkit.getDefaultToolkit().getImage(CreditWindow.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		frmGuardCredits.setType(Type.POPUP);
		frmGuardCredits.setTitle("Guard - Credits");
		frmGuardCredits.setResizable(false);
		frmGuardCredits.setBounds(100, 100, 450, 300);
		frmGuardCredits.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGuardCredits.getContentPane().setLayout(null);
		
		
		JLabel lblGurard = new JLabel("Guard - Usermanager");
		lblGurard.setBounds(10, 11, 585, 34);
		lblGurard.setForeground(new Color(204, 51, 0));
		lblGurard.setFont(new Font("Calibri", Font.BOLD, 27));
		frmGuardCredits.getContentPane().add(lblGurard);
		
		JTextArea txtrProgrammiertVonLucas = new JTextArea();
		txtrProgrammiertVonLucas.setText("Programmiert von Lucas Helfer\r\nKlasse 12a\r\n\r\n(c) windowbuilder by Eclipse.org\r\nMySQL by FynnxProduction (Lucas Helfer)\r\n\r\nVersion: 1.0alpha\r\nlucas.helfer@gmx.net");
		txtrProgrammiertVonLucas.setBounds(20, 41, 389, 170);
		frmGuardCredits.getContentPane().add(txtrProgrammiertVonLucas);
		frmGuardCredits.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmGuardCredits.getContentPane(), lblGurard, txtrProgrammiertVonLucas}));
		
	}
}
