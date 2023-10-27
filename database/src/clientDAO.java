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
@WebServlet("/userDAO")
public class clientDAO {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public clientDAO() {
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
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Project1?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }

    public boolean database_login(String email, String password) throws SQLException {
        try {
            connect_func("root", "pass1234");
            String sql = "select * from Client where Email = ?";
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

    public List<client> listAllClients() throws SQLException {
        List<client> listClient = new ArrayList<client>();
        String sql = "SELECT * FROM Client";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String email = resultSet.getString("Email");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String password = resultSet.getString("Password");
            String address = resultSet.getString("Address");
            String creditCardInfo = resultSet.getString("CreditCardInfo");
            String phoneNumber = resultSet.getString("PhoneNumber");
            String clientID = resultSet.getString("Client_ID");

            client clients = new client(email, clientID, password, firstName, lastName, address, creditCardInfo, phoneNumber);
            listClient.add(clients);
        }
        resultSet.close();
        disconnect();
        return listClient;
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    public void insert(client clients) throws SQLException {
        connect_func("root", "pass1234");
        String sql = "insert into client(Client_ID, Password, FirstName, LastName, Address,CreditCardInfo, PhoneNumber,Email) values (?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, clients.getClientID());
        preparedStatement.setString(2, clients.getPassword());
        preparedStatement.setString(3, clients.getFirstName());
        preparedStatement.setString(4, clients.getLastName());
        preparedStatement.setString(5, clients.getAddress());
        preparedStatement.setString(6, clients.getCreditCardInfo());
        preparedStatement.setString(7, clients.getPhoneNumber());
        preparedStatement.setString(8, clients.getEmail());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public boolean delete(String Email) throws SQLException {
        String sql = "DELETE FROM Client WHERE Email = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

    public boolean update(client clients) throws SQLException {
        String sql = "update Client set Client_ID=?, FirstName=?, LastName =?,Password = ?,Address=?, CreditCardInfo=?, PhoneNumber=? where Email = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, clients.getClientID());
        preparedStatement.setString(2, clients.getPassword());
        preparedStatement.setString(3, clients.getFirstName());
        preparedStatement.setString(4, clients.getLastName());
        preparedStatement.setString(5, clients.getAddress());
        preparedStatement.setString(6, clients.getCreditCardInfo());
        preparedStatement.setString(7, clients.getPhoneNumber());
        preparedStatement.setString(8, clients.getEmail());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }

    public client getClient(String Email) throws SQLException {
        client client = null;
        String sql = "SELECT * FROM Client WHERE Email = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String password = resultSet.getString("Password");
            String address = resultSet.getString("Address");
            String creditCardInfo = resultSet.getString("CreditCardInfo");
            String phoneNumber = resultSet.getString("PhoneNumber");
            String clientID = resultSet.getString("Client_ID");

            client = new client(Email, clientID, password, firstName, lastName, address, creditCardInfo, phoneNumber);
        }

        resultSet.close();
        statement.close();

        return client;
    }

    public boolean checkEmail(String Email) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM Client WHERE Email = ?";
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
        String sql = "SELECT * FROM Client WHERE password = ?";
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
        String sql = "SELECT * FROM Client";
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

        String[] INITIAL = {"drop database if exists Project1; ",
                "create database Project1; ",
                "use Project1; ",
                "drop table if exists Client; ",
                ("CREATE TABLE if not exists Client( " +
                        "Client_ID VARCHAR(10) NOT NULL, " +
                        "Password VARCHAR(100) NOT NULL, " +
                        "FirstName VARCHAR(50) NOT NULL, " +
                        "LastName VARCHAR(50) NOT NULL, " +
                        "Address VARCHAR(200) NOT NULL, " +
                        "CreditCardInfo VARCHAR(10) , " +
                        "PhoneNumber VARCHAR(10) , " +
                        "Email VARCHAR(50)," +
                        "PRIMARY KEY (Client_ID) " + "); ")
        };
        String[] TUPLES = {("insert into Client(Client_ID, firstName, lastName,password, address, creditCardInfo, phoneNumber, Email)" +
                "values ( '1500','Susie ', 'Guzman','susie123', '1234 whatever street detroit MI 48202','10000123', '11111', 'susie@gmail.com')," +
                "( '1502', 'Lawson', 'Lee', 'mar123', '1234 ivan street tata CO 12561','10021250', '99999','margarita@gmail.com')," +
                "( '1503', 'Brady', 'Plum', 'jo123', '3214 marko street brat DU 54321','10003674', '9990','jo@gmail.com')," +
                "( '1504', 'Moore', 'Mone', 'wall123','4500 frey street sestra MI 48202','10500455', '19000', 'wallace@gmail.com')," +
                "( '1505', 'Phillips', 'Zipp','ameli123','1245 m8s street baka IL 48000','12904550', '245000', 'amelia@gmail.com')," +
                "( '1506', 'Pierce', 'Lucki','sophi123','2468 yolos street ides CM 24680','10034509', '234550', 'sophie@gmail.com')," +
                "( '1507', 'Francis','Hawkin', 'angelo123','4680 egypt street lolas DT 13579','10075670', '23440', 'angelo@gmail.com')," +
                "( '1508', 'Smith', 'Joe','rudy123','1234 sign street samo ne tu MH 09876','10006785', '34560', 'rudy@gmail.com')," +
                "( '1509', 'Stone', 'Pills','jean123','0981 snoop street kojik HW 87654','18006780', '99990', 'jeannette@gmail.com');")
        };

        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)
            statement.execute(TUPLES[i]);
        disconnect();
    }


}
