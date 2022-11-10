---Finding which patients have yet to pay for an appointment---
select fname, lname, email
from SUPERUSER
where user_id not in (
	select patient_id
	from APPOINTMENT
	where appointment_id in (
		(select appointment_id from payment)
		minus
		(select appointment_id from appointment)
	)
);


---Finding the most commonly requested service---
select service_id, count(*)
from (SERVICE natural join APPOINTMENT)
group by service_id
having count(*) >= all (
	select count(*)
	from APPOINTMENT
	group by service_id
);


---Listing doctors in descending order of the number of appointments they've serviced---
select fname, minit, lname, count(*)
from (SUPERUSER join APPOINTMENT on user_id = doctor_id)
group by doctor_id
order by count(*) desc;


