public class ClientRespondToQuoteResponse {
    protected int ClientResponseID;
    protected int ContractorID;
    protected int ResponseID;
    protected String ResponseDate;
    protected Status status; // Assuming Status is an enum
    protected String Note;

    // Assuming Status is an enum
    public enum Status {
        Accepted, Rejected, Pending, RequestRevision
    }

    // Constructors
    public ClientRespondToQuoteResponse() {
    }

    public ClientRespondToQuoteResponse(String note) {
        this.Note = note;
    }

    public ClientRespondToQuoteResponse(int ClientResponseID, int contractorID, int ResponseID, String responseDate, Status status, String note) {
        this.ClientResponseID = ClientResponseID;
        this.ContractorID = contractorID;
        this.ResponseID = ResponseID;
        this.ResponseDate = responseDate;
        this.status = status;
        this.Note = note;
    }

    public ClientRespondToQuoteResponse(int contractorID, int ResponseID, String responseDate, String status, String note) {
       
        this.ContractorID = contractorID;
        this.ResponseID = ResponseID;
        this.ResponseDate = responseDate;
        this.status = Status.valueOf(status); // Convert the status string to enum
        this.Note = note;
    }
    
    public int getClientResponseID() {
        return ClientResponseID;
    }

    public void setClientResponseID(int clientResponseID) {
        ClientResponseID = clientResponseID;
    }

    public int getContractorID() {
        return ContractorID;
    }

    public void setContractorID(int contractorID) {
        ContractorID = contractorID;
    }

    public int getResponseID() {
        return ResponseID;
    }

    public void setResponseID(int responseID) {
        ResponseID = responseID;
    }

    public String getResponseDate() {
        return ResponseDate;
    }

    public void setResponseDate(String responseDate) {
        ResponseDate = responseDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
