--superuser
;
INSERT INTO superuser VALUES ('U0001', 'P&S*HS%&1', 'Vedant', 'K', 'Sapra', 'vks220000@utdallas.edu');
INSERT INTO superuser VALUES ('U0002', 'P&S*HS%&2', 'Mrinalini', null, 'Raghavendran', 'mxr210111@utdallas.edu');
INSERT INTO superuser VALUES ('U0003', 'P&S*HS%&3', 'Akihiro', 'F', 'Yoshimoto', 'afy180000@utdallas.edu');
INSERT INTO superuser VALUES ('U0004', 'P&S*HS%&4', 'Sarah', null, 'James', 'sxj210023@utdallas.edu');
INSERT INTO superuser VALUES ('U0005', 'P&S*HS%&5', 'Kim', 'J', 'Randouf', 'kxr349921@utdallas.edu');
INSERT INTO superuser VALUES ('U0006', 'P&S*HS%&6', 'Michael', 'G', 'Scott', 'mgs210128@utdallas.edu');
INSERT INTO superuser VALUES ('U0007', 'P&S*HS%&7', 'William', null, 'Blake', 'wxb310111@utdallas.edu');
INSERT INTO superuser VALUES ('U0008', 'P&S*HS%&8', 'Henryk', null, 'Szeryng', 'hxs410311@utdallas.edu');
INSERT INTO superuser VALUES ('U0009', 'P&S*HS%&9', 'Shankar', 'M', 'Iyer', 'smi592011@utdallas.edu');
INSERT INTO superuser VALUES ('U0010', 'P&S*HS%&10', 'Ray', null, 'Chen', 'rxc345678@utdallas.edu');
INSERT INTO superuser VALUES ('U0011', 'P&S*HS%&11', 'Sherlock', 'S', 'Holmes', 'ssh221066@utdallas.edu');

<<<<<<< HEAD
--patient
;
=======
-- patient
>>>>>>> 5959eb91c4f93d3dd235c35d9a7bc67c8daad610
INSERT INTO patient VALUES ('U0001','1998-6-20 00:00:00','Company A Plan A');
INSERT INTO patient VALUES ('U0002','1999-05-19 00:00:00','Company A Plan B');
INSERT INTO patient VALUES ('U0003','2001-02-21 00:00:00','Company A Plan C');
INSERT INTO patient VALUES ('U0004','1989-12-20 00:00:00','Company B Plan B');
INSERT INTO patient VALUES ('U0005','1992-8-26 00:00:00','Company B Plan B');

<<<<<<< HEAD
--doctor
;
=======
-- doctor
>>>>>>> 5959eb91c4f93d3dd235c35d9a7bc67c8daad610
INSERT INTO doctor VALUES ('U0006', 'Physician');
INSERT INTO doctor VALUES ('U0007', 'Dentist');
INSERT INTO doctor VALUES ('U0008', 'Cardiologist');
INSERT INTO doctor VALUES ('U0011', 'Physician');

<<<<<<< HEAD
--user_admin
;
=======
-- user_admin
>>>>>>> 5959eb91c4f93d3dd235c35d9a7bc67c8daad610
INSERT INTO user_admin VALUES ('U0009');
INSERT INTO user_admin VALUES ('U0010');

<<<<<<< HEAD
--service
;
=======
-- service
>>>>>>> 5959eb91c4f93d3dd235c35d9a7bc67c8daad610
INSERT INTO service VALUES ('S0001', 'General Consultation', '50');
INSERT INTO service VALUES ('S0002', 'Blood Vitals Test', '100');
INSERT INTO service VALUES ('S0003', 'Dental Checkup', '80');
INSERT INTO service VALUES ('S0004', 'ECG', '120');

<<<<<<< HEAD
--appointment
;
=======
-- appointment
>>>>>>> 5959eb91c4f93d3dd235c35d9a7bc67c8daad610
INSERT INTO appointment VALUES ('A0001', default, 'S0001', 'U0001', 'U0006');
INSERT INTO appointment VALUES ('A0002', default, 'S0001', 'U0002', 'U0006');
INSERT INTO appointment VALUES ('A0003', default, 'S0002', 'U0003', 'U0011');
INSERT INTO appointment VALUES ('A0004', default, 'S0002', 'U0004', 'U0006');
INSERT INTO appointment VALUES ('A0005', default, 'S0003', 'U0004', 'U0007');
INSERT INTO appointment VALUES ('A0006', default, 'S0004', 'U0005', 'U0008');

<<<<<<< HEAD
--payment
;
INSERT INTO payment VALUES ('P0001', '50', default, 'A0001', 'U0009');
INSERT INTO payment VALUES ('P0002', '50', default, 'A0002', 'U0010');
INSERT INTO payment VALUES ('P0003', '100', default, 'A0003', 'U0009');
INSERT INTO payment VALUES ('P0004', '100', default, 'A0004', 'U0009');
INSERT INTO payment VALUES ('P0005', '80', default, 'A0005', 'U0010');
=======
-- payment
INSERT INTO payment VALUES ('P0001', '50', default, 'A0001', 'U0009');
>>>>>>> 5959eb91c4f93d3dd235c35d9a7bc67c8daad610
