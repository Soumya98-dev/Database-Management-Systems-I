public class QuoteRequest {
    protected int RequestID;
    protected int ClientID;   
    protected String RequestDate;
    protected String Status;
    protected String Note;
    protected String Size;
    protected double Height;
    protected String Location;
    protected double ProximityToHouse;

    // Constructors
    public QuoteRequest() {
    }

    public QuoteRequest(String note) {
        this.Note = note;
    }

   
    public QuoteRequest (int clientID, String requestDate, String status, String note, String size, double height, String location, double proximityToHouse) {
    	this.ClientID = clientID;
        this.RequestDate = requestDate;
        this.Status = status;
        this.Note = note;
        this.Size = size;
        this.Height = height;
        this.Location = location;
        this.ProximityToHouse = proximityToHouse;
    }
    
    
    public QuoteRequest(int requestID, int clientID,  String requestDate, String status, String note, String size, double height, String location, double proximityToHouse) {
        this.RequestID = requestID;
        this.ClientID = clientID;       
        this.RequestDate = requestDate;
        this.Status = status;
        this.Note = note;
        this.Size = size;
        this.Height = height;
        this.Location = location;
        this.ProximityToHouse = proximityToHouse;
    }
    
    



    // Getter and setter methods
    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int requestID) {
        RequestID = requestID;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClient_ID(int clientID) {
        ClientID = clientID;
    }

   

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public double getProximityToHouse() {
        return ProximityToHouse;
    }

    public void setProximityToHouse(double proximityToHouse) {
        ProximityToHouse = proximityToHouse;
    }
}
