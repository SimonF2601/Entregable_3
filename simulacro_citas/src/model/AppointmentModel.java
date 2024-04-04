package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;
import entity.Medico;
import entity.Patient;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Appointment objAppointment = (Appointment) obj;

        try {

            String sql = "INSERT INTO appointment (id_patient,id_medico,date_appointment,time_appointment,reason) VALUES (?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objAppointment.getPatient().getId());
            objPrepare.setInt(2, objAppointment.getMedico().getId());
            objPrepare.setDate(3, objAppointment.getDate());
            objPrepare.setString(4, objAppointment.getTime());
            objPrepare.setString(5, objAppointment.getReason());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()) {
                objAppointment.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Appointment inserted successfully");

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Failed to insert\n ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return objAppointment;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listAppointment = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM  appointment INNER JOIN medico ON appointment.id_medico = medico.id INNER JOIN patient ON appointment.id_patient = patient.id ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Appointment objAppointment = new Appointment();
                objAppointment.setId(objResult.getInt("appointment.id"));
                objAppointment.setDate(objResult.getDate("appointment.date_appointment"));
                objAppointment.setTime(objResult.getString("appointment.time_appointment"));
                objAppointment.setReason(objResult.getString("appointment.reason"));

                Patient objPatient = new Patient();
                objPatient.setId(objResult.getInt("patient.id"));
                objPatient.setNames(objResult.getString("patient.names"));
                objPatient.setLastNames(objResult.getString("patient.last_names"));
                objPatient.setDateBorn(objResult.getDate("patient.date_born"));
                objPatient.setIdentityDocument(objResult.getString("patient.identity_document"));

                Medico objMedico = new Medico();
                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNames(objResult.getString("medico.names"));
                objMedico.setNames(objResult.getString("medico.last_names"));
                objMedico.setIdSpecialty(objResult.getInt("medico.id_specialty"));

                objAppointment.setPatient(objPatient);
                objAppointment.setMedico(objMedico);

                listAppointment.add(objAppointment);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return listAppointment;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Appointment objAppointment = (Appointment) obj;

        boolean isUpdate = false;

        try{
            String sql = "UPDATE appointment SET id_patient = ?,id_medico = ?,date_appointment = ?,time_appointment = ?,reason = ? WHERE appointment.id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objAppointment.getPatient().getId());
            objPrepare.setInt(2, objAppointment.getMedico().getId());
            objPrepare.setDate(3, objAppointment.getDate());
            objPrepare.setString(4, objAppointment.getTime());
            objPrepare.setString(5, objAppointment.getReason());
            objPrepare.setInt(6, objAppointment.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was succesful");
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return  isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        boolean isDeleted = false;

        Connection objConnection = ConfigDB.openConnection();

        Appointment objAppointment = (Appointment) obj;

        try {
            String sql = "DELETE FROM appointment WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAppointment.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was succesful");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Appointment findByDate (Date date){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar
        Appointment objAppointment = null;

        try{
            //3. Sentencia SQL
            String sql = "SELECT * FROM  appointment INNER JOIN medico ON appointment.id_medico = medico.id INNER JOIN patient ON appointment.id_patient = patient.id ; WHERE date = ?";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setDate(1,date);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objAppointment = new Appointment();
                objAppointment.setId(objResult.getInt("appointment.id"));
                objAppointment.setDate(objResult.getDate("appointment.date_appointment"));
                objAppointment.setTime(objResult.getString("appointment.time_appointment"));
                objAppointment.setReason(objResult.getString("appointment.reason"));

                Patient objPatient = new Patient();
                objPatient.setId(objResult.getInt("patient.id"));
                objPatient.setNames(objResult.getString("patient.names"));
                objPatient.setLastNames(objResult.getString("patient.last_names"));
                objPatient.setDateBorn(objResult.getDate("patient.date_born"));
                objPatient.setIdentityDocument(objResult.getString("patient.identity_document"));

                Medico objMedico = new Medico();
                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNames(objResult.getString("medico.names"));
                objMedico.setNames(objResult.getString("medico.last_names"));
                objMedico.setIdSpecialty(objResult.getInt("medico.id_specialty"));

                objAppointment.setPatient(objPatient);
                objAppointment.setMedico(objMedico);
            } else {
                JOptionPane.showMessageDialog(null,"Appointment not found", "Not Found", JOptionPane.QUESTION_MESSAGE);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objAppointment;
    }

    public Appointment findById (int id){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar
        Appointment objAppointment = null;

        try{
            //3. Sentencia SQL
            String sql = "SELECT * FROM  appointment INNER JOIN medico ON appointment.id_medico = medico.id INNER JOIN patient ON appointment.id_patient = patient.id ; WHERE appointment.id = ?";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1,id);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objAppointment = new Appointment();
                objAppointment.setId(objResult.getInt("appointment.id"));
                objAppointment.setDate(objResult.getDate("appointment.date_appointment"));
                objAppointment.setTime(objResult.getString("appointment.time_appointment"));
                objAppointment.setReason(objResult.getString("appointment.reason"));

                Patient objPatient = new Patient();
                objPatient.setId(objResult.getInt("patient.id"));
                objPatient.setNames(objResult.getString("patient.names"));
                objPatient.setLastNames(objResult.getString("patient.last_names"));
                objPatient.setDateBorn(objResult.getDate("patient.date_born"));
                objPatient.setIdentityDocument(objResult.getString("patient.identity_document"));

                Medico objMedico = new Medico();
                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNames(objResult.getString("medico.names"));
                objMedico.setNames(objResult.getString("medico.last_names"));
                objMedico.setIdSpecialty(objResult.getInt("medico.id_specialty"));

                objAppointment.setPatient(objPatient);
                objAppointment.setMedico(objMedico);
            } else {
                JOptionPane.showMessageDialog(null,"Appointment not found", "Not Found", JOptionPane.QUESTION_MESSAGE);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objAppointment;
    }

//    public ArrayList<Appointment> findByIdSpecialty (int idSpecialty){
//        //1. Abrimos la conexion
//        Connection objConnection = ConfigDB.openConnection();
//
//        //2. Crear el Book que vamos a retornar
//        Appointment objAppointment = null;
//        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
//        ArrayList<Appointment>  AppointmentList = new ArrayList<>();
//
//        try{
//            //3. Sentencia SQL
//            String sql ="SELECT * FROM  appointment INNER JOIN specialty ON appointment.id_specialty = specialty.id WHERE specialty.id = ?; ";
//
//            //4. Preparar el statement
//            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
//
//            //5. Darle valor al parametro del Query (?)
//            objPrepare.setInt(1, idSpecialty);
//
//            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
//            ResultSet objResult = objPrepare.executeQuery();
//
//
//            while(objResult.next()){//Definicion del next
//
//                objAppointment = new Appointment();
//                objAppointment.setId(objResult.getInt("Appointment.id"));
//                objAppointment.setNames(objResult.getString("Appointment.names"));
//                objAppointment.setLastNames(objResult.getString("Appointment.last_names"));
//                objAppointment.setIdSpecialty(objResult.getInt("Appointment.id_specialty"));
//
//                //Llenar el objeto Author para ingresar al objeto del libro
//                Specialty objSpecialty = new Specialty();
//                objSpecialty.setId(objAppointment.getIdSpecialty());
//                objSpecialty.setName(objResult.getString("specialty.name"));
//                objSpecialty.setDescription(objResult.getString("specialty.description "));
//                objAppointment.setSpecialty(objSpecialty);
//
//                AppointmentList.add(objAppointment);
//            }
//
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//
//        // 7. Cerrar  connection
//        ConfigDB.closeConnection();
//        return AppointmentList;
//    }

}
