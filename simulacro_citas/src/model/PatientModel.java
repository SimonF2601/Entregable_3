package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patient;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Patient objPatient = (Patient) obj;

        try {

            String sql = "INSERT INTO patient (names,last_names,date_born,identity_document) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPatient.getNames());
            objPrepare.setString(2, objPatient.getLastNames());
            objPrepare.setDate(3, objPatient.getDateBorn());
            objPrepare.setString(4, objPatient.getIdentityDocument());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()) {
                objPatient.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Patient inserted successfully");

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Failed to insert\n ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return objPatient;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listPatient = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM patient;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Patient objPatient = new Patient();

                objPatient.setId(objResult.getInt(1));
                objPatient.setNames(objResult.getString(2));
                objPatient.setLastNames(objResult.getString(3));
                objPatient.setDateBorn(objResult.getDate(4));
                objPatient.setIdentityDocument(objResult.getString(5));

                listPatient.add(objPatient);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return listPatient;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Patient objPatient = (Patient) obj;

        boolean isUpdate = false;

        try{
            String sql = "UPDATE patient SET names = ? , last_names = ?, date_born = ?, identity_document = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setString(1, objPatient.getNames());
            objPrepare.setString(2, objPatient.getLastNames());
            objPrepare.setDate(3, objPatient.getDateBorn());
            objPrepare.setString(4, objPatient.getIdentityDocument());
            objPrepare.setInt(5, objPatient.getId());

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

        Patient objPatient = (Patient) obj;

        try {
            String sql = "DELETE FROM patient WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPatient.getId());

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

    public Patient findByDocument (String id){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar
        Patient objPatient = null;

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM patient Where identity_document = ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setString(1,id);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objPatient = new Patient();
                objPatient.setId(objResult.getInt("id"));
                objPatient.setNames(objResult.getString("names"));
                objPatient.setLastNames(objResult.getString("last_names"));
                objPatient.setDateBorn(objResult.getDate("date_born"));
                objPatient.setIdentityDocument(objResult.getString("identity_document"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objPatient;
    }

    public Patient findById (int id){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar
        Patient objPatient = null;

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM patient Where id = ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1,id);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){//Definicion del next
                objPatient = new Patient();
                objPatient.setId(objResult.getInt("id"));
                objPatient.setNames(objResult.getString("names"));
                objPatient.setLastNames(objResult.getString("last_names"));
                objPatient.setDateBorn(objResult.getDate("date_born"));
                objPatient.setIdentityDocument(objResult.getString("identity_document"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objPatient;
    }


}
