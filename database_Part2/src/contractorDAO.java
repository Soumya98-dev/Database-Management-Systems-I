import java.io.FileNotFoundException;
import java.io.IOException;
// import java.io.PrintWriter;
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
@WebServlet("/contractorDAO  ")
public class contractorDAO {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public contractorDAO() {
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
            String sql = "SELECT * FROM Contractor WHERE ContractorID = ?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("failed login");
            return false;
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
                    .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
                            + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }

    public List<contractor> listAllContractor() throws SQLException {
        List<contractor> listContractor = new ArrayList<contractor>();
        String sql = "SELECT * FROM Contractor";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
        	int contractorID = resultSet.getInt("ContractorID");           
            String username = resultSet.getString("Username");
            String password = resultSet.getString("Password");
            String email = resultSet.getString("Email");


            contractor Contractor = new contractor(contractorID, username, password, email);
            listContractor.add(Contractor);
        }
        resultSet.close();
        disconnect();
        return listContractor;
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    public void insert(contractor Contractor) throws SQLException {
        connect_func("root", "pass1234");
        String sql = "INSERT INTO Contractor (Username, Password, Email) VALUES (?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

        preparedStatement.setString(1, Contractor.getUsername());
        preparedStatement.setString(2, Contractor.getPassword());
        preparedStatement.setString(3, Contractor.getEmail());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public boolean delete(String Email) throws SQLException {
        String sql = "DELETE FROM Contractor WHERE Email = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

    public boolean update(contractor Contractor) throws SQLException {
        String sql = "update Contractor set Username=?, Password=? , ContractorID = ? where Email = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, Contractor.getContractorID());       
        preparedStatement.setString(2, Contractor.getUsername());
        preparedStatement.setString(3, Contractor.getPassword());
        preparedStatement.setString(4, Contractor.getEmail());


        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }

    public contractor getContractor(int contractorID) throws SQLException {
        contractor contractor = null;
        String sql = "SELECT * FROM Contractor WHERE contractorID = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, contractorID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String username = resultSet.getString("Username");
            String password = resultSet.getString("Password");
            String email = resultSet.getString("Email");

            contractor = new contractor(1, username, password, email);
        }

        resultSet.close();
        preparedStatement.close();  // Close the preparedStatement instead of statement

        return contractor;
    }


    public boolean checkEmail(String Email) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM Contractor WHERE Email = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(checks);

        if (resultSet.next()) {
            checks = true;
        }

        System.out.println(checks);
        return checks;
    }

    public boolean checkPassword(String password) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM Contractor WHERE ContractorID = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(checks);

        if (resultSet.next()) {
            checks = true;
        }

        System.out.println(checks);
        return checks;
    }


    public boolean isValid(String Email, String Password) throws SQLException {
        String sql = "SELECT * FROM Contractor";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.last();

        int setSize = resultSet.getRow();
        resultSet.beforeFirst();

        for (int i = 0; i < setSize; i++) {
            resultSet.next();
            if (resultSet.getString("Email").equals(Email) && resultSet.getString("Password").equals(Password)) {
                return true;
            }
        }
        return false;
    }


    public void init() throws SQLException, FileNotFoundException, IOException {
        connect_func();
        statement = (Statement) connect.createStatement();

        String[] INITIAL = {"drop database if exists project1;",
                "create database Project1; ",
                "use Project1; ",
                "drop table if exists Contractor; ",
                ("CREATE TABLE if not exists Contractor( " +
                		"ContractorID  INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +                          
                        "Username VARCHAR(50) NOT NULL, " +
                        "Password VARCHAR(50) NOT NULL, " +
                        "Email VARCHAR(10) NOT NULL, " +"); ")
                      
        };
        String[] TUPLES = {("insert into Contractor(Email, Username, Password)" +
                "values ('David@gmail.com', 'David', 'DC1000');")
        };

        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)
            statement.execute(TUPLES[i]);
        disconnect();
    }

    
	
	

}
