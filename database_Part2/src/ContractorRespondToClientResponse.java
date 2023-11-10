public class ContractorRespondToClientResponse {
    protected int ContractorResponseID;
    protected int ContractorID;
    protected int ClientResponseID;
    protected String ResponseDate;
    protected Status status; // Assuming Status is an enum
    protected String Note;
    protected double ModifiedPrice;
    protected String ModifiedWorkPeriodFrom;
    protected String ModifiedWorkPeriodTo;

    // Assuming Status is an enum
    public enum Status {
        Accepted, Rejected, Pending, RequestRevision
    }

    public ContractorRespondToClientResponse(String note) {
    	 this.Note = note;
    }

    public ContractorRespondToClientResponse(int contractorResponseID, int contractorID, int clientResponseID, String responseDate, Status status, String note, double modifiedPrice, String modifiedWorkPeriodFrom, String modifiedWorkPeriodTo) {
    	this.ContractorResponseID = contractorResponseID;
        this.ContractorID = contractorID;
        this.ClientResponseID = clientResponseID;
        this.ResponseDate = responseDate;
        this.status = status;
        this.Note = note;
        this.ModifiedPrice = modifiedPrice;
        this.ModifiedWorkPeriodFrom = modifiedWorkPeriodFrom;
        this.ModifiedWorkPeriodTo = modifiedWorkPeriodTo;
    }

    public ContractorRespondToClientResponse(int contractorID, int clientResponseID, String responseDate, String status, String note, double modifiedPrice, String modifiedWorkPeriodFrom, String modifiedWorkPeriodTo) {
    	  this.ContractorID = contractorID;
          this.ClientResponseID = clientResponseID;
          this.ResponseDate = responseDate;
          this.status = Status.valueOf(status); // Convert the status string to enum
          this.Note = note;
          this.ModifiedPrice = modifiedPrice;
          this.ModifiedWorkPeriodFrom = modifiedWorkPeriodFrom;
          this.ModifiedWorkPeriodTo = modifiedWorkPeriodTo;
    }

    public ContractorRespondToClientResponse() {
    }

    public int getContractorResponseID() {
        return ContractorResponseID;
    }

    public void setContractorResponseID(int contractorResponseID) {
        ContractorResponseID = contractorResponseID;
    }

    public int getContractorID() {
        return ContractorID;
    }

    public void setContractorID(int contractorID) {
        ContractorID = contractorID;
    }

    public int getClientResponseID() {
        return ClientResponseID;
    }

    public void setClientResponseID(int clientResponseID) {
        ClientResponseID = clientResponseID;
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

    public double getModifiedPrice() {
        return ModifiedPrice;
    }

    public void setModifiedPrice(double modifiedPrice) {
        ModifiedPrice = modifiedPrice;
    }

    public String getModifiedWorkPeriodFrom() {
        return ModifiedWorkPeriodFrom;
    }

    public void setModifiedWorkPeriodFrom(String modifiedWorkPeriodFrom) {
        ModifiedWorkPeriodFrom = modifiedWorkPeriodFrom;
    }

    public String getModifiedWorkPeriodTo() {
        return ModifiedWorkPeriodTo;
    }

    public void setModifiedWorkPeriodTo(String modifiedWorkPeriodTo) {
        ModifiedWorkPeriodTo = modifiedWorkPeriodTo;
    }
}