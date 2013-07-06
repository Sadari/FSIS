package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Connector {
	
	private final String dbURL = "jdbc:mysql://localhost:3306/";
	private final String dbClasss = "";
	private final String driverName = "com.mysql.jdbc.Driver";
	private final String dbUserName = "root";
	private final String dbpwd = "";

	private Connection connection;
	private Statement statement;
	private String query = "";
	private ResultSet resultSet;
	private ArrayList<String> fillingStations, fsInfo, telDirectory;
	private float diameter;
	private float upperLat, lowerLat, upperLang, lowerLang;
	
	public Connector(){
		super();
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			connection = (Connection) DriverManager.getConnection (dbURL, dbUserName, dbpwd);
			statement = (Statement) connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		fillingStations = new ArrayList<String>();
		fsInfo = new ArrayList<String>();
		telDirectory = new ArrayList<String>();
		diameter = 1.0f;
	}
	
	public ArrayList<String> selectFS(float lat, float lang){
		lowerLat = lat - diameter;
		upperLat = lat + diameter;
		lowerLang = lang - diameter;
		upperLang = lang + diameter;
		String temp = "";
		
		query = "SELECT * FROM fsis_db.fs WHERE latitude BETWEEN " + lowerLat + " AND " + upperLat + " AND longtitude BETWEEN " + lowerLang + " AND " + upperLang + ";" ;
		System.out.println(query);
		try {
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()){
				temp = resultSet.getInt("id") + " " + resultSet.getString("location") + " " + resultSet.getString("stationType") + " " + resultSet.getFloat("latitude") + " " + resultSet.getFloat("longtitude") + " " + resultSet.getString("address");
				fillingStations.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fillingStations;
	}

	
	
	public ArrayList<String> selectFSInfo(int id){
		String temp = "";
		query = "SELECT fueltypes FROM fsis_db.fs_info WHERE id=" + id;
		System.out.println(query);
		try {
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()){
				temp = resultSet.getString("fueltypes");
				fsInfo.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fsInfo;
	}
	
	public ArrayList<String> selectTelNo(int id){
		String temp = "";
		query = "SELECT telNo FROM fsis_db.fs_telNo WHERE id=" + id;
		System.out.println(query);
		try {
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()){
				temp = resultSet.getString("telNo");
				telDirectory.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return telDirectory;
	}
	
	public String selectPrices(){
		String temp = "", result="";
		query = "SELECT * FROM fsis_db.prices";
		System.out.println(query);
		try {
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()){
				temp = resultSet.getString("fueltypes");
				result += temp + " ";
				result += resultSet.getFloat("price") + ";";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void setDiameter(float diameter) {
		System.out.println("third");
		this.diameter = diameter;
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
