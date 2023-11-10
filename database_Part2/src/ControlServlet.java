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
//import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private administratorDAO administratorDAO = new administratorDAO();
    private String currentAdministrator;
    private clientDAO clientDAO = new clientDAO();
    private String currentClient;
    private contractorDAO contractorDAO = new contractorDAO();
    private String currentContractor;
    private QuoteRequestDAO QuoteRequestDAO = new QuoteRequestDAO(); 
    private String currentQuoteRequest;  
    private QuoteResponseDAO QuoteResponseDAO = new QuoteResponseDAO(); 
    private String currentQuoteResponse;
    private ClientRespondToQuoteResponseDAO ClientRespondToQuoteResponseDAO = new ClientRespondToQuoteResponseDAO(); 
    private String currentClientRespondToQuoteResponse;
    private ContractorRespondToClientResponseDAO ContractorRespondToClientResponseDAO = new ContractorRespondToClientResponseDAO(); 
    private String currentContractorRespondToClientResponseDAO;
   
    private HttpSession session = null;

    public ControlServlet() {

    }

    public void init() {
        clientDAO = new clientDAO();
        setCurrentClient("");
        
        administratorDAO = new administratorDAO();
        setCurrentAdministrator("");
        
        contractorDAO = new contractorDAO();
        setCurrentContractor("");
        
        QuoteRequestDAO = new  QuoteRequestDAO();
        setCurrentQuoteRequest("");
        
        QuoteResponseDAO = new  QuoteResponseDAO();
        setCurrentQuoteResponse("");
        
        ClientRespondToQuoteResponseDAO = new  ClientRespondToQuoteResponseDAO();
        setCurrentClientRespondToQuoteResponse ("");
        
        ContractorRespondToClientResponseDAO = new ContractorRespondToClientResponseDAO();
        setCurrentContractorRespondToClientResponse ("");
        
       
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
                    try {
                        // Initialize client data
                        List<client> clients = clientDAO.listAllClients();
                        request.setAttribute("listClient", clients);
                        System.out.println("Client data successfully initialized!");

                        // Initialize administrator data
                        List<administrator> Admin = administratorDAO.listAllAdministrator();
                        request.setAttribute("listAdministrator", Admin);
                        System.out.println("Administrator data successfully initialized!");
                        
                        // Initialize contractor data
                        List<contractor> contractor = contractorDAO.listAllContractor();
                        request.setAttribute("listContractor", contractor );
                        System.out.println("Contractor data successfully initialized!");

                        // Call rootPage to forward the request to the view                      
                        request.getRequestDispatcher("rootView.jsp").forward(request, response);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Handle any potential database-related exceptions here
                    }
                    break;

                case "/root":
                    request.getRequestDispatcher("rootView.jsp").forward(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                
                case "/RequestQuote":
                    requestQuote(request, response);
                    request.getRequestDispatcher("QuoteRequest.jsp").forward(request, response);
                    break;

                    
                case "/ResponseQuote":
                	responseQuote(request, response);
                    request.getRequestDispatcher("QuoteResponse.jsp").forward(request, response);
                    break;
                    
                case "/ClientResponse":
                    clientResponse(request, response);
                    request.getRequestDispatcher("ClientRespondToQuoteResponse.jsp").forward(request, response);
                    break;
                    
                case "/ContractorResponse":
                    contractorResponse(request, response); 
                    request.getRequestDispatcher("ContractorRespondToClientResponse.jsp").forward(request, response);
                    break;

                    
                case "/ClientList":            	   
					  List<client> clients = clientDAO.listAllClients();
					  request.setAttribute("listClient", clients);
					  request.getRequestDispatcher("ClientList.jsp").forward(request, response);
					  System.out.println("Client data successfully initialized!");		
					  break;

                case "/QuoteRequest":                   
                	List<QuoteRequest> requests = QuoteRequestDAO.listAllRequests();
                	 request.setAttribute("listRequests", requests); 					 
                    request.getRequestDispatcher("QuoteRequest.jsp").forward(request, response);
                    System.out.println("QuoteRequest data successfully initialized!");
                    break;  
                              
               
                case "/QuoteResponse":                   
                	List<QuoteResponse> responses = QuoteResponseDAO.listAllResponses();
                    request.setAttribute("listResponses", responses ); 					 
                    request.getRequestDispatcher("QuoteResponse.jsp").forward(request, response);
                    System.out.println("QuoteResponse data successfully initialized!");
                    break;
                    
                    
                    
                case "/ClientRespondToQuoteResponse":
                    List<ClientRespondToQuoteResponse> clientResponses = ClientRespondToQuoteResponseDAO.listAllQuoteResponse();
                    request.setAttribute("listQuoteResponses", clientResponses);                  
                    
                    request.getRequestDispatcher("ClientRespondToQuoteResponse.jsp").forward(request, response);                    
                    System.out.println("ClientResponse data successfully initialized!");
                    break;
                    
                case "/ContractorRespondToClientResponse":                   
                      List<ContractorRespondToClientResponse> contractorResponses = ContractorRespondToClientResponseDAO.listAllClientResponse();                     
                      request.setAttribute("listClientResponses", contractorResponses);                      
                      request.getRequestDispatcher("ContractorRespondToClientResponse.jsp").forward(request, response);                      
                      System.out.println("ContractorResponse data successfully initialized!");                  
                       break;


					  
                                                                        
              	 
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

            
    
    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
        System.out.println("root view");
        
        // Set the client data in the request attribute
        request.setAttribute("listClient", clientDAO.listAllClients());
        
        // Set the administrator data in the request attribute
        request.setAttribute("listAdministrator", administratorDAO.listAllAdministrator());
        
        request.setAttribute("listContractor", contractorDAO.listAllContractor());
        
        request.getRequestDispatcher("rootView.jsp").forward(request, response);
    }
  
        
	
    private void QuoteRequest(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
    	List<QuoteRequest> requests = QuoteRequestDAO.listAllRequests();
    	System.out.println("Number of requests retrieved: " + requests.size());    	
        request.setAttribute("listRequests", QuoteRequestDAO.listAllRequests());  // Set the attribute with the retrieved data
        request.getRequestDispatcher("QuoteRequest.jsp").forward(request, response); // Forward to JSP
    }
    
    private void QuoteResponse(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException {
    	List<QuoteResponse> responses = QuoteResponseDAO.listAllResponses();
    	System.out.println("Number of responses retrieved: " + responses.size());  
    	
        request.setAttribute("listResponses", QuoteResponseDAO.listAllResponses());  // Set the attribute with the retrieved data
        request.getRequestDispatcher("QuoteResponse.jsp").forward(request, response); // Forward to JSP
    }

	 
    
    
    private void ClientRespondToQuoteResponse(HttpServletRequest request, HttpServletResponse response, String view)throws ServletException, IOException, SQLException {
        List<ClientRespondToQuoteResponse> quoteResponses = ClientRespondToQuoteResponseDAO.listAllQuoteResponse();
        System.out.println("Number of quote responses retrieved: " + quoteResponses.size());  

        request.setAttribute("listQuoteResponses", quoteResponses);  // Set the attribute with the retrieved data
        request.getRequestDispatcher("ClientRespondToQuoteResponse.jsp").forward(request, response); // Forward to JSP
    }



    public class UserRole {
        public static final String CLIENT = "Client";
        public static final String ADMINISTRATOR = "Administrator";
        public static final String CONTRACTOR = "Contractor";
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");

        // Simulated user roles (you'd get these from your user data)
        String userRole = getUserRoleByEmail(email);

        if (UserRole.ADMINISTRATOR.equals(userRole)) {            
            response.sendRedirect("administrator.jsp");
        } else if (UserRole.CLIENT.equals(userRole)) {            
            response.sendRedirect("ClientList.jsp");
        } else if (UserRole.CONTRACTOR.equals(userRole)) {           
            response.sendRedirect("contractor.jsp");
        } else {
            
            request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // Simulated method to retrieve user's role from your data store
    private String getUserRoleByEmail(String email) {
           return "Client";
    }
    
    
    private void requestQuote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("request view");

        // Get parameters from the request
        int clientID = Integer.parseInt(request.getParameter("ClientID"));
        String requestDate = request.getParameter("RequestDate");
        String status = request.getParameter("Status");
        String note = request.getParameter("Note");
        String size = request.getParameter("Size");

        // Parse the height and proximityToHouse parameters to double
        double height = Double.parseDouble(request.getParameter("Height"));
        String location = request.getParameter("Location");
        double proximityToHouse = Double.parseDouble(request.getParameter("Proximity"));

        // Print the data for debugging
        System.out.println("ClientID: " + clientID);
        System.out.println("RequestDate: " + requestDate);
        System.out.println("Status: " + status);
        System.out.println("Note: " + note);
        System.out.println("Size: " + size);
        System.out.println("Height: " + height);
        System.out.println("Location: " + location);
        System.out.println("ProximityToHouse: " + proximityToHouse);

        // Create a new QuoteRequest object with the desired values
        QuoteRequest requests = new QuoteRequest(clientID, requestDate, status, note, size, height, location, proximityToHouse);

        // Insert the QuoteRequest object into the database
        QuoteRequestDAO quoteRequestDAO = new QuoteRequestDAO();
        quoteRequestDAO.connect_func("root", "pass1234");
        quoteRequestDAO.insert(requests);

        // Set the Quote data in the request attribute
        request.setAttribute("listRequests", quoteRequestDAO.listAllRequests());

        // Disconnect from the database
        quoteRequestDAO.disconnect();

        // Forward the request to a JSP page
        request.getRequestDispatcher("QuoteRequest.jsp").forward(request, response);
    }

    
    private void responseQuote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("request view");

        // Get parameters from the request
        int requestID = Integer.parseInt(request.getParameter("RequestID"));
        int clientID = Integer.parseInt(request.getParameter("ClientID"));
        String responseDate = request.getParameter("ResponseDate");
       
       
        double price = Double.parseDouble(request.getParameter("Price"));
        String workPeriodFrom = request.getParameter("WorkPeriodFrom");
        String workPeriodTo = request.getParameter("WorkPeriodTo");
        String note = request.getParameter("Note");

     // Print the data for debugging
        System.out.println("RequestID: " + requestID);
        System.out.println("ClientID: " + clientID);
        System.out.println("ResponseDate: " + responseDate);
        System.out.println("Price: " + price);
        System.out.println("WorkPeriodFrom: " + workPeriodFrom);
        System.out.println("WorkPeriodTo: " + workPeriodTo);
        System.out.println("Note: " + note);
       
        // Create a new QuoteResponse object with the desired values
        QuoteResponse responses = new QuoteResponse(requestID, clientID, responseDate, price, workPeriodFrom, workPeriodTo, note);

        // Insert the QuoteResponse object into the database
        QuoteResponseDAO quoteResponseDAO = new QuoteResponseDAO();
        quoteResponseDAO.connect_func("root", "pass1234");
        quoteResponseDAO.insert(responses);
        quoteResponseDAO.disconnect();

        // Set the QuoteResponse data in the request attribute
        request.setAttribute("listResponses", quoteResponseDAO.listAllResponses());

        // Forward the request to a JSP page
        request.getRequestDispatcher("QuoteResponse.jsp").forward(request, response);
    }

    
    private void clientResponse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("client respond to quote response view");

        // Get parameters from the request
        int contractorID = Integer.parseInt(request.getParameter("ContractorID"));
        int responseID = Integer.parseInt(request.getParameter("ResponseID"));
        String responseDate = request.getParameter("ResponseDate");
        String status = request.getParameter("Status"); // Make sure to have an input for the status in your HTML form
        String note = request.getParameter("Note");

        // Print the data for debugging
        System.out.println("ContractorID: " + contractorID);
        System.out.println("ResponseID: " + responseID);
        System.out.println("ResponseDate: " + responseDate);
        System.out.println("Status: " + status);
        System.out.println("Note: " + note);

        // Create a new ClientRespondToQuoteResponse object with the desired values
        ClientRespondToQuoteResponse clientResponse = new ClientRespondToQuoteResponse(contractorID, responseID, responseDate, status, note);

        // Insert the ClientRespondToQuoteResponse object into the database
        ClientRespondToQuoteResponseDAO clientRespondToQuoteResponseDAO = new ClientRespondToQuoteResponseDAO();
        clientRespondToQuoteResponseDAO.connect_func("root", "pass1234");
        clientRespondToQuoteResponseDAO.insert(clientResponse);

        // Set the response data in the request attribute
        request.setAttribute("listQuoteResponses", clientRespondToQuoteResponseDAO.listAllQuoteResponse());        

        // Forward the request to a JSP page
        request.getRequestDispatcher("ClientRespondToQuoteResponse.jsp").forward(request, response);
    }

    
    private void contractorResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("contractor respond to client response view");

        // Get parameters from the request
        int contractorID = Integer.parseInt(request.getParameter("ContractorID"));
        int clientResponseID = Integer.parseInt(request.getParameter("ClientResponseID"));
        String responseDate = request.getParameter("ResponseDate");
        String status = request.getParameter("Status"); // Make sure to have an input for the status in your HTML form
        String note = request.getParameter("Note");
        double modifiedPrice = Double.parseDouble(request.getParameter("ModifiedPrice"));
        String modifiedWorkPeriodFrom = request.getParameter("ModifiedWorkPeriodFrom");
        String modifiedWorkPeriodTo = request.getParameter("ModifiedWorkPeriodTo");

        // Print the data for debugging
        System.out.println("ContractorID: " + contractorID);
        System.out.println("ClientResponseID: " + clientResponseID);
        System.out.println("ResponseDate: " + responseDate);
        System.out.println("Status: " + status);
        System.out.println("Note: " + note);
        System.out.println("ModifiedPrice: " + modifiedPrice);
        System.out.println("ModifiedWorkPeriodFrom: " + modifiedWorkPeriodFrom);
        System.out.println("ModifiedWorkPeriodTo: " + modifiedWorkPeriodTo);

        // Create a new ContractorRespondToClientResponse object with the desired values
        ContractorRespondToClientResponse contractorResponse = new ContractorRespondToClientResponse(contractorID, clientResponseID, responseDate, status, note, modifiedPrice, modifiedWorkPeriodFrom,
             modifiedWorkPeriodTo);

        // Insert the ContractorRespondToClientResponse object into the database
        ContractorRespondToClientResponseDAO contractorRespondToClientResponseDAO = new ContractorRespondToClientResponseDAO();
        contractorRespondToClientResponseDAO.connect_func("root", "pass1234");
        contractorRespondToClientResponseDAO.insert(contractorResponse);

        // Set the response data in the request attribute
        request.setAttribute("listClientResponses", contractorRespondToClientResponseDAO.listAllClientResponse());

        // Forward the request to a JSP page
        request.getRequestDispatcher("ContractorRespondToClientResponse.jsp").forward(request, response);
    }



    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String email = request.getParameter("Email");
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String password = request.getParameter("Password");
        String address = request.getParameter("Address");
        String creditCardInfo = request.getParameter("CreditCardInfo");
        String phoneNumber = request.getParameter("PhoneNumber");       
        String confirm = request.getParameter("confirmation");      
        String username = request.getParameter("FirstName");; // Set username to FirstName
        String role = request.getParameter("Role");

     // Set default values if the fields are empty
        if (address == null || address.isEmpty()) {
            address = "Default";
        }
        if (creditCardInfo == null || creditCardInfo.isEmpty()) {
            creditCardInfo = "Default";
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            phoneNumber = "Default ";
        }
        
      
       
        if (password.equals(confirm)) {
            if ("Client".equals(role)) {
                if (!clientDAO.checkEmail(email)) {
                    System.out.println("Registration Successful! Added to the database");
                    client clients = new client(email, firstName, lastName, password, address, creditCardInfo, phoneNumber);
                    clientDAO.insert(clients);
                    response.sendRedirect("login.jsp");
                } else {
                    System.out.println("Email taken, please enter a new email");
                    request.setAttribute("errorOne", "Registration failed: Email taken, please enter a new email.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            
            } else {
                System.out.println("Invalid role selected");
                request.setAttribute("errorOne", "Registration failed: Invalid role selected.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            System.out.println("Password and Password Confirmation do not match");
            request.setAttribute("errorTwo", "Registration failed: Password and Password Confirmation do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
    }


    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCurrentClient("");
        response.sendRedirect("login.jsp");
    }

	public String getCurrentAdministrator() {
		return currentAdministrator;
	}

	public void setCurrentAdministrator(String currentAdministrator) {
		this.currentAdministrator = currentAdministrator;
	}

	public String getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(String currentClient) {
		this.currentClient = currentClient;
	}
	
	
	 private void setCurrentContractor(String currentContractor) {
		 this.currentContractor = currentContractor;
			
		}
	 public String getCurrentContractor() {
			return currentContractor;
		}
	 
	 private void setCurrentQuoteRequest(String currentQuoteRequest) {
		 this.currentQuoteRequest = currentQuoteRequest;
			
		}
	 public String getCurrentQuoteRequest() {
			return currentQuoteRequest;
		}
	 
	 private void setCurrentQuoteResponse(String currentQuoteResponse) {		    
		    this.currentQuoteResponse = currentQuoteResponse;
		}
	 
	 public String getCurrentQuoteResponse() {
			return currentQuoteResponse;
		}

	 private void setCurrentClientRespondToQuoteResponse(String currentClientRespondToQuoteResponse) {		    
		    this.currentClientRespondToQuoteResponse = currentClientRespondToQuoteResponse;
		}
	 
	 public String getCurrentClientRespondToQuoteResponse() {
			return currentClientRespondToQuoteResponse;
		}

	 private void setCurrentContractorRespondToClientResponse(String response) {		    
		    this.currentClientRespondToQuoteResponse = response;
		}
	 public String getCurrentContractorRespondToClientResponse() {
			return currentClientRespondToQuoteResponse;
		}

	
	 
}

	        
	        
	    
	        
	        
	        
	    


