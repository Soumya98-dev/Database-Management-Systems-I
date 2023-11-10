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

import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Connect
 */

@WebServlet("/ContractorRespondToClientResponseDAO")
public class ContractorRespondToClientResponseDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public ContractorRespondToClientResponseDAO() {
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void connect_func() throws SQLException {
		// uses default connection to the database
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
			String sql = "select * from ContractorRespondToClientResponseDAO where ContractorResponseID = ?";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("failed login");
			return false;
		}
	}

	// connect to the database
	public void connect_func(String username, String password) throws SQLException {
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project1?"
					+ "useSSL=false&user=" + username + "&password=" + password);
			System.out.println(connect);
		}
	}

	public List<ContractorRespondToClientResponse> listAllClientResponse() throws SQLException {
		List<ContractorRespondToClientResponse> listClientResponses = new ArrayList<ContractorRespondToClientResponse>();
		String sql = "SELECT * FROM ContractorRespondToClientResponse";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int contractorResponseID = resultSet.getInt("ContractorResponseID");
			int contractorID = resultSet.getInt("ContractorID");
			int clientResponseID = resultSet.getInt("ClientResponseID");
			String responseDate = resultSet.getString("ResponseDate");
			String note = resultSet.getString("Note");
			double modifiedPrice = resultSet.getDouble("ModifiedPrice");
			String modifiedWorkPeriodFrom = resultSet.getString("ModifiedWorkPeriodFrom");
			String modifiedWorkPeriodTo = resultSet.getString("ModifiedWorkPeriodTo");

			// Assuming Status is stored as a String in the database
			String statusString = resultSet.getString("Status");
			ContractorRespondToClientResponse.Status status = ContractorRespondToClientResponse.Status.valueOf(statusString);

			ContractorRespondToClientResponse contractorResponse = new ContractorRespondToClientResponse(contractorResponseID, contractorID, clientResponseID, responseDate, status, note, modifiedPrice,modifiedWorkPeriodFrom, modifiedWorkPeriodTo);
			listClientResponses.add(contractorResponse);
		}

		resultSet.close();
		disconnect();
		return listClientResponses;
	}

	private void disconnect() {
		// TODO Auto-generated method stub

	}

	public void insert(ContractorRespondToClientResponse contractorResponse) throws SQLException {
		connect_func("root", "pass1234");
		String sql = "INSERT INTO ContractorRespondToClientResponse ( contractorID, clientResponseID, responseDate, status, note, modifiedPrice,modifiedWorkPeriodFrom, modifiedWorkPeriodTo) VALUES (?, ?, ?, ?, ?, ?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		preparedStatement.setInt(1, contractorResponse.getContractorID());
		preparedStatement.setInt(2, contractorResponse.getClientResponseID());
		preparedStatement.setString(3, contractorResponse.getResponseDate());
		preparedStatement.setString(4, contractorResponse.getStatus().toString());
		preparedStatement.setString(5, contractorResponse.getNote());
		preparedStatement.setDouble(6, contractorResponse.getModifiedPrice());
		preparedStatement.setString(7, contractorResponse.getModifiedWorkPeriodFrom());
		preparedStatement.setString(8, contractorResponse.getModifiedWorkPeriodTo());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public boolean deleteById(int ContractorResponseID) throws SQLException {
		String sql = "DELETE FROM ContractorRespondToClientResponse WHERE ContractorResponseID = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, ContractorResponseID);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(ContractorRespondToClientResponse contractorResponse) throws SQLException {
		String sql = "UPDATE ContractorRespondToClientResponse SET ContractorResponseID=?, ContractorID=?, ClientResponseID=?, ResponseDate=?, Status=?, Note=?,ModifiedPrice=?,ModifiedWorkPeriodFrom=?,ModifiedWorkPeriodTo=? WHERE ResponseID=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		preparedStatement.setInt(1, contractorResponse.getContractorResponseID());
		preparedStatement.setInt(2, contractorResponse.getContractorID());
		preparedStatement.setInt(3, contractorResponse.getClientResponseID());
		preparedStatement.setString(4, contractorResponse.getResponseDate());
		preparedStatement.setString(5, contractorResponse.getStatus().toString());
		preparedStatement.setString(6, contractorResponse.getNote());
		preparedStatement.setDouble(7, contractorResponse.getModifiedPrice());
		preparedStatement.setString(8, contractorResponse.getModifiedWorkPeriodFrom());
		preparedStatement.setString(9, contractorResponse.getModifiedWorkPeriodTo());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		return rowUpdated;
	}

	public ContractorRespondToClientResponse getContractorRespondToClientResponse(int contractorResponseID)
			throws SQLException {
		ContractorRespondToClientResponse contractorResponse = null;
		String sql = "SELECT * FROM ContractorRespondToClientResponse WHERE ContractorResponseID = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, contractorResponseID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			
			int contractorID = resultSet.getInt("ContractorID");
			int clientResponseID = resultSet.getInt("ClientResponseID");
			String responseDate = resultSet.getString("ResponseDate");
			String statusString = resultSet.getString("Status"); // Assuming Status is stored as a String
			String note = resultSet.getString("Note");
			double modifiedPrice = resultSet.getDouble("ModifiedPrice");
			String modifiedWorkPeriodFrom = resultSet.getString("ModifiedWorkPeriodFrom");
			String modifiedWorkPeriodTo = resultSet.getString("ModifiedWorkPeriodTo");

			// Assuming you have a constructor that includes the Status parameter
			contractorResponse = new ContractorRespondToClientResponse(contractorResponseID, contractorID,clientResponseID, responseDate, ContractorRespondToClientResponse.Status.valueOf(statusString),
					note, modifiedPrice, modifiedWorkPeriodFrom, modifiedWorkPeriodTo);
		}

		resultSet.close();
		preparedStatement.close();
		disconnect();

		return contractorResponse;
	}

	public boolean checkContractorResponseID(int ContractorResponseID) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM ContractorRespondToClientResponse WHERE ContractorResponseID = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, ContractorResponseID);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean isValid(int ContractorResponseID, int contractorID) throws SQLException {
		String sql = "SELECT * FROM ContractorRespondToClientResponse WHERE ContractorResponseID = ? AND ContractorID = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, ContractorResponseID);
		preparedStatement.setInt(2, contractorID);

		ResultSet resultSet = preparedStatement.executeQuery();
		boolean isValid = resultSet.next(); // Check if a matching record is found

		resultSet.close();
		preparedStatement.close();
		disconnect();

		return isValid;
	}

	public void init() throws SQLException, FileNotFoundException, IOException {
		connect_func();
		statement = (Statement) connect.createStatement();

		String[] INITIAL = { 
				"drop database if exists project1; ", 
				"create database Project1; ", "use Project1; ",
				"drop table if exists ContractorRespondToClientResponse; ",
				"CREATE TABLE if not exists ContractorRespondToClientResponse ( "
						+ "ContractorResponseID INT AUTO_INCREMENT PRIMARY KEY, " + "ContractorID INT, "
						+ "ClientResponseID INT, "
						+ "Status ENUM('Accepted', 'Rejected', 'Pending', 'RequestRevision')," + "Note VARCHAR(255), "
						+ "ModifiedPrice DOUBLE," + "ModifiedWorkPeriodFrom DATE," + "ModifiedWorkPeriodTo DATE,"
						+ "FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID),"
						+ "FOREIGN KEY (ClientResponseID) REFERENCES ClientRespondToQuoteResponse(ClientResponseID),"
						+ "); " };

		// Insert initial data
		String[] TUPLES = {
				"INSERT INTO ContractorRespondToClientResponse ( ContractorID, ClientResponseID, ResponseDate, Status, Note,ModifiedPrice,ModifiedWorkPeriodFrom,ModifiedWorkPeriodTo) "
						+ "VALUES (1, 3, 'Accepted', '2022-12-29','sg',42.2,'2023-01-01', '2023-01-02'), "
						+ "(1, 4, 'Accepted','2022-12-29',  'sg',42.2,'2023-01-01', '2023-01-02');" };

		// Execute statements

		// For loop to execute these SQL statements
		for (int i = 0; i < INITIAL.length; i++) {
			statement.execute(INITIAL[i]);
		}

		for (int i = 0; i < TUPLES.length; i++) {
			statement.execute(TUPLES[i]);
		}

		disconnect();
	}
}
