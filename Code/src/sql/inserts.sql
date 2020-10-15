INSERT INTO Users VALUES ('tor', 'Password');
INSERT INTO Users VALUES ('anton', 'Password2');
INSERT INTO Users VALUES ('emil', 'Password3');
INSERT INTO Users VALUES ('jonathan', 'Password4');
INSERT INTO Users VALUES ('bilbo', 'Password4');

INSERT INTO EmissionData VALUES ('tor', '2020-09-24', 'pork', 78, 1);
INSERT INTO EmissionData VALUES ('tor', '2020-09-25', 'beef', 58, 1);
INSERT INTO EmissionData VALUES ('tor', '2020-09-26', 'chicken', 23, 1);
INSERT INTO EmissionData VALUES ('tor', '2020-09-27', 'lamb', 35, 1);

INSERT INTO EmissionData VALUES ('anton', '2020-09-24', 'cod', 32, 1);
INSERT INTO EmissionData VALUES ('atnton', '2020-09-25', 'salmon', 78, 1);
INSERT INTO EmissionData VALUES ('anton', '2020-09-26', 'eggs', 12, 1);
INSERT INTO EmissionData VALUES ('anton', '2020-09-27', 'milk', 67, 1);

INSERT INTO Friends VALUES ( 'tor' , 'anton');
INSERT INTO Friends VALUES ( 'anton' , 'tor');
INSERT INTO Friends VALUES ( 'tor' , 'emil');