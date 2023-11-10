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
//import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class Connect
 */
@WebServlet("/administratorDAO ")
public class administratorDAO {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public administratorDAO() {
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
            String sql = "select * from Administrator where AdminID = ?";
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

    public List<administrator> listAllAdministrator() throws SQLException {
        List<administrator> listAdministrator = new ArrayList<administrator>();
        String sql = "SELECT * FROM Administrator";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int adminID = resultSet.getInt("AdminID");
            String username = resultSet.getString("Username");
            String password = resultSet.getString("Password");
            String email = resultSet.getString("Email");


            administrator Admin = new administrator(adminID, username, password, email);
            listAdministrator.add(Admin);
        }
        resultSet.close();
        disconnect();
        return listAdministrator;
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    public void insert(administrator Admin) throws SQLException {
        connect_func("root", "pass1234");
        String sql = "insert into Administrator(Username, Password, Email ) values ( ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        
        preparedStatement.setString(1, Admin.getUsername());
        preparedStatement.setString(2, Admin.getPassword());
        preparedStatement.setString(3, Admin.getEmail());


        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    public boolean delete(String Email) throws SQLException {
        String sql = "DELETE FROM Administrator WHERE Email = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

    public boolean update(administrator admin) throws SQLException {
        String sql = "update Administrator set Username=?, Password=?,  AdminID = ? where Email = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, admin.getAdminID());
        preparedStatement.setString(2, admin.getUsername());
        preparedStatement.setString(3, admin.getPassword());
        preparedStatement.setString(4, admin.getEmail());


        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }

    public administrator getAdministrator(int adminID) throws SQLException {
        administrator admin = null;
        String sql = "SELECT * FROM Administrator WHERE AdminID = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, adminID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            String username = resultSet.getString("Username");
            String password = resultSet.getString("Password");
            String email = resultSet.getString("Email");


			admin = new administrator(adminID, username, password, email);
        }

        resultSet.close();
        statement.close();

        return admin;
    }

    public boolean checkEmail(String Email) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM Administrator WHERE Email = ?";
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

    public boolean checkPassword(String Password) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM Administrator WHERE AdminID = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Password);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(checks);

        if (resultSet.next()) {
            checks = true;
        }

        System.out.println(checks);
        return checks;
    }


    public boolean isValid(String Email, String Password) throws SQLException {
        String sql = "SELECT * FROM Administrator";
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
                "drop table if exists Administrator; ",
                ("CREATE TABLE if not exists Administrator( " +
                        "AdminID VARCHAR(50) NOT NULL, " +
                        "Username VARCHAR(50) NOT NULL, " +
                        "Password VARCHAR(50) NOT NULL, " +
                        "Email VARCHAR(50)," +
                        "Primary KEY (AdminID) " + "); ")
        };
        String[] TUPLES = {("insert into Administrator(Username, Password, Email)" +
                "values (''Cummis', 'DC1000', 'Cu@gmail.com')," +           
                "(  'Shone', 'Sh234', 'SH@gmail.com'),")
             
        };

        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)
            statement.execute(TUPLES[i]);
        disconnect();
    }

    
	
	

}
