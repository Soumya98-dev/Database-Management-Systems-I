import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/QuoteRequestDAO")
public class QuoteRequestDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public QuoteRequestDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
	 protected void connect_func() throws SQLException {
	        //uses default connection to the database
	        if (connect == null || connect.isClosed()) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Project1?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
	            System.out.println(connect);
	        }
	    }
	 	
		    
	 public boolean database_login(String email, String password) throws SQLException {
		    try {
		        connect_func("root", "pass1234");
		        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ?";
		        preparedStatement = connect.prepareStatement(sql);
		        preparedStatement.setString(1, email);
		        ResultSet rs = preparedStatement.executeQuery();
		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace(); // Log the exception for debugging purposes
		        return false;
		    } finally {
		        disconnect();
		    }
		}

    
  //connect to the database
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1:3306/project1?"
                            + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<QuoteRequest> listAllRequests() throws SQLException {
        List<QuoteRequest> listRequests = new ArrayList<QuoteRequest>();
        String sql = "SELECT * FROM QuoteRequest"; // Corrected the table name to QuoteRequest
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int requestID = resultSet.getInt("RequestID");
            int clientID = resultSet.getInt("ClientID");          
            String requestDate = resultSet.getString("RequestDate");
            String status = resultSet.getString("Status");
            String note = resultSet.getString("Note");
            String size = resultSet.getString("Size");
            double height = resultSet.getDouble("Height");
            String location = resultSet.getString("Location");
            double proximityToHouse = resultSet.getDouble("ProximityToHouse");

            QuoteRequest request = new QuoteRequest(requestID, clientID, requestDate, status, note, size, height, location, proximityToHouse);
            listRequests.add(request);
        }
        resultSet.close();
        disconnect();
        return listRequests;
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(QuoteRequest request) throws SQLException {
        connect_func("root", "pass1234");
        String sql = "INSERT INTO QuoteRequest (ClientID, RequestDate, Status, Note, Size, Height, Location, ProximityToHouse) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, request.getClientID());
        preparedStatement.setString(2, request.getRequestDate());
        preparedStatement.setString(3, request.getStatus());
        preparedStatement.setString(4, request.getNote());
        preparedStatement.setString(5, request.getSize());
        preparedStatement.setDouble(6, request.getHeight());
        preparedStatement.setString(7, request.getLocation());
        preparedStatement.setDouble(8, request.getProximityToHouse());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public boolean delete(int RequestID) throws SQLException {
        String sql = "DELETE FROM QuoteRequest WHERE RequestID = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

     
    public boolean updateQuoteRequest(QuoteRequest quoteRequest) throws SQLException {
        String sql = "UPDATE QuoteRequest SET ClientID=?, RequestDate=?, Status=?, Note=?, Size=?, Height=?, Location=?, ProximityToHouse=? WHERE RequestID=?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteRequest.getClientID());       
        preparedStatement.setString(2, quoteRequest.getRequestDate());
        preparedStatement.setString(3, quoteRequest.getStatus());
        preparedStatement.setString(4, quoteRequest.getNote());
        preparedStatement.setString(5, quoteRequest.getSize());
        preparedStatement.setDouble(6, quoteRequest.getHeight());
        preparedStatement.setString(7, quoteRequest.getLocation());
        preparedStatement.setDouble(8, quoteRequest.getProximityToHouse());
        preparedStatement.setInt(9, quoteRequest.getRequestID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }

    
    public QuoteRequest getQuoteRequest(int requestID) throws SQLException {
        QuoteRequest quoteRequest = null;
        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, requestID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int clientID = resultSet.getInt("ClientID");            
            String requestDate = resultSet.getString("RequestDate");
            String status = resultSet.getString("Status");
            String note = resultSet.getString("Note");
            String size = resultSet.getString("Size");
            double height = resultSet.getDouble("Height");
            String location = resultSet.getString("Location");
            double proximityToHouse = resultSet.getDouble("ProximityToHouse");

            quoteRequest = new QuoteRequest(requestID, clientID, requestDate, status, note, size, height, location, proximityToHouse);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect(); // Close the connection after use

        return quoteRequest;
    }


    
    public boolean checkRequestID(int RequestID) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            checks = true;
        }

        resultSet.close();
        preparedStatement.close();
        return checks;
    }

    
    
    
    
    public boolean isValid(int RequestID, int ClientID) throws SQLException {
        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ? AND ClientID = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);
        preparedStatement.setInt(2, ClientID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean isValid = resultSet.next(); // Check if a matching record is found

        resultSet.close();
        preparedStatement.close();
        return isValid;
    }

    
    public void init() throws SQLException, FileNotFoundException, IOException {
        connect_func();
        statement = (Statement) connect.createStatement();

        String[] INITIAL = {
            "drop database if exists project1;",
            "create database Project1;",
            "use Project1;",
            "drop table if exists QuoteRequest;",
            "CREATE TABLE if not exists QuoteRequest ( " +
            "RequestID INT AUTO_INCREMENT PRIMARY KEY, " +
            "ClientID INT NOT NULL, " +
            "FOREIGN KEY (ClientID) REFERENCES Client (ClientID) ON DELETE CASCADE, " +
            "RequestDate DATE," +
            "Status VARCHAR(100)," +
            "Note VARCHAR(200)," +
            "Size VARCHAR(50)," +
            "Height DOUBLE," +
            "Location VARCHAR(100)," +
            "ProximityToHouse DOUBLE" +
            ");"
        };
        
    

        String[] TUPLES = {
            "( 1,'2022-11-10', 'Ok condition', 'Need somefjj', 'Large', 12.5, 'backyard', 5.3)," +
            "( 2,'2022-11-12', 'Good condition', 'tata', 'Medium', 9.2, 'frontyard', 2.7),"+
            "( 3, '2022-12-10', 'Ok condition', 'brat', 'Small', 7.8, 'frontyard', 1.5),"+
            "( 4,'2022-12-01', 'Great condition', 'sestra', 'Medium', 8.6, 'backyard', 3.2),"+
            "( 5, '2023-08-12', 'Better condition', 'baka', 'Large', 11.4, 'backyard', 4.7),"+
            "( 6, '2023-06-02', 'Good condition', 'ides', 'Small', 6.9, 'frontyard', 1.8),"+
            "( 7, '2023-06-04', 'Better condition', 'lolas', 'Large', 10.1, 'frontyard', 6.5),"+
            "( 8,'2023-03-12', 'Great condition', 'samo ne tu', 'Medium', 9.8, 'frontyard', 2.1),"+           
            "( 9,'2023-03-20', 'Excellent condition', 'Lily Road', 'Large', 12.0, 'backyard', 5.7);"};

        // Loop to execute the SQL statements
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)
            statement.execute("INSERT INTO QuoteRequest (ClientID, RequestDate, Status, Note, Size, Height, Location, ProximityToHouse) VALUES " + TUPLES[i]);
        disconnect();
    }

   
    
    
    
    
    
	
	

}
