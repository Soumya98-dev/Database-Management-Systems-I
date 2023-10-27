CREATE DATABASE Project1;
USE Project1;

# Stores information about registered clients. ClientID is generated as a unique identifier
CREATE TABLE Client
(
    Client_ID      VARCHAR(10) PRIMARY KEY,
    FirstName      VARCHAR(50),
    LastName       VARCHAR(50),
    Address        VARCHAR(200),
    CreditCardInfo VARCHAR(10),
    PhoneNumber    VARCHAR(10),
    Email          VARCHAR(50)
);

ALTER TABLE Client
ADD COLUMN Password VARCHAR(100) AFTER Client_ID;

insert into Client(Client_ID, firstName, lastName,password, address, creditCardInfo, phoneNumber, Email)
                values ( '1500','Susie ', 'Guzman','susie123', '1234 whatever street detroit MI 48202','10000123', '11111', 'susie@gmail.com'),
                ( '1502', 'Lawson', 'Lee', 'mar123', '1234 ivan street tata CO 12561','10021250', '99999','margarita@gmail.com'),
                ( '1503', 'Brady', 'Plum', 'jo123', '3214 marko street brat DU 54321','10003674', '9990','jo@gmail.com'),
                ( '1504', 'Moore', 'Mone', 'wall123','4500 frey street sestra MI 48202','10500455', '19000', 'wallace@gmail.com'), 
                ( '1505', 'Phillips', 'Zipp','ameli123','1245 m8s street baka IL 48000','12904550', '245000', 'amelia@gmail.com'),
                ( '1506', 'Pierce', 'Lucki','sophi123','2468 yolos street ides CM 24680','10034509', '234550', 'sophie@gmail.com'),
                ( '1507', 'Francis','Hawkin', 'angelo123','4680 egypt street lolas DT 13579','10075670', '23440', 'angelo@gmail.com'),
                ( '1508', 'Smith', 'Joe','rudy123','1234 sign street samo ne tu MH 09876','10006785', '34560', 'rudy@gmail.com'), 
                ( '1509', 'Stone', 'Pills','jean123','0981 snoop street kojik HW 87654','18006780', '99990', 'jeannette@gmail.com');

# Stores information about tree-cutting requests submitted by clients. The Status field indicates whether the request is pending, accepted, or rejected
CREATE TABLE TreeCuttingRequest
(
    RequestID   VARCHAR(10) PRIMARY KEY,
    Client_ID   VARCHAR(10) REFERENCES Client (Client_ID),
    RequestDate DATE,
    Status      VARCHAR(100),
    Note        VARCHAR(200)
);

# Stores details about each tree in a request, linked to the corresponding request.
CREATE TABLE TreeInfo
(
    TreeID           VARCHAR(10) PRIMARY KEY,
    RequestID        VARCHAR(10) REFERENCES TreeCuttingRequest (RequestID),
    Size             VARCHAR(10),
    Height           DECIMAL(2, 1),
    Location         VARCHAR(100),
    ProximityToHouse FLOAT
);

# Stores quotes provided by David Smith in response to client requests. The Quote table is linked to the TreeCuttingRequest table
CREATE TABLE Quote
(
    QuoteID        VARCHAR(10) PRIMARY KEY,
    RequestID      VARCHAR(10) REFERENCES TreeCuttingRequest (RequestID),
    Price          DECIMAL(2, 2),
    WorkPeriodFrom DATE,
    WorkPeriodTo   Date,
    ResponseDate   Date,
    Note           VARCHAR(200)
);

# Stores information about orders of work created when a client accepts a quote. It links to the original request and the associated quote
CREATE TABLE OrderOfWork
(
    OrderID   VARCHAR(10) PRIMARY KEY,
    RequestID VARCHAR(10) REFERENCES TreeCuttingRequest (RequestID),
    QuoteID   VARCHAR(10) REFERENCES Quote (QuoteID),
    StartDate DATE,
    EndDate   DATE,
    Status    VARCHAR(50)
);

# Stores bills generated based on completed work. It links to the order of work and indicates the status of the bill (e.g., pending, in dispute)
CREATE TABLE Bill
(
    BillID  VARCHAR(10) PRIMARY KEY,
    OrderID VARCHAR(10) REFERENCES OrderOfWork (OrderID),
    Amount  FLOAT,
    DueDate DATE,
    Status  VARCHAR(50)
);

# Stores responses from clients to quotes. It is linked to the Quote table and the Client table
CREATE TABLE ResponseToQuote
(
    ResponseToQuoteID VARCHAR(10) PRIMARY KEY,
    QuoteID           VARCHAR(10) REFERENCES Quote (QuoteID),
    Client_ID         VARCHAR(10) REFERENCES Client (Client_ID),
    ResponseDate      DATE,
    Note              VARCHAR(50)
);

# Stores responses from clients to bills. It is linked to the Bill table and the Client table
CREATE TABLE ResponseToBill
(
    ResponseToBillID VARCHAR(10) PRIMARY KEY,
    BillID           VARCHAR(10) REFERENCES Bill (BillID),
    Client_ID        VARCHAR(10) REFERENCES Client (Client_ID),
    ResponseDate     DATE,
    Note             VARCHAR(50)
);

# Stores login credentials for administrators with access to the system
CREATE TABLE Administrator
(
    AdminID  VARCHAR(10) PRIMARY KEY,
    Username VARCHAR(50),
    Password VARCHAR(50)
);

ALTER TABLE Administrator
ADD COLUMN Email VARCHAR(50) AFTER AdminID;

# Stores records of revenue generated for specific time periods
CREATE TABLE Revenue
(
    RevenueID VARCHAR(10) PRIMARY KEY,
    Date      DATE,
    Amount    FLOAT
);