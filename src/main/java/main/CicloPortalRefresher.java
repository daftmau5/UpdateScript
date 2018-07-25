package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import alert.TeamsAlert;

public class CicloPortalRefresher {
	public static void main(String[] args) {
		ResultSet ret;
		
		int exec = 1;
		try {
			System.out.println("---------------------------------------------------------");
			System.out.println("Starting");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://URL:PORT/BASE",
					"USER", "PASS");

			final Statement stt = con.createStatement();

			String sql;

			boolean loop = true;

			sql = "QUERY WITH RETURN;";

			PreparedStatement stmt = con.prepareStatement(sql);
			int cont = 1;

			while (loop) {
				ret = stmt.executeQuery();

				if (ret == null || !ret.next()) {
					loop = false;
					continue;
				}

				while (ret.next()) {
					System.out.print(ret.getInt(1)+" - ");
					cont++;
				}
				Date date = new Date();
				System.out.println();
				System.out.println("WebHooking...");
				TeamsAlert.sendMessage(" Exec " + exec + " ok ("+cont+" updates) - Free Memory: "+((Runtime.getRuntime().freeMemory()/1024)/1024)+"/"+((Runtime.getRuntime().totalMemory()/1024)/1024)+"Mb");
				System.out.println(date + " - Exec " + exec + " ok ("+cont+" updates).");
				date = null;
				exec++;
				cont = 1;
				Thread.sleep(1000);
			}

			stt.close();
			con.close();
			System.out.println("Ok");
			System.out.println("---------------------------------------------------------");

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
