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
@WebServlet("/ClientRespondToQuoteResponseDAO")
public class ClientRespondToQuoteResponseDAO {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ClientRespondToQuoteResponseDAO() {
    }

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
            String sql = "select * from ClientRespondToQuoteResponse where ClientResponseID = ?";
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

    public List<ClientRespondToQuoteResponse> listAllQuoteResponse() throws SQLException {
        List<ClientRespondToQuoteResponse> listQuoteResponses = new ArrayList<ClientRespondToQuoteResponse>();
        String sql = "SELECT * FROM ClientRespondToQuoteResponse";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int clientResponseID = resultSet.getInt("ClientResponseID");
            int contractorID = resultSet.getInt("ContractorID");
            int responseID = resultSet.getInt("ResponseID");
            String responseDate = resultSet.getString("ResponseDate");
            String note = resultSet.getString("Note");

            // Assuming Status is stored as a String in the database
            String statusString = resultSet.getString("Status");
            ClientRespondToQuoteResponse.Status status = ClientRespondToQuoteResponse.Status.valueOf(statusString);

            ClientRespondToQuoteResponse clientResponse = new ClientRespondToQuoteResponse(clientResponseID, contractorID, responseID, responseDate, status, note);
            listQuoteResponses.add(clientResponse);
        }

        resultSet.close();
        disconnect();
        return listQuoteResponses;
    }

   
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    
	} 
    
    public void insert(ClientRespondToQuoteResponse clientResponse) throws SQLException {
        connect_func("root", "pass1234");
        String sql = "INSERT INTO ClientRespondToQuoteResponse ( contractorID, responseID, responseDate, status, note) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);       
        preparedStatement.setInt(1, clientResponse.getContractorID());
        preparedStatement.setInt(2, clientResponse.getResponseID());
        preparedStatement.setString(3, clientResponse.getResponseDate());
        preparedStatement.setString(4, clientResponse.getStatus().toString());
        preparedStatement.setString(5, clientResponse.getNote());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    public boolean deleteById(int ClientResponseID) throws SQLException {
        String sql = "DELETE FROM ClientRespondToQuoteResponse WHERE ResponseID = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, ClientResponseID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateClientRespondToQuoteResponse(ClientRespondToQuoteResponse clientResponse) throws SQLException {
        String sql = "UPDATE ClientRespondToQuoteResponse SET ContractorID=?, ResponseID=?, ResponseDate=?, Status=?, Note=? WHERE ResponseID=?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, clientResponse.getContractorID());
        preparedStatement.setInt(2, clientResponse.getResponseID());
        preparedStatement.setString(3, clientResponse.getResponseDate());
        preparedStatement.setString(4, clientResponse.getStatus().toString());  // Assuming Status is stored as a String in the database
        preparedStatement.setString(5, clientResponse.getNote());
        preparedStatement.setInt(6, clientResponse.getResponseID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowUpdated;
    }

    public ClientRespondToQuoteResponse getClientRespondToQuoteResponse(int clientResponseID) throws SQLException {
        ClientRespondToQuoteResponse clientResponse = null;
        String sql = "SELECT * FROM ClientRespondToQuoteResponse WHERE ClientResponseID = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, clientResponseID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            
            int contractorID = resultSet.getInt("ContractorID");
            int responseID = resultSet.getInt("ResponseID");
            String responseDate = resultSet.getString("ResponseDate");
            String statusString = resultSet.getString("Status");  // Assuming Status is stored as a String
            String note = resultSet.getString("Note");

            // Assuming you have a constructor that includes the Status parameter
            clientResponse = new ClientRespondToQuoteResponse(clientResponseID, contractorID, responseID, responseDate, ClientRespondToQuoteResponse.Status.valueOf(statusString), note);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return clientResponse;
    }


    public boolean checkClientResponseID(int ClientResponseID) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM ClientRespondToQuoteResponse WHERE ClientResponseID = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, ClientResponseID);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(checks);

        if (resultSet.next()) {
            checks = true;
        }
        
        resultSet.close();
        System.out.println(checks);
        return checks;
    }

   

    public boolean isValid(int ClientResponseID, int ContractorID) throws SQLException {
    String sql = "SELECT * FROM ClientRespondToQuoteResponse WHERE ClientResponseID = ? AND ContractorID = ?";
    connect_func();

    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    preparedStatement.setInt(1, ClientResponseID);
    preparedStatement.setInt(2, ContractorID);

    ResultSet resultSet = preparedStatement.executeQuery();
    boolean isValid = resultSet.next();  // Check if a matching record is found

    resultSet.close();
    preparedStatement.close();
    disconnect();

    return isValid;
}


    public void init() throws SQLException {
        connect_func();
        statement = connect.createStatement();

        String[] INITIAL = {
            "drop database if exists project1;",
            "create database Project1;",
            "use Project1;",
            "drop table if exists ClientRespondToQuoteResponse;",
            "CREATE TABLE if not exists ClientRespondToQuoteResponse ( " +
            "ClientResponseID INT AUTO_INCREMENT PRIMARY KEY, " +
            "ContractorID INT, " +
            "ResponseID INT, " +
            "ResponseDate DATE, " +
            "Status ENUM('Accepted', 'Rejected', 'Pending', 'RequestRevision'), " +
            "Note VARCHAR(255), " +
            "FOREIGN KEY (ResponseID) REFERENCES QuoteResponse(ResponseID), " +
            "FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID)" +
            ");",
            "INSERT INTO ClientRespondToQuoteResponse (ContractorID, ResponseID, ResponseDate, Status, Note) " +
            "VALUES (1, 1, '2023-09-09', 'Accepted', 'sg'), " +
            "(1, 2, '2023-01-01', 'Rejected', 'dfxh');"
        };

        // Loop to execute the SQL statements
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);

        disconnect();
    }

}
    