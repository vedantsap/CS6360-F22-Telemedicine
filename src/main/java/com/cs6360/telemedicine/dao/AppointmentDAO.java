package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.AppointmentInformation;
import com.cs6360.telemedicine.model.Doctor;
import com.cs6360.telemedicine.model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class AppointmentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean bookAppointment(AppointmentInformation appInfo, String serviceId, String doctorId) {
        String dateString = appInfo.getYear() + "-" + appInfo.getMonth() + "-" + appInfo.getDay() + " " + appInfo.getHour() + ":" + appInfo.getMinute();
        java.sql.Timestamp appointmentDate;
        try {
            appointmentDate = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString).getTime());
        } catch (ParseException e) {
            return false;
        }

        String latestAppIdQuery = "select appointment_id from appointment order by appointment_id desc";
        Integer latestAppIdNumber = Integer.valueOf(String.valueOf(jdbcTemplate.queryForList(latestAppIdQuery).get(0).get("appointment_id")).substring(1));
        String newAppId = "A" + String.format("%04d", latestAppIdNumber + 1);

        String insertQuery = "insert into appointment values (?, ?, ?, ?, ?)";
        System.out.println(newAppId + " " + serviceId + " " + appInfo.getPatientId() + " " + doctorId + " " + appointmentDate);

        try {
            jdbcTemplate.update(insertQuery, new Object[] { newAppId, appointmentDate, serviceId, appInfo.getPatientId(), doctorId });
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }
}