CREATE DATABASE Project1;
USE Project1;

CREATE TABLE Contractor
(
    ContractorID  INT PRIMARY KEY DEFAULT 1,       
    Username VARCHAR(50),
    Password VARCHAR(50),
    Email  VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into Contractor( Username, Password, Email)
values ( 'David', 'DC1000', 'David@gmail.com');

    

# Stores information about registered clients. ClientID is generated as a unique identifier
CREATE TABLE Client (
    ClientID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Password VARCHAR(50),
    Address VARCHAR(200),
    CreditCardInfo VARCHAR(10),
    PhoneNumber VARCHAR(10),
    Email VARCHAR(50) UNIQUE,
    UNIQUE (ClientID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


insert into Client(  FirstName, LastName, Password, Address, CreditCardInfo, PhoneNumber, Email)
values ( 'Susie ', 'Guzman','susie123', '1234 whatever street detroit MI 48202','10000123', '11111', 'susie@gmail.com'),
('Lawson', 'Lee', 'mar123', '1234 ivan street tata CO 12561','10021250', '99999','margarita@gmail.com'),
('Brady', 'Plum', 'jo123', '3214 marko street brat DU 54321','10003674', '9990','jo@gmail.com'),
('Moore', 'Mone', 'wall123','4500 frey street sestra MI 48202','10500455', '19000', 'wallace@gmail.com'),
('Phillips', 'Zipp','ameli123','1245 m8s street baka IL 48000','12904550', '245000', 'amelia@gmail.com'),
('Pierce', 'Lucki','sophi123','2468 yolos street ides CM 24680','10034509', '234550', 'sophie@gmail.com'),
('Francis','Hawkin', 'angelo123','4680 egypt street lolas DT 13579','10075670', '23440', 'angelo@gmail.com'),
('Smith', 'Joe','rudy123','1234 sign street samo ne tu MH 09876','10006785', '34560', 'rudy@gmail.com'),
('Stone', 'Pills','jean123','0981 snoop street kojik HW 87654','18006780', '99990', 'jeannette@gmail.com');

select* from Client;
 

# Stores information about tree-cutting requests submitted by clients. The Status field indicates whether the request is pending, accepted, or rejected
CREATE TABLE QuoteRequest
(
    RequestID   INT AUTO_INCREMENT PRIMARY KEY,
    ClientID   INT,   
    RequestDate DATE,
    Status      VARCHAR(100),
    Note        VARCHAR(200),
    Size        VARCHAR(10), 
    Height      DECIMAL(3, 1), 
    Location    VARCHAR(100),
    ProximityToHouse FLOAT (20),
    UNIQUE (RequestID),
    FOREIGN KEY (ClientID) REFERENCES Client (ClientID)    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


 insert into QuoteRequest ( ClientID, RequestDate, Status, Note, Size, Height, Location, ProximityToHouse)
 values( 1,'2022-11-10', 'Ok condition', 'Need somefjj', 'Large', 12.5, 'backyard', 5.3),
( 2,'2022-11-12', 'Good condition', 'tata', 'Medium', 9.2, 'frontyard', 2.7),
( 3, '2022-12-10', 'Ok condition', 'brat', 'Small', 7.8, 'frontyard', 1.5),
( 4,'2022-12-01', 'Great condition', 'sestra', 'Medium', 8.6, 'backyard', 3.2),
( 5, '2023-08-12', 'Better condition', 'baka', 'Large', 11.4, 'backyard', 4.7),
( 6, '2023-06-02', 'Good condition', 'ides', 'Small', 6.9, 'frontyard', 1.8),
( 7,'2023-06-04', 'Better condition', 'lolas', 'Large', 10.1, 'frontyard', 6.5),
( 8,'2023-03-12', 'Great condition', 'samo ne tu', 'Medium', 9.8, 'frontyard', 2.1),           
( 9,'2023-03-20', 'Excellent condition', 'Lily Road', 'Large', 12.0, 'backyard', 5.7);
            
 select * from QuoteRequest;           



CREATE TABLE QuoteResponse
(
    ResponseID INT AUTO_INCREMENT PRIMARY KEY,
    RequestID  INT,
    ClientID INT,
    ResponseDate DATE,
    Price      DOUBLE,
    WorkPeriodFrom DATE,
    WorkPeriodTo DATE,     
    Note       VARCHAR(200),
     UNIQUE (ResponseID),
    FOREIGN KEY (RequestID) REFERENCES QuoteRequest (RequestID),
	FOREIGN KEY (ClientID) REFERENCES QuoteRequest (ClientID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into QuoteResponse (RequestID, ClientID, ResponseDate, Price, WorkPeriodFrom, WorkPeriodTo, Note) 
values(1, 1, '2022-05-12', 1500.0, '2022-05-12', '2023-09-14', 'Note1'),
	(2, 2, '2023-09-14', 1501.0, '2023-09-14', '2023-07-18', 'Note2'),
    (3, 3, '2023-07-18', 1502.0, '2023-07-18', '2023-01-31', 'Note3'),
	(4, 4, '2023-01-31', 1503.0, '2023-01-31', '2023-08-05', 'Note4'),
	(5, 5, '2023-08-05', 1504.0, '2023-08-05', '2022-12-25', 'Note5'),
	(6, 6, '2022-12-25', 1505.0, '2022-12-25', '2022-11-20', 'Note6'),
	(7, 7, '2022-11-20', 1506.0, '2022-11-20', '2023-06-12', 'Note7'),
	(8, 8, '2023-06-12', 1507.0, '2023-06-12', '2023-03-20', 'Note8'),
	(9, 9, '2023-03-20', 1508.0, '2023-03-20', '2023-04-12', 'Note9');
    
select * from QuoteResponse;


CREATE TABLE ClientRespondToQuoteResponse (
    ClientResponseID INT AUTO_INCREMENT PRIMARY KEY,
    ContractorID INT,
    ResponseID INT,
    ResponseDate DATE,
    Status ENUM('Accepted', 'Rejected', 'Pending', 'RequestRevision'),
    Note VARCHAR(255),
     UNIQUE (ClientResponseID),
    -- Other response-related fields
    FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID),
    FOREIGN KEY (ResponseID) REFERENCES QuoteResponse(ResponseID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO ClientRespondToQuoteResponse (ContractorID, ResponseID, ResponseDate, Status, Note)
VALUES (1, 1, '2023-09-09', 'Accepted', 'sg'), 
(1, 2, '2023-01-01', 'Rejected', 'dfxh');

select * from ClientRespondToQuoteResponse;



CREATE TABLE ContractorRespondToClientResponse (
    ContractorResponseID INT AUTO_INCREMENT PRIMARY KEY,
    ContractorID INT,
    ClientResponseID INT,
    ResponseDate DATE,
    Status ENUM('Accepted', 'Rejected', 'Pending', 'RequestRevision'),
    Note VARCHAR(255),
    ModifiedPrice DOUBLE,
    ModifiedWorkPeriodFrom DATE,
    ModifiedWorkPeriodTo DATE,
    -- Other response-related fields
    FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID),
    FOREIGN KEY (ClientResponseID) REFERENCES ClientRespondToQuoteResponse(ClientResponseID)
);

INSERT INTO ContractorRespondToClientResponse ( ContractorID, ClientResponseID, Status, ResponseDate, Note,ModifiedPrice,ModifiedWorkPeriodFrom,ModifiedWorkPeriodTo) 
VALUES (1, 10, 'Accepted', '2022-12-29','need some extra time',42.2,'2023-01-01', '2023-01-02'),
(1, 12, 'Accepted', '2022-12-29', 'available to work',42.2,'2023-01-01', '2023-01-02');

select * from ContractorRespondToClientResponse;


CREATE TABLE OrderOfWork
(
    OrderID   INT AUTO_INCREMENT PRIMARY KEY,
    RequestID INT,   
    StartDate DATE,
    EndDate   DATE,
    Status    VARCHAR(50),
    FOREIGN KEY (RequestID) REFERENCES QuoteRequest (RequestID)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



# Stores bills generated based on completed work. It links to the order of work and indicates the status of the bill (e.g., pending, in dispute)
CREATE TABLE Bill
(
    BillID  INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT REFERENCES OrderOfWork (OrderID),
    Amount  FLOAT,
    DueDate DATE,
    Status  VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



# Stores responses from clients to bills. It is linked to the Bill table and the Client table
CREATE TABLE ResponseToBill
(
    ResponseToBillID INT AUTO_INCREMENT PRIMARY KEY,
    BillID           INT REFERENCES Bill (BillID),
    Client_ID        INT REFERENCES Client (Client_ID),
    ResponseDate     DATE,
    Note             VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# Stores login credentials for administrators with access to the system
CREATE TABLE Administrator
(
	
    AdminID  INT AUTO_INCREMENT PRIMARY KEY,
    Email  VARCHAR(50) ,
    Username VARCHAR(50),
    Password VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
insert into Administrator(Username, Password, Email)
                values ( 'Cummis', 'DC1000', 'Cu@gmail.com'),
                ( 'Laws', 'ML1234', 'La@gmail.com');              

select * from administrator;





# Stores records of revenue generated for specific time periods
CREATE TABLE Revenue
(
    RevenueID INT AUTO_INCREMENT PRIMARY KEY,
    Date      DATE,
    Amount    FLOAT
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ROLES - (David Smith, clients, or Admin root)

SHOW GRANTS FOR 'john'@'localhost';

-- drop database project1;

















-- drop database project1;
