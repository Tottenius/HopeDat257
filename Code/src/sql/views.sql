SELECT name, password FROM
    Users WHERE name = 'Tor';

SELECT name FROM Users WHERE Users.id = '1';

SELECT * FROM EmissionData;

SELECT fromUserID AS UserID ,toUserID AS FriendID
    FROM Friends AS f , Users AS u
    WHERE u.id = '1';

CREATE VIEW FriendsView AS
    SELECT name, toUserID FROM Users
         JOIN Friends ON(Users.id = Friends.fromUserID);

SELECT * FROM FriendsView;