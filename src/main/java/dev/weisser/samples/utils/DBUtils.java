package dev.weisser.samples.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.google.gson.JsonElement;

import dev.weisser.samples.SampleDBObj;

public class DBUtils {

	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public DBUtils() {
		Properties prop = new Properties();
		try {
			// load config.properties for db credentials
			prop.load(new FileInputStream("src/main/resources/config.properties"));
			// load jdbc driver and start db connection
			// mit "SET GLOBAL time_zone = "+3:00" time_zone bei mysql setzen um keine TimeZone errors zu bekommen
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + prop.getProperty("host", "localhost") +  "/"+prop.getProperty("database"), prop.getProperty("user"), prop.getProperty("password", "lol"));
			String query = "INSERT INTO someTable (timestamp, uid, name, text, description) values (?, ?, ?, ?, ?)";
			// preparedStatement benefits: faster, no sql injection is possible
			// must be created once initially with the query and is then only filled with data; this improves performance.
			preparedStatement = connection.prepareStatement(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * insert the obj to the database
	 * 
	 * @param SambleDBObj
	 * @throws SQLException
	 */
	public void insertSomething(SampleDBObj obj) throws SQLException {
		preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
		preparedStatement.setString(2, obj.getUid());
		preparedStatement.setString(3, obj.getName());
		preparedStatement.setString(4, obj.getText());
		preparedStatement.setString(5, obj.getDescription());
		preparedStatement.executeUpdate();
	}
	
	/**
	 * 
	 * @return List<SampleDBObj> - return all entrys
	 * @throws SQLException
	 */
	public List<SampleDBObj> listEvrything() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM someTable");
		List <SampleDBObj> objs = new ArrayList<SampleDBObj>();
		while(rs.next()) {    
			SampleDBObj obj = new SampleDBObj(rs.getDate("timestamp"), rs.getString("uid"), rs.getString("name"), rs.getString("text"), rs.getString("description"));
			objs.add(obj);
		}
		return objs;
	}

	/**
	 * 
	 * @return SampleDBObj - return specific entry by name
	 * @throws SQLException
	 */
	public SampleDBObj list(String name) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM someTable WHERE name = ?");
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			return new SampleDBObj(rs.getDate("timestamp"), rs.getString("uid"), rs.getString("name"), rs.getString("text"), rs.getString("description"));
		}
		return null;
	}
}
