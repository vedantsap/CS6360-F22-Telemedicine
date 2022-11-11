create database telemedicine_data;
use telemedicine_data;
create table superuser(
    user_id int not null primary key,
    user_password varchar(30) not null,
    fname varchar(30) not null,
    minit char(1),
    lname varchar(30) not null,
    email varchar(30) not null
);
create table patient(
    patient_id int not null primary key,
    birthday datetime default current_timestamp,
    insurance varchar(30),
    foreign key(patient_id) references superuser(user_id)
);
create table doctor(
    doctor_id int not null primary key,
    speciality varchar(30),
    user_id int not null,
    foreign key(doctor_id) references superuser(user_id)
);
create table user_admin(
    admin_id int not null primary key,
    user_id int not null,
    foreign key(admin_id) references superuser(user_id)
);
create table service_type_price(
    service_type varchar(30) not null primary key,
    price decimal(5, 2) not null
);
create table service(
    service_id int not null primary key,
    service_name varchar(30) not null,
    service_type varchar(30),
    foreign key(service_type) references service_type_price(service_type)
);
create table appointment(
    appointment_id int not null primary key,
    appointment_date datetime default current_timestamp,
    service_id int not null,
    patient_id int not null,
    doctor_id int not null,
    foreign key(service_id) references service(service_id),
    foreign key(patient_id) references patient(patient_id),
    foreign key(doctor_id) references doctor(doctor_id)
);
create table payment(
    payment_id int not null primary key,
    cost decimal(5, 2) not null,
    payment_date datetime default current_timestamp,
    appointment_id int not null,
    admin_id int not null,
    foreign key(appointment_id) references appointment(appointment_id),
    foreign key(admin_id) references user_admin(admin_id)
);


create table doctor_services(
	service_id int not null,
    specialty varchar(30),
    primary key (service_id, specialty),
    foreign key(service_id) references service(service_id)
);