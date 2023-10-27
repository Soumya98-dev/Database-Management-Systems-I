public class client {
    protected String ClientID;
    protected String Password;
    protected String FirstName;
    protected String LastName;
    protected String Address;
    protected String CreditCardInfo;
    protected String PhoneNumber;
    protected String Email;

    //constructors
    public client() {
    }

    public client(String email) {
        this.Email = email;
    }

    public client(String email, String clientID, String password,String firstName, String lastName, String address, String creditCardInfo, String phoneNumber) {
        this(clientID, password, firstName, lastName, address, creditCardInfo, phoneNumber);
        this.Email = email;
    }

    public client(String clientID, String password, String firstName, String lastName, String address, String creditCardInfo, String phoneNumber) {
        this.ClientID = clientID;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.CreditCardInfo = creditCardInfo;
        this.PhoneNumber = phoneNumber;
    }

    //getter and setter methods

    public String getClientID() {
        return ClientID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
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