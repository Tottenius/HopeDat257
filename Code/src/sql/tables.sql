CREATE TABLE Users(
	id VARCHAR(64),
   	name VARCHAR(64) UNIQUE NOT NULL,
   	password VARCHAR(64) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE EmissionData(
  	userID VARCHAR(64),
  	Date DATE,
    Emission VARCHAR(64),
    PRIMARY KEY( Date, userID),
    FOREIGN KEY (userID) REFERENCES Users(id)
);

CREATE TABLE Friends(
    fromUserID VARCHAR(64) REFERENCES Users,
    toUserID VARCHAR(64) REFERENCES Users,
    friendshipStatus BOOLEAN,
    PRIMARY KEY (fromUserId , toUserID )
);
