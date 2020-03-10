package de.baxplayzlp.project;

import de.baxplayzlp.project.savemanager.Readmanager;
import de.baxplayzlp.project.savemanager.database.Profile;
import de.baxplayzlp.project.windows.AdminLogin;
import de.baxplayzlp.project.windows.CreditWindow;
import de.baxplayzlp.project.windows.ErrorWindow;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Fenster {

	public static Handlemanager handlemanager = new Handlemanager();
	
	private String logged = "";
	private String id = "0";
	
	private int lines = 0;
	
	private JFrame frmGuard;
	private static JTextField txtId;
	private JTextArea LogTxt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		System.out.println("");
		System.out.println(" _______  __   __  _______  ______    ______                                                                              ");
		System.out.println("|       ||  | |  ||   _   ||    _ |  |      |                                                                             ");
		System.out.println("|    ___||  | |  ||  |_|  ||   | ||  |  _    |   ____                                                                     ");
		System.out.println("|   | __ |  |_|  ||       ||   |_||_ | | |   |  |____|                                                                    ");
		System.out.println("|   ||  ||       ||       ||    __  || |_|   |                                                                            ");
		System.out.println("|   |_| ||       ||   _   ||   |  | ||       |                                                                            ");
		System.out.println("|_______||_______||__| |__||___|  |_||______|                                                                             ");
		System.out.println(" _______  __   __    ___      __   __  _______  _______  _______    __   __  _______  ___      _______  _______  ______   ");
		System.out.println("|  _    ||  | |  |  |   |    |  | |  ||       ||   _   ||       |  |  | |  ||       ||   |    |       ||       ||    _ |  ");
		System.out.println("| |_|   ||  |_|  |  |   |    |  | |  ||       ||  |_|  ||  _____|  |  |_|  ||    ___||   |    |    ___||    ___||   | ||  ");
		System.out.println("|       ||       |  |   |    |  |_|  ||       ||       || |_____   |       ||   |___ |   |    |   |___ |   |___ |   |_||_ ");
		System.out.println("|  _   | |_     _|  |   |___ |       ||      _||       ||_____  |  |       ||    ___||   |___ |    ___||    ___||    __  |");
		System.out.println("| |_|   |  |   |    |       ||       ||     |_ |   _   | _____| |  |   _   ||   |___ |       ||   |    |   |___ |   |  | |");
		System.out.println("|_______|  |___|    |_______||_______||_______||__| |__||_______|  |__| |__||_______||_______||___|    |_______||___|  |_|");
		System.out.println("");
		
		Handlemanager.intialiseMySQL();
		
		EventQueue.invokeLater(() -> {
            try {
                Fenster window = new Fenster();
                window.frmGuard.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
		Readmanager.read();
		
		
	}

	

	/**
	 * Create the application.
	 */
	private Fenster() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuard = new JFrame();
		frmGuard.setResizable(false);
		frmGuard.setTitle("Guard");
		frmGuard.setBounds(100, 100, 772, 472);
		frmGuard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmGuard.getContentPane().setLayout(null);
		
		JLabel lblGurardLucas = new JLabel("Guard - Lucas Helfer");
		lblGurardLucas.setBounds(10, 11, 235, 34);
		lblGurardLucas.setForeground(new Color(204, 51, 0));
		lblGurardLucas.setFont(new Font("Calibri", Font.BOLD, 27));
		frmGuard.getContentPane().add(lblGurardLucas);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblId.setBounds(10, 112, 46, 14);
		frmGuard.getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtId.setColumns(10);
		txtId.setBounds(79, 109, 198, 20);
		frmGuard.getContentPane().add(txtId);
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setFont(new Font("Calibri", Font.BOLD, 20));
		lblLog.setBounds(359, 50, 266, 20);
		frmGuard.getContentPane().add(lblLog);
		
		LogTxt = new JTextArea();
		LogTxt.setFont(new Font("Calibri", Font.PLAIN, 15));
		LogTxt.setEditable(false);
		LogTxt.setText("Guard - by Lucas");
		logged = "Guard - by Lucas";
		lines++;
		LogTxt.setBounds(359, 81, 362, 280);
		frmGuard.getContentPane().add(LogTxt);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(55); //Projekt state
		progressBar.setBounds(10, 387, 711, 20);
		frmGuard.getContentPane().add(progressBar);
		
		JLabel lblProjektstatus = new JLabel("Projektstatus");
		lblProjektstatus.setFont(new Font("Calibri", Font.BOLD, 20));
		lblProjektstatus.setBounds(10, 358, 198, 26);
		frmGuard.getContentPane().add(lblProjektstatus);
		
		JButton btnKommen = new JButton("Kommen");
		btnKommen.setBounds(10, 140, 150, 50);
		frmGuard.getContentPane().add(btnKommen);
		
		JButton btnGehen = new JButton("Gehen");
		btnGehen.setBounds(180, 140, 150, 50);
		frmGuard.getContentPane().add(btnGehen);
		
		JButton btnGottesdienst = new JButton("Gruppenstunde");
		btnGottesdienst.setBounds(10, 210, 150, 50);
		frmGuard.getContentPane().add(btnGottesdienst);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.setBounds(180, 210, 150, 50);
		frmGuard.getContentPane().add(btnInfo);
		
		JButton btnRFID = new JButton("Von RFID lesen");
		btnRFID.addActionListener(arg0 -> {
            String id = Readmanager.read();
            txtId.setText(id);
        });
		btnRFID.setBounds(10, 51, 150, 50);
		frmGuard.getContentPane().add(btnRFID);
		btnInfo.addActionListener(e -> {
            id = txtId.getText();
            stechen(id, Mode.INFO);
        });
		
		
		
		
		btnGottesdienst.addActionListener(e -> {
            id = txtId.getText();
            stechen(id, Mode.GRUPPENSTUNDE);
        });
		btnGehen.addActionListener(e -> {
            id = txtId.getText();
            stechen(id, Mode.GEHEN);
        });
		btnKommen.addActionListener(e -> {
            id = txtId.getText();
            stechen(id, Mode.KOMMEN);
        });
		
	
		JMenuBar menuBar = new JMenuBar();
		frmGuard.setJMenuBar(menuBar);
		
		JButton btnUsermanager = new JButton("User-Manager");
		btnUsermanager.addActionListener(e -> AdminLogin.launch());
		menuBar.add(btnUsermanager);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(arg0 -> CreditWindow.launch());
		menuBar.add(btnCredits);
		
		
	}
	
	private void writeLog(final String s){
		
		if(lines == 13){
			logged = "Guard - by Lucas";
		}
		
		String s0 = logged + "\r\n" + s;	
		LogTxt.setText(s0);
		logged = s0;
		lines++;
	}
	
	private void stechen(final String id, final Mode mode){
		
		if(txtId.getText().isEmpty()) {
			ErrorWindow.launch("Keine ID eingegeben!");
			clear();
			return;
		}
		
		
		try{
			Long.parseLong(txtId.getText());
		}catch (NumberFormatException e) {
			ErrorWindow.launch("Die ID ist keine Zahl");
			clear();
			return;
		}
		if(!handlemanager.accountExists(id)){
			ErrorWindow.launch("ID Existiert nicht!");
			clear();
			return;
		}
		
		Profile profile = handlemanager.getProfile(id);

		switch (mode) {
			case KOMMEN:
				Handlemanager.perform(profile, Mode.KOMMEN);
				info(profile);
				break;
			case GEHEN:
				Handlemanager.perform(profile, Mode.GEHEN);
				info(profile);
				break;
			case GRUPPENSTUNDE:
				Handlemanager.perform(profile, Mode.GRUPPENSTUNDE);
				info(profile);

				break;
			case INFO:
				info(profile);
				break;
		}
	}
	
	private void info(final Profile profile){
		logged = "Guard - Info";
		writeLog("Name: " + profile.getName());
		writeLog("Vorname: " + profile.getFirstname());
		writeLog("Letzte Aktion um: " + profile.getDate());
		writeLog("Gearbeitete Zeit: " + TimeUnit.MILLISECONDS.toMinutes(profile.getWorkedtime()) + " Minuten");
		writeLog("Letzte Aktion: " + profile.getLastAction());
	}

	private void clear(){
		txtId.setText("");
	}

}
