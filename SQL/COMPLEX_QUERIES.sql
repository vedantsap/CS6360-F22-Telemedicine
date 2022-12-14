-- Finding which patients have yet to pay for an appointment
select fname, lname, email
from SUPERUSER
where user_id in (
	select patient_id
	from APPOINTMENT
	where appointment_id in (
		select a.appointment_id
		from (appointment as a left join payment as p on a.appointment_id = p.appointment_id)
		where payment_id is NULL
	)
);


-- List the doctors available for each service
select service_name, fname, lname, doctor.specialty, email
from (superuser join doctor on user_id = doctor_id), (service natural join doctor_services)
where
	doctor.specialty = doctor_services.specialty;
	

-- Listing doctors in descending order of the number of appointments they've serviced
select fname, minit, lname, count(*)
from (SUPERUSER join APPOINTMENT on user_id = doctor_id)
group by doctor_id
order by count(*) desc;


-- Finding the most commonly requested service
select service_id, service_name, count(*)
from (SERVICE natural join APPOINTMENT)
group by service_id
having count(*) >= all (
	select count(*)
	from APPOINTMENT
	group by service_id
);