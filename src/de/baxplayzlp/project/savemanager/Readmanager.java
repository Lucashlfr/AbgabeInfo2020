package de.baxplayzlp.project.savemanager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Readmanager {


	public static String read() {
		String s = "";
		try {
			  FileInputStream in = new FileInputStream("C://Users//clash//Documents//Schule//Klasse 12//TI//Abschlussprojekt//Saveplace//log.csv");
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			 
			  String strLine = null, tmp;
			 
			  while ((tmp = br.readLine()) != null)
			  {
			     strLine = tmp;
			  }

			s = strLine;
			 
			  in.close();
			 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;

	}

}
