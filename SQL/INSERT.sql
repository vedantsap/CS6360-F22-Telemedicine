--superuser
INSERT INTO superuser VALUES ('U0001', 'P&S*HS%&1', 'Vedant', 'K', 'Sapra', 'vks220000@utdallas.edu');
INSERT INTO superuser VALUES ('U0002', 'P&S*HS%&2', 'Mrinalini', null, 'Raghavendran', 'mxr210111@utdallas.edu');
INSERT INTO superuser VALUES ('U0006', 'P&S*HS%&6', 'Michael', 'G', 'Scott', 'mgs210128@utdallas.edu');
INSERT INTO superuser VALUES ('U0009', 'P&S*HS%&9', 'Shankar', 'M', 'Iyer', 'smi592011@utdallas.edu');

	

--patient
INSERT INTO patient VALUES ('U0001','1998-6-20 00:00:00','Company A Plan A');

--doctor
INSERT INTO doctor VALUES ('U0006', 'Physician');

--user_admin
INSERT INTO user_admin VALUES ('U0009');

--service
INSERT INTO service VALUES ('S0001', 'General Consultation', '50');

--appointment
INSERT INTO appointment VALUES ('A0001', default, 'S0001', 'U0001', 'U0006');

--payment
INSERT INTO payment VALUES ('P0001', '50', default, 'A0001', 'U0009');