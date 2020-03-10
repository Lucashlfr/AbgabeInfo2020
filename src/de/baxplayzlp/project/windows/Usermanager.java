package de.baxplayzlp.project.windows;

import de.baxplayzlp.project.Fenster;
import de.baxplayzlp.project.Handlemanager;
import de.baxplayzlp.project.Mode;
import de.baxplayzlp.project.savemanager.Readmanager;
import de.baxplayzlp.project.savemanager.database.Profile;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Usermanager {

	private JFrame frmUsermanager;
	private JTextField txtChipID;
	private JTextField txtName;
	private JTextField txtFirstName;
	private JTextField txtWorkedTime;
	private JTextArea InfoTxt;

	private String logged = "";
	private int lines = 0;

	/**
	 * Launch the application.
	 */
	static void launch() {
		EventQueue.invokeLater(() -> {
            try {
                Usermanager window = new Usermanager();
                window.frmUsermanager.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the application.
	 */
	public Usermanager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUsermanager = new JFrame();
		frmUsermanager.setAlwaysOnTop(true);
		frmUsermanager.setResizable(false);
		frmUsermanager.setTitle("Usermanager");
		frmUsermanager.setBounds(100, 100, 772, 472);
		frmUsermanager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUsermanager.getContentPane().setLayout(null);

		JLabel lblGurard = new JLabel("Guard - Usermanager");
		lblGurard.setBounds(10, 11, 585, 34);
		lblGurard.setForeground(new Color(204, 51, 0));
		lblGurard.setFont(new Font("Calibri", Font.BOLD, 27));
		frmUsermanager.getContentPane().add(lblGurard);

		JLabel lblChipid = new JLabel("Chip-ID*:");
		lblChipid.setFont(new Font("Calibri", Font.BOLD, 15));
		lblChipid.setBounds(10, 74, 98, 14);
		frmUsermanager.getContentPane().add(lblChipid);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri", Font.BOLD, 15));
		lblName.setBounds(10, 101, 46, 14);
		frmUsermanager.getContentPane().add(lblName);

		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setFont(new Font("Calibri", Font.BOLD, 15));
		lblVorname.setBounds(10, 126, 69, 14);
		frmUsermanager.getContentPane().add(lblVorname);

		txtChipID = new JTextField();
		txtChipID.setBounds(85, 74, 180, 20);
		frmUsermanager.getContentPane().add(txtChipID);
		txtChipID.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(85, 99, 180, 20);
		frmUsermanager.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(85, 123, 180, 20);
		frmUsermanager.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblImmerAusfllen = new JLabel("Felder mit * immer ausf\u00FCllen:");
		lblImmerAusfllen.setBounds(10, 43, 254, 27);
		lblImmerAusfllen.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(lblImmerAusfllen);

		JButton btnMitarbeiterAnlegen = new JButton("Mitarbeiter Anlegen");
		btnMitarbeiterAnlegen.addActionListener(arg0 -> createNewUser());
		btnMitarbeiterAnlegen.setBounds(10, 158, 255, 25);
		btnMitarbeiterAnlegen.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(btnMitarbeiterAnlegen);

		JLabel lblGutschreiben = new JLabel("Gutschreiben:");
		lblGutschreiben.setBounds(335, 43, 254, 14);
		lblGutschreiben.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(lblGutschreiben);

		txtWorkedTime = new JTextField();
		txtWorkedTime.setBounds(335, 98, 255, 20);
		frmUsermanager.getContentPane().add(txtWorkedTime);
		txtWorkedTime.setColumns(10);

		JButton btnGutschrieben = new JButton("Gutschrieben");
		btnGutschrieben.addActionListener(e -> {

            if ((txtChipID.getText() != "") && (txtWorkedTime.getText() != "")) {

                String id = txtChipID.getText();

                if (Fenster.handlemanager.accountExists(id)) {


                    final Profile profile = Fenster.handlemanager.getProfile(id);

                    long old = profile.getWorkedtime();
                    long a = 0;

                    try{
                        a = Long.valueOf(txtWorkedTime.getText());
                    }catch (Exception e3) {
                        // TODO: handle exception
                    }
                    long add = TimeUnit.MINUTES.toMillis(a);

                    long n2 = old + add;
                    profile.setWorkedtime(n2);

                    String daten = Fenster.handlemanager.createProfileString(profile);

                    try {
                        PreparedStatement ps = Handlemanager.getMySQL().getConnection()
                                .prepareStatement("UPDATE LOGINSYSTEM SET daten = '" + daten +  "' WHERE chipid = '"
                        + profile.getChip() + "'");

                        ps.executeUpdate();

                        logged = "Gurard - Gutschrifft";
                        writeLog("Name: " + profile.getName());
                        writeLog("Gutgeschriebene Zeit: " + txtWorkedTime.getText() + " min");
                        writeLog("Neue Zeit:" + TimeUnit.MILLISECONDS.toMinutes(profile.getWorkedtime()) + " min");

                    } catch (SQLException e2) {
                        ErrorWindow.launch("Es können nur Minuten gutgeschrieben werden!");
                    }


                } else {
                    ErrorWindow.launch("Id existiert nicht!");
                }
            } else {
                ErrorWindow.launch("Id-Feld ist leer!");
            }

        });
		btnGutschrieben.setBounds(335, 121, 255, 25);
		btnGutschrieben.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(btnGutschrieben);

		JLabel lblGearbeiteteZeit = new JLabel("Gearbeitete Zeit:");
		lblGearbeiteteZeit.setBounds(335, 80, 130, 14);
		lblGearbeiteteZeit.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(lblGearbeiteteZeit);

		JButton btnMitarbeiterLschen = new JButton("Mitarbeiter Löschen");
		btnMitarbeiterLschen.addActionListener(e -> {
            if (!Objects.equals(txtChipID.getText(), "")) {

                String id = txtChipID.getText();

                if (Fenster.handlemanager.accountExists(id)) {

                    Fenster.handlemanager.deleteProfile(id);
                    writeLog(id + " wurde gelöscht!");

                } else {
                    ErrorWindow.launch("Id existiert nicht!");
                }
            } else {
                ErrorWindow.launch("Id-Feld ist leer!");
            }
        });
		btnMitarbeiterLschen.setBounds(10, 194, 255, 25);
		btnMitarbeiterLschen.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(btnMitarbeiterLschen);

		JButton btnInfo = new JButton("Info");
		btnInfo.addActionListener(e -> {
            if (!Objects.equals(txtChipID.getText(), "")) {
                final Profile profile = Fenster.handlemanager.getProfile(txtChipID.getText());
                logged = "Guard - Info";
                writeLog("Name: " + profile.getName());
                writeLog("Vorname: " + profile.getFirstname());
                writeLog("Letzte Aktion vor: " + TimeUnit.MILLISECONDS.toMinutes(profile.getTime()) + " Minuten");
                writeLog("Gearbeitete Zeit: " + TimeUnit.MILLISECONDS.toMinutes(profile.getWorkedtime())
                        + " Minuten");
                writeLog("Letzte Aktion: " + profile.getLastAction());
                writeLog("Letzte Aktion um: " + profile.getDate());
            } else {
                ErrorWindow.launch("Id-Feld ist leer!");
            }
        });
		btnInfo.setBounds(10, 230, 255, 25);
		btnInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(btnInfo);

		InfoTxt = new JTextArea();
		InfoTxt.setFont(new Font("Calibri", Font.PLAIN, 15));
		InfoTxt.setEditable(false);
		InfoTxt.setText("Guard - Info");
		logged = "Guard - Info";
		InfoTxt.setBounds(335, 159, 255, 259);
		frmUsermanager.getContentPane().add(InfoTxt);
		
		JButton btnRFID = new JButton("Chip-ID von RFID");
		btnRFID.addActionListener(arg0 -> {
            String rfid = Readmanager.read();
            txtChipID.setText(rfid);
        });
		btnRFID.setBounds(10, 266, 255, 25);
		btnRFID.setFont(new Font("Calibri", Font.BOLD, 15));
		frmUsermanager.getContentPane().add(btnRFID);
	}

	private void writeLog(final String s) {

		if (lines == 13) {
			logged = "Guard - Info";
		}

		String s0 = logged + "\r\n" + s;
		InfoTxt.setText(s0);
		logged = s0;
		lines++;
	}

	private void createNewUser() {
		String chip = txtChipID.getText();

		try {
			Long.parseLong(chip);

		} catch (NumberFormatException e) {
			writeLog("Die Chip-Id ist keine Zahl!");
			return;
		}

		String firstname = txtFirstName.getText();
		String name = txtName.getText();

		if (Objects.equals(name, "") || firstname == "") {
			writeLog("Der Vorname oder Nachname ist leer!");
			return;
		}

		long millis = System.currentTimeMillis();

		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		String orginal = date.format(new Date());

		Profile profile = new Profile(chip, name, firstname, millis, 1, Mode.INFO, orginal);

		if (!Fenster.handlemanager.accountExists(chip)) { // CAN CREATE PROFILE
			Fenster.handlemanager.createProfile(profile);
			writeLog("Chip regristriert (" + chip + ")!");
			txtChipID.setText("");
			txtFirstName.setText("");
			txtName.setText("");
		} else {
			writeLog("Dieser Chip ist bereits regristiert!");

		}
	}

}
