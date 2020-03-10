package de.baxplayzlp.project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.baxplayzlp.project.savemanager.MySQL;
import de.baxplayzlp.project.savemanager.database.Profile;
import de.baxplayzlp.project.windows.ErrorWindow;

public class Handlemanager {

	private static MySQL mySQL;

	public static void intialiseMySQL() {
		mySQL = new MySQL("localhost", "3306", "schule", "admin", "");
		mySQL.connect();
		try {
			PreparedStatement preparedStatement = mySQL.getConnection().prepareStatement(
					"CREATE TABLE IF NOT EXISTS LOGINSYSTEM (chipid VARCHAR(100), daten VARCHAR(100))");
			System.out.println("[SQL] Database erstellt!");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[SQL] Verbindung konte nicht hergestellt werden!");
		}
	}

	public String createProfileString(final Profile profile) {
		return profile.getName() + "#" + profile.getFirstname() + "#" + profile.getTime() + "#"
				+ profile.getWorkedtime() + "#" + profile.getLastAction().toString() + "#" + profile.isLoggedin() + "#"
				+ profile.getDate();
	}

	private Profile readProfileString(final String chipid, final String ps) {
		String[] a = ps.split("#");

		return new Profile(chipid, a[0], a[1], Long.valueOf(a[2]), Long.valueOf(a[3]),
				Mode.valueOf(a[4]), a[6]);
	}

	public boolean accountExists(final String chip) {
		try {
			PreparedStatement ps = getMySQL().getConnection()
					.prepareStatement("SELECT daten FROM LOGINSYSTEM WHERE chipid = '" + chip + "'");

			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteProfile(final String chipid) {
		try {
			PreparedStatement ps = getMySQL().getConnection()
					.prepareStatement("DELETE FROM LOGINSYSTEM WHERE chipid = ?");
			ps.setString(1, chipid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createProfile(final Profile profile) {
		String chipid = profile.getChip();
		String daten = createProfileString(profile);
		if (accountExists(chipid)) {
			try {
				PreparedStatement ps = getMySQL().getConnection()
						.prepareStatement("UPDATE LOGINSYSTEM SET daten = ? WHERE chipid = ?");
				ps.setString(1, chipid);
				ps.setString(2, daten);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement ps = getMySQL().getConnection().prepareStatement(
						"INSERT INTO LOGINSYSTEM (chipid, daten) VALUES ('" + chipid + "','" + daten + "')");
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Profile getProfile(String chip) {
		if (!accountExists(chip)) {
			new ErrorWindow("Account existiert nicht");
			return null;
		}
		try {
			PreparedStatement preparedStatement = getMySQL().getConnection()
					.prepareStatement("SELECT daten FROM LOGINSystem WHERE chipid = ?");
			preparedStatement.setString(1, chip);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())

				return readProfileString(chip, resultSet.getString("daten"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void perform(Profile profile, final Mode mode) {


		if (profile.getLastAction() == mode) {
			if (mode == Mode.KOMMEN) {
				ErrorWindow.launch("Bereits eingestochen");
			} else if (mode == Mode.GEHEN) {
				ErrorWindow.launch("Noch nicht eingestochen");
			} else if (mode == Mode.GRUPPENSTUNDE) {
				ErrorWindow.launch("Bereits Gestochen!");
			}
			return;
		} else {
			System.out.println("STECHEN!");
			if (mode == Mode.GEHEN) {
				long now = System.currentTimeMillis();
				long la = profile.getTime();
				long hasworkedtime = profile.getWorkedtime();
				long workedtime = hasworkedtime + (now - la);
				profile.setWorkedtime(workedtime);
			} else if (mode == Mode.GRUPPENSTUNDE) {

				long old = profile.getWorkedtime();
				long add = TimeUnit.MINUTES.toMillis(60);

				long n2 = old + add;
				profile.setWorkedtime(n2);

			}
		}

		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		String orginal = date.format(new Date());
		profile.setDate(orginal);

		profile.setLoggedin(true);
		profile.setLastAction(mode);
		profile.setTime(System.currentTimeMillis());

		String daten = Fenster.handlemanager.createProfileString(profile);

		try {
			PreparedStatement ps = getMySQL().getConnection().prepareStatement(
					"UPDATE LOGINSYSTEM SET daten = '" + daten + "' WHERE chipid = '" + profile.getChip() + "'");

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static MySQL getMySQL() {
		return mySQL;
	}

}
