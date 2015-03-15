package bham.trasformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author Zaina
 * 
 */

public class DBConnection {


	public static final String NEW_PROPERTIES_FILE_NAME = "newdatabase.properties";
	public static final String PROPERTIES_FILE_NAME = "database.properties";
	public static final String DB_NAME_PROP = "database.name";
	public static final String DB_DRIVER_PROP = "jdbc.drivers";
	public static final String DB_USER_NAME_PROP = "database.user";
	public static final String DB_PASSWORD_PROP = "database.password";
	public static final String DB_SCHEMA_PROP = "database.schema";
	public static final String DB_URL = "database.url";
	// this should be dynamic ,used as  static for testing only
	private static final String TEMP_FILE_PATH = "src/bham/trasformation/";	
	private static final String GET_DB_USER_NAME = "Enter your database username:";
	private static final String GET_DB_USER_PASS = "Enter your database password:";
	private static final String GET_DB_URL = "Enter your database URL:";
	private static final String GET_DB_NAME = "Enter your database name:";
	private static final String GET_DB_SCHEMA = "Enter your database schema:";
	private static final String GET_DB_DRIVER = "Enter your database driver:";

	
	private Connection conn = null;

	private Properties props;
	
	
	public Properties getProps() {
		return props;
	}


	public Connection connect() {
		try {
			// Load in Database properties from file
			props = new Properties();
			
			// try to load the last saved properties
			InputStream in = DBConnection.class
					.getResourceAsStream(NEW_PROPERTIES_FILE_NAME);

			// if no new properties exists load the default ones
			if (in == null) {
				System.out.println("No new properties are stored");
				in = DBConnection.class
						.getResourceAsStream(PROPERTIES_FILE_NAME);

				if (in == null) {
					System.err
							.println("Error: Failed to find the \"database.properties\" file. Note that it must be ");
					System.err
							.println("in the same directory as Main.class and that the name is case sensitive");
					System.exit(1);
				}
			}
			props.load(in);
			// close stream
			in.close();
			
			// get the db info from properties
			String[] databaseInfo = getDBConnectionInfo(props);
			if (databaseInfo[0] == null) {
				System.err.println("Error: No JDBC driver specified in the");
				System.exit(1);
			}

			//load the jdbc driver
			Class.forName(databaseInfo[0]);
			
			// Get a database connection
			String fullDatabaseURL = null;
			try {
				if (!databaseInfo[1].endsWith("/"))
					databaseInfo[1] = databaseInfo[1].concat("/");

				fullDatabaseURL = databaseInfo[1] + databaseInfo[2];// +":"+databaseInfo[3];
				conn = DriverManager.getConnection(fullDatabaseURL.trim(),
						databaseInfo[4], databaseInfo[5]);
				System.out.println(fullDatabaseURL);

			} catch (Exception e) {
				System.out.println(fullDatabaseURL);
				e.printStackTrace();
				throw new Exception("Error: Attempt to connect to database \""
						+ fullDatabaseURL
						+ ((databaseInfo[4] == null) ? ""
								: (" with username \"" + databaseInfo[4]))
						+ "\" failed: " + e.getMessage());
			}

			saveProperties(props ,databaseInfo);
		} catch (SQLException e) {
			do {
				System.out.println(e.getMessage());
				// e.printStackTrace();
			} while ((e = e.getNextException()) != null);
			System.exit(1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		return conn;

	}

	public void close() {
		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	// build the dbinfo UI
	private static String[] getDBConnectionInfo(Properties props) {
		// get the JDBC driver
		String drivers = props.getProperty(DB_DRIVER_PROP);
		// get the db url
		String databaseUrl = props.getProperty(DB_URL);
		// get the db Name
		String database = props.getProperty(DB_NAME_PROP);
		// get the schema
		String schema = props.getProperty(DB_SCHEMA_PROP);

		JTextField dbDriver = new JTextField((drivers == null) ? "" : drivers,
				50);
		JTextField dbURL = new JTextField((databaseUrl == null) ? ""
				: databaseUrl, 20);
		JTextField dbName = new JTextField((database == null) ? "" : database,
				20);
		JTextField dbSchema = new JTextField((schema == null) ? "" : schema, 20);
		JTextField username = new JTextField(20);
		JPasswordField password = new JPasswordField(20);

		final Object[] fields = { GET_DB_DRIVER, dbDriver, GET_DB_URL, dbURL,
				GET_DB_NAME, dbName, GET_DB_SCHEMA, dbSchema, GET_DB_USER_NAME,
				username, GET_DB_USER_PASS, password };

		JOptionPane pane = new JOptionPane(fields,
				JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION,
				null);
		JDialog d = pane.createDialog(null, "Database Access");
		d.setVisible(true);

		Integer returnValue = (Integer) pane.getValue();
		d.dispose();

		if (returnValue == null || returnValue != JOptionPane.OK_OPTION) {
			return null;
		}
		return new String[] { dbDriver.getText(), dbURL.getText(),
				dbName.getText(), dbSchema.getText(), username.getText(),
				new String(password.getPassword()) };
	}

	private void saveProperties(Properties props ,String[] dbInfo) {
		
		// set the JDBC driver
		props.setProperty(DB_DRIVER_PROP, dbInfo[0]);
		// set the db url
		props.setProperty(DB_URL, dbInfo[1]);
		// set the db Name
		props.setProperty(DB_NAME_PROP, dbInfo[2]);
		// set the schema
		props.setProperty(DB_SCHEMA_PROP, dbInfo[3]);

		File file = new File(TEMP_FILE_PATH
				+ NEW_PROPERTIES_FILE_NAME);
		OutputStream out = null;
		try {

			out = new FileOutputStream(file);
			props.store(out, "This is an optional header comment string");
			System.out.println(file.getAbsolutePath());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}