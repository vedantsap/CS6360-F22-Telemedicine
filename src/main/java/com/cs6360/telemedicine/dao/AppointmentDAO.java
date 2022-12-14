package com.cs6360.telemedicine.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cs6360.telemedicine.model.AppointmentInformation;

@Component
public class AppointmentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean bookAppointment(AppointmentInformation appInfo, String serviceId, String doctorId) {
        String dateString = appInfo.getTimestamp().substring(0, appInfo.getTimestamp().indexOf('T'))
                + " "
                + appInfo.getTimestamp().substring(appInfo.getTimestamp().indexOf('T') + 1,
                        appInfo.getTimestamp().indexOf('.'));
        // Timestamp appointmentDate;
        // try {
        // appointmentDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd
        // HH:mm").parse(dateString).getTime());
        // } catch (ParseException e) {
        // return false;
        // }

        String latestAppIdQuery = "select appointment_id from appointment order by appointment_id desc";
        Integer latestAppIdNumber = Integer.valueOf(
                String.valueOf(jdbcTemplate.queryForList(latestAppIdQuery).get(0).get("appointment_id")).substring(1));
        String newAppId = "A" + String.format("%04d", latestAppIdNumber + 1);

        String insertQuery = "insert into appointment values (?, ?, ?, ?, ?)";
        System.out.println(
                newAppId + " " + serviceId + " " + appInfo.getPatientId() + " " + doctorId + " " + dateString);

        try {
            jdbcTemplate.update(insertQuery,
                    new Object[] { newAppId, dateString, serviceId, appInfo.getPatientId(), doctorId });
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    public List<AppointmentInformation> getAllPatientAppointments(String patientId) {
        String query = "select appointment_id, appointment_date, service_name, u.fname, u.lname, specialty, u.email from appointment a, service s, doctor d, patient p, superuser u "
                +
                "where p.patient_id = ? and u.user_id = d.doctor_id and a.service_id = s.service_id and p.patient_id = a.patient_id and d.doctor_id = a.doctor_id";
        List<Map<String, Object>> rs = jdbcTemplate.queryForList(query, new Object[] { patientId });
        List<AppointmentInformation> patientAppointments = new ArrayList<>();
        rs.forEach(r -> {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(((Timestamp) r.get("appointment_date")));
            patientAppointments.add(
                    AppointmentInformation.builder()
                            .appointmentId(String.valueOf(r.get("appointment_id")))
                            .hour(calendar.get(Calendar.HOUR_OF_DAY))
                            .minute(calendar.get(Calendar.MINUTE))
                            .year(calendar.get(Calendar.YEAR))
                            .month(calendar.get(Calendar.MONTH))
                            .day(calendar.get(Calendar.DAY_OF_MONTH))
                            .doctorFname(String.valueOf(r.get("fname")))
                            .doctorLname(String.valueOf(r.get("lname")))
                            .serviceName(String.valueOf(r.get("service_name")))
                            .build());
        });

        return patientAppointments;
    }

    public List<AppointmentInformation> getFuturePatientAppointments(String patientId) {
        List<AppointmentInformation> appointments = getAllPatientAppointments(patientId);
        Calendar now = Calendar.getInstance();
        Calendar.Builder appDate = new Calendar.Builder();
        Iterator<AppointmentInformation> it = appointments.iterator();
        while (it.hasNext()) {
            AppointmentInformation appInfo = it.next();
            if (now.after(
                    appDate
                            .setDate(appInfo.getYear(), appInfo.getMonth(), appInfo.getDay())
                            .setTimeOfDay(appInfo.getHour(), appInfo.getMinute(), 0)
                            .build())) {
                it.remove();
            }
        }
        return appointments;
    }

    public Boolean cancelAppointment(String appointmentId) {
        String query = "delete from appointment where appointment_id = ?";
        try {
            jdbcTemplate.update(query, new Object[] { appointmentId });
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    public List<AppointmentInformation> getAllPastAppointments(String doctorId) {
        String query = "SELECT u.fname, u.lname, s.service_name, a.appointment_id, a.appointment_date "
                + "FROM superuser AS u, appointment AS a, service AS s "
                + "WHERE u.user_id = a.patient_id "
                + "AND a.service_id = s.service_id "
                + "AND doctor_id = ? "
                + "HAVING DATE(a.appointment_date) <= DATE(NOW()) "
                + "ORDER BY a.appointment_date DESC;";
        List<Map<String, Object>> rs = jdbcTemplate.queryForList(query, new Object[] { doctorId });
        List<AppointmentInformation> doctorAppointments = new ArrayList<>();
        rs.forEach(r -> {
            doctorAppointments.add(
                    AppointmentInformation.builder()
                            .appointmentId(String.valueOf(r.get("appointment_id")))
                            .patientFname(String.valueOf(r.get("fname")))
                            .patientLname(String.valueOf(r.get("lname")))
                            .serviceName(String.valueOf(r.get("service_name")))
                            .timestamp(String.valueOf(r.get("appointment_date")))
                            .build());
        });

        return doctorAppointments;
    }

    public List<AppointmentInformation> getAllUpcomingAppointments(String doctorId) {
        String query = "SELECT u.fname, u.lname, s.service_name, a.appointment_id, a.appointment_date "
                + "FROM superuser AS u, appointment AS a, service AS s "
                + "WHERE u.user_id = a.patient_id "
                + "AND a.service_id = s.service_id "
                + "AND doctor_id = ? "
                + "HAVING DATE(a.appointment_date) > DATE(NOW()) "
                + "ORDER BY a.appointment_date DESC;";
        List<Map<String, Object>> rs = jdbcTemplate.queryForList(query, new Object[] { doctorId });
        List<AppointmentInformation> doctorAppointments = new ArrayList<>();
        rs.forEach(r -> {
            doctorAppointments.add(
                    AppointmentInformation.builder()
                            .appointmentId(String.valueOf(r.get("appointment_id")))
                            .patientFname(String.valueOf(r.get("fname")))
                            .patientLname(String.valueOf(r.get("lname")))
                            .serviceName(String.valueOf(r.get("service_name")))
                            .timestamp(String.valueOf(r.get("appointment_date")))
                            .build());
        });

        return doctorAppointments;
    }
}