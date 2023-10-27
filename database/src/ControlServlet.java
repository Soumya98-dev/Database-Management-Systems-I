import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private clientDAO clientDAO = new clientDAO();
    private String currentClient;
    private HttpSession session = null;

    public ControlServlet() {

    }

    public void init() {
        clientDAO = new clientDAO();
        currentClient = "";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);

        try {
            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/register":
                    register(request, response);
                    break;
                case "/initialize":
                    clientDAO.init();
                    System.out.println("Database successfully initialized!");
                    rootPage(request, response, "");
                    break;
                case "/root":
                    rootPage(request, response, "");
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/list":
                    System.out.println("The action is: list");
                    listClient(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("listClient started: 00000000000000000000000000000000000");


        List<client> listClient = clientDAO.listAllClients();
        request.setAttribute("listClient", listClient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientList.jsp");
        dispatcher.forward(request, response);

        System.out.println("listPeople finished: 111111111111111111111111111111111111");
    }

    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
        System.out.println("root view");
        request.setAttribute("listClient", clientDAO.listAllClients());
        request.getRequestDispatcher("rootView.jsp").forward(request, response);
    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");

        if (email.equals("root") && password.equals("pass1234")) {
            System.out.println("Login Successful! Redirecting to root");
            session = request.getSession();
            session.setAttribute("Username", email);
            rootPage(request, response, "");
        } else if (clientDAO.isValid(email, password)) {

            currentClient = email;
            System.out.println("Login Successful! Redirecting");
            request.getRequestDispatcher("activitypage.jsp").forward(request, response);

        } else {
            request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("Email");
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String password = request.getParameter("Password");
        String address = request.getParameter("Address");
        String creditCardInfo = request.getParameter("CreditCardInfo");
        String phoneNumber = request.getParameter("PhoneNumber");
        String clientID = request.getParameter("Client_ID");
        String confirm = request.getParameter("confirmation");

        if (password.equals(confirm)) {
            if (!clientDAO.checkEmail(email)) {
                System.out.println("Registration Successful! Added to database");
                client clients = new client(email, clientID, password, firstName, lastName, address, creditCardInfo, phoneNumber);
                clientDAO.insert(clients);
                response.sendRedirect("login.jsp");
            } else {
                System.out.println("Username taken, please enter new username");
                request.setAttribute("errorOne", "Registration failed: Username taken, please enter a new username.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            System.out.println("Password and Password Confirmation do not match");
            request.setAttribute("errorTwo", "Registration failed: Password and Password Confirmation do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        currentClient = "";
        response.sendRedirect("login.jsp");
    }


}
	        
	        
	    
	        
	        
	        
	    


