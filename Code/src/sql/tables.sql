--Standard table for each user.
CREATE TABLE Users(
   	username VARCHAR(64) UNIQUE NOT NULL,
   	password VARCHAR(64) NOT NULL,
	PRIMARY KEY(username)
);

--Each user has emission data that stores their emission for each day.
CREATE TABLE EmissionData(
  	username VARCHAR(64),
  	date DATE,
  	food VARCHAR(64),
    emission INT,
    PRIMARY KEY(username, date, food),
    FOREIGN KEY (username) REFERENCES Users(username)
);

--Each user can have multiple friends.
CREATE TABLE Friends(
    fromUser VARCHAR(64) REFERENCES Users,
    toUser VARCHAR(64) REFERENCES Users,
    PRIMARY KEY (fromUser , toUser)
);