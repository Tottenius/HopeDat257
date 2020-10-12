
--Standard table for each user.
CREATE TABLE Users(
   	username VARCHAR(64) UNIQUE NOT NULL,
   	password VARCHAR(64) NOT NULL,
	PRIMARY KEY(username)
);

--Each user has emission data that stores their emission for each day.
CREATE TABLE EmissionData(
  	userID VARCHAR(64),
  	Date DATE,
    Emission VARCHAR(64),
    PRIMARY KEY( Date, userID),
    FOREIGN KEY (userID) REFERENCES Users(username)
);

--Each user can have multiple friends.
CREATE TABLE Friends(
    fromUserID VARCHAR(64) REFERENCES Users,
    toUserID VARCHAR(64) REFERENCES Users,
    PRIMARY KEY (fromUserId , toUserID)
);