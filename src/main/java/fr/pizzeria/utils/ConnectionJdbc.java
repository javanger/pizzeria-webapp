package fr.pizzeria.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionJdbc {

	/** connection : connection  à la BDD*/
	private static Connection connection = null;
	/** statement : statement  utilisé plus tard*/
	private static Statement statement; 
	
	/**
	 * Méthode qui va lire le fichier jdbc.properties pour les paramètres d'accès à la BDD
	 * @return Properties
	 * @throws Exception
	 */
	public static Properties loadPropertiesFile() throws Exception {

		Properties properties = new Properties();
		InputStream in = new FileInputStream("jdbc.properties");
		properties.load(in);
		in.close();
		return properties;
	}

	/**
	 * Méthode qui va faire la connection à la BDD
	 */
	public static void connectionDB()  {

		try {

			Properties properties = loadPropertiesFile();

			String driverClass = properties.getProperty("MYSQLJDBC.driver");
			String url = properties.getProperty("MYSQLJDBC.url");
			String username = properties.getProperty("MYSQLJDBC.username");
			String password = properties.getProperty("MYSQLJDBC.password");

			Class.forName(driverClass);

			connection = DriverManager.getConnection(url, username, password);
			
			if(connection != null)
					statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Méthode pour fermer la connection avec la base de données
	 */
	public static void fermerJDBC(){
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Méthode qui va ouvrir une nouvelle connection si il n'y en a pas d'active
	 */
	public static void ouvrirConnection(){
		try {
			if (connection == null || connection.isClosed()){
				connectionDB();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter de connection
	 * @return connection
	 */
	public static Connection getConnection(){
		return connection;
	}
	
	/**
	 * Getter de statement
	 * @return statement
	 */
	public static Statement getStatement(){
		return statement;
	}
}
