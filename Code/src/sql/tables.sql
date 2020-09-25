
--Standard table for each user.
CREATE TABLE Users(
	id INT,
   	name VARCHAR(64) UNIQUE NOT NULL,
   	password VARCHAR(64) NOT NULL,
	PRIMARY KEY(id)
);

--Each user has emission data that stores their emission for each day.
CREATE TABLE EmissionData(
  	userID INTS,
  	Date DATE,
    Emission VARCHAR(64),
    PRIMARY KEY( Date, userID),
    FOREIGN KEY (userID) REFERENCES Users(id)
);

--Each user can have multiple friends.
CREATE TABLE Friends(
    fromUserID INT REFERENCES Users,
    toUserID INT REFERENCES Users,
    friendshipStatus BOOLEAN,
    PRIMARY KEY (fromUserId , toUserID )
);


