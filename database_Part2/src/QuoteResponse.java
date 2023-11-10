public class QuoteResponse {
    protected int ResponseID;
    protected int RequestID;
    protected int ClientID;    
    protected String ResponseDate;
    protected double Price;
    protected String WorkPeriodFrom;
    protected String WorkPeriodTo;    
    protected String Note;

    //constructors
    public QuoteResponse() {
    }

    public QuoteResponse(String Note) {
        this.Note = Note;
    }

    public QuoteResponse(int requestID, int clientID, String responseDate, double price, String workPeriodFrom, String workPeriodTo, String note) {
    	this.RequestID = requestID;
        this.ClientID = clientID;
        this.ResponseDate = responseDate;
        this.Price = price;
        this.WorkPeriodFrom = workPeriodFrom;
        this.WorkPeriodTo = workPeriodTo;
        this.Note = note;
    }


    public QuoteResponse(int responseID, int requestID, int clientID, String responseDate, double price, String workPeriodFrom, String workPeriodTo,  String note) {
        this.ResponseID = responseID;
        this.RequestID = requestID;
        this.ClientID = clientID;
        this.ResponseDate = responseDate;
        this.Price = price;
        this.WorkPeriodFrom = workPeriodFrom;
        this.WorkPeriodTo = workPeriodTo;
        this.Note = note;
    }

    public static void add(QuoteResponse listQuotes) {
    }


    //getter and setter methods

    public int getResponseID() {
        return ResponseID;
    }

    public void setResponseToQuoteID(int responseID) {
        ResponseID = responseID;
    }

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

    public String getResponseDate() {
        return ResponseDate;
    }

    public void setResponseDate(String responseDate) {
        ResponseDate = responseDate;
    }
    
    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
       Price = price;
    }
    
    public String getWorkPeriodFrom() {
        return WorkPeriodFrom;
    }

    public void setWorkPeriodFrom(String workPeriodFrom) {
    	WorkPeriodFrom = workPeriodFrom;
    }
    
    public String getWorkPeriodTo() {
        return WorkPeriodFrom;
    }

    public void setWorkPeriodTo(String workPeriodTo) {
    	WorkPeriodTo = workPeriodTo;
    }


    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }



}
	    