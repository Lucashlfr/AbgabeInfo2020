package de.baxplayzlp.project.savemanager.database;

import java.io.Serializable;

import de.baxplayzlp.project.Mode;

public class Profile implements Serializable{

	//Sorgt dafür dass alle Daten in eine Datei exportiert werden können und gelesen werden können!
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chip;
	private String name;
	private String firstname;
	private long time;
	private long workedtime;
	private Mode lastAction;
	private boolean loggedin;
	private String date;
	
	
	public Profile(String chip, String name, String firstname, long time, long workedtime, Mode lastAction, String date){
		this.chip = chip;
		this.name = name;
		this.firstname = firstname;
		this.time = time;
		this.workedtime = workedtime;
		this.lastAction = lastAction;
		this.loggedin = (lastAction == Mode.KOMMEN);
		this.date = date;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public boolean isLoggedin() {
		return loggedin;
	}


	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}


	public String getChip() {
		return chip;
	}


	public void setChip(String chip) {
		this.chip = chip;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public long getTime() {
		return time;
	}


	public void setTime(long time) {
		this.time = time;
	}


	public long getWorkedtime() {
		return workedtime;
	}


	public void setWorkedtime(long workedtime) {
		this.workedtime = workedtime;
	}


	public Mode getLastAction() {
		return lastAction;
	}


	public void setLastAction(Mode lastAction) {
		this.lastAction = lastAction;
	}
	
	
	
	
}
