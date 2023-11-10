public class administrator 
{
	    protected int  AdminID;
	    protected String Username;
	    protected String Password;
	    protected String Email;

	    public administrator() {
	    }
	 
	    public administrator( String username, String password, String email)
	    {
	    	
	    	this.Username = username;
	    	this.Password = password;
	    	this.Email = email;
	    }  
	   
	    public administrator( int adminID,String username, String password, String email)
	    {
	    	this.AdminID = adminID;
	    	this.Username = username;
	    	this.Password = password;
	    	this.Email = email;
	    }
	 
	
	   
	   //getter and setter methods
	   	    
	    public int getAdminID() {
		    return AdminID;
		}
		public void setAdminID(int adminID) {
		    this.AdminID =  adminID;
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
		    this.Email = email;
		}	    
	    	    
	    
	    
}
	    