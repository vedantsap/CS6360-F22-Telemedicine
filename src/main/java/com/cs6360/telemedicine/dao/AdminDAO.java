package com.cs6360.telemedicine.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cs6360.telemedicine.model.Appointment;
import com.cs6360.telemedicine.model.Payment;

@Component
public class AdminDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Payment> getConfirmedPayments() {
		String query = "SELECT p.payment_id, p.cost, p.payment_date, p.appointment_id, p.admin_id, s.fname \n"
				+ "FROM payment AS p \n"
				+ "LEFT OUTER JOIN superuser AS s on p.admin_id = s.user_id;";
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(query);
		List<Payment> payments = new ArrayList<Payment>();
		resultSet.forEach(r -> payments.add(
				Payment.builder()
						.paymentId(String.valueOf(r.get("payment_id")))
						.cost(Double.valueOf(String.valueOf(r.get("cost"))).intValue())
						.paymentDate(String.valueOf(r.get("payment_date")))
						.appointmentId(String.valueOf(r.get("appointment_id")))
						.adminId(String.valueOf(r.get("admin_id")))
						.adminName(String.valueOf(r.get("fname")))
						.build()));
		return payments;
	}

	public List<Appointment> getUnconfirmedAppointments() {
		String query = "SELECT appointment_id, appointment_date, service_id, patient_id, doctor_id \n"
				+ "FROM appointment \n"
				+ "WHERE appointment_id NOT IN ( \n"
				+ "	SELECT appointment_id from payment \n"
				+ ");";
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(query);
		List<Appointment> appointments = new ArrayList<Appointment>();
		resultSet.forEach(r -> appointments.add(
				Appointment.builder()
						.appointmentId(String.valueOf(r.get("appointment_id")))
						.appointmentDate(String.valueOf(r.get("appointment_date")))
						.serviceId(String.valueOf(r.get("service_id")))
						.patientId(String.valueOf(r.get("patient_id")))
						.doctorId(String.valueOf(r.get("doctor_id")))
						.build()));
		return appointments;
	}

	public void approvePayment(Payment payment) {
		String query = "INSERT INTO payment VALUES (?, ?, default, ?, ?);";
		jdbcTemplate.update(query, new Object[] {
				payment.getPaymentId(),
				payment.getCost(),
				payment.getAppointmentId(),
				payment.getAdminId()
		});
	}

}
