INSERT INTO Users VALUES ('Tor', 'Password');
INSERT INTO Users VALUES ('Anton', 'Password2');
INSERT INTO Users VALUES ('Emil', 'Password3');
INSERT INTO Users VALUES ('Jonathan', 'Password4');
INSERT INTO Users VALUES ('Bilbo Baggins', 'Password4');

INSERT INTO EmissionData VALUES ('Tor', '2020-09-24', 'pork', 78, 1);
INSERT INTO EmissionData VALUES ('Tor', '2020-09-25', 'beef', 58, 1);
INSERT INTO EmissionData VALUES ('Tor', '2020-09-26', 'chicken', 23, 1);
INSERT INTO EmissionData VALUES ('Tor', '2020-09-27', 'lamb', 35, 1);

INSERT INTO EmissionData VALUES ('Anton', '2020-09-24', 'cod', 32, 1);
INSERT INTO EmissionData VALUES ('Anton', '2020-09-25', 'salmon', 78, 1);
INSERT INTO EmissionData VALUES ('Anton', '2020-09-26', 'eggs', 12, 1);
INSERT INTO EmissionData VALUES ('Anton', '2020-09-27', 'milk', 67, 1);

INSERT INTO Friends VALUES ( 'Tor' , 'Anton');
INSERT INTO Friends VALUES ( 'Anton' , 'Tor');
INSERT INTO Friends VALUES ( 'Tor' , 'Emil');