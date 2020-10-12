INSERT INTO Users VALUES ('Tor', 'Password');
INSERT INTO Users VALUES ('Anton', 'Password2');
INSERT INTO Users VALUES ('Emil', 'Password3');
INSERT INTO Users VALUES ('Jonathan', 'Password4');
INSERT INTO Users VALUES ('Bilbo Baggins', 'Password4');

INSERT INTO EmissionData VALUES ('Tor', '2020-09-24', 78);
INSERT INTO EmissionData VALUES ('Tor', '2020-09-25', 58);
INSERT INTO EmissionData VALUES ('Tor', '2020-09-26', 23);
INSERT INTO EmissionData VALUES ('Tor', '2020-09-27', 35);

INSERT INTO EmissionData VALUES ('Anton', '2020-09-24', 32);
INSERT INTO EmissionData VALUES ('Anton', '2020-09-25', 78);
INSERT INTO EmissionData VALUES ('Anton', '2020-09-26', 12);
INSERT INTO EmissionData VALUES ('Anton', '2020-09-27', 67);

INSERT INTO Friends VALUES ( 'Tor' , 'Anton');
INSERT INTO Friends VALUES ( 'Anton' , 'Tor');
INSERT INTO Friends VALUES ( 'Tor' , 'Emil');