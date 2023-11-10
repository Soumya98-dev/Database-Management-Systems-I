public class client {
    protected int ClientID;    
    protected String FirstName;
    protected String LastName;
    protected String Password;
    protected String Address;
    protected String CreditCardInfo;
    protected String PhoneNumber;
    protected String Email;

    // constructors
    public client() {
    }

    public client(String email) {
        this.Email = email;
    }

    public client(String email, String firstName, String lastName, String password, String address, String creditCardInfo, String phoneNumber) {
        this.Email = email;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.CreditCardInfo = creditCardInfo;
        this.PhoneNumber = phoneNumber;
    }
    
  

    public client(int clientID,  String firstName, String lastName,String password, String address, String creditCardInfo, String phoneNumber, String email) {
        this.ClientID = clientID;       
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Password = password;
        this.Address = address;
        this.CreditCardInfo = creditCardInfo;
        this.PhoneNumber = phoneNumber;
        this.Email = email;
    }

    //getter and setter methods

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }
    
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCreditCardInfo() {
        return CreditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        CreditCardInfo = creditCardInfo;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}