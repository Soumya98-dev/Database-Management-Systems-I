public class contractor 
{
	    protected int ContractorID;	   
	    protected String Username;
	    protected String Password;
	    protected String Email;

	    public contractor() {
	    }
	 
	     
	    public contractor(String username,  String password, String email)
	    {	    	
	    	
	    	this.Username = username;
	    	this.Password = password;
	    	this.Email = email;
	    }
	   
	    public contractor(int contractorID ,String username,  String password , String email)
	    {
	    	this.ContractorID = contractorID;	    	
	    	this.Username = username;
	    	this.Password = password;
	    	this.Email = email;
	    }
	 
	
	   
	   //getter and setter methods
	   	    
	    
	    public int getContractorID() {
		    return ContractorID;
		}
		public void setContractorID(int contractorID) {
		    this.ContractorID =  contractorID;
		}
	  
		public String getUsername() {
		    return Username;
		}
		public void setUsername(String username) {
		    this.Username = username;
		}
		public String getPassword() {
		    return Password;
		}
		public void setPassword(String password) {
		    this.Password = password;
		}
		  public String getEmail() {
			 return Email;
		}
		public void setEmail(String email) {
			 this.Email =  email;
		}
	    
	    	    
		
	    
	    
}
	    