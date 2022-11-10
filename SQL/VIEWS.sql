---Patient information---
create view PATIENT_INFO(fname, minit, lname, birthday, insurance, email)
as
	select fname, minit, lname, birthday, insurance, email
	from SUPERUSER, PATIENT
	where user_id = patient_id;

---Doctor information---
create view DOCTOR_INFO(fname, minit, lname, specialty, email)
as
	select fname, minit, lname, specialty, email
	from SUPERUSER, DOCTOR
	where user_id = doctor_id;

---Admin information---
create view ADMIN_INFO(fname, minit, lname, email)
as
	select fname, minit, lname, email
	from SUPERUSER, USER_ADMIN
	where user_id = admin_id;

---Appointment Information---
create view APPOINTMENT_INFO(appointment_date, patient_fname, patient_lname, doctor_fname, doctor_lname, service_name)
as
	select appointment_date, patient.fname, patient.lname, doctor.fname, doctor.lname, service_name
	from APPOINTMENT, SUPERUSER as patient, SUPERUSER as doctor
	where patient_id = patient.user_id and doctor_id = doctor.user_id;
