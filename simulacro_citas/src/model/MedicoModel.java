package model;

import controller.SpecialtyController;
import database.CRUD;
import database.ConfigDB;
import entity.Medico;
import entity.Specialty;

import javax.swing.*;
import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = (Medico) obj;

        try {

            String sql = "INSERT INTO medico (names,last_names,id_specialty) VALUES (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objMedico.getNames());
            objPrepare.setString(2, objMedico.getLastNames());
            objPrepare.setInt(3, objMedico.getSpecialty().getId());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()) {
                objMedico.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Medico inserted successfully");

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Failed to insert\n ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listMedico = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM medico INNER JOIN specialty on medico.id_specialty = specialty.id;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Medico objMedico = new Medico();
                Specialty objSpecialty = new Specialty();

                objSpecialty.setId(objResult.getInt("specialty.id"));
                objSpecialty.setName(objResult.getString("specialty.name"));
                objSpecialty.setDescription(objResult.getString("specialty.Description"));

                objMedico.setId(objResult.getInt(1));
                objMedico.setNames(objResult.getString(2));
                objMedico.setLastNames(objResult.getString(3));
                objMedico.setIdSpecialty(objResult.getInt(4));
                objMedico.setSpecialty(objSpecialty);

                listMedico.add(objMedico);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return listMedico;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = (Medico) obj;

        boolean isUpdate = false;

        try{
            String sql = "UPDATE medico SET names = ? , last_names = ?, id_specialty = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setString(1, objMedico.getNames());
            objPrepare.setString(2, objMedico.getLastNames());
            objPrepare.setInt(3, objMedico.getIdSpecialty());
            objPrepare.setInt(4, objMedico.getId());

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

        Medico objMedico = (Medico) obj;

        try {
            String sql = "DELETE FROM medico WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objMedico.getId());

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

    public Medico findById (int id){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar
        Medico objMedico = null;

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM medico INNER JOIN  specialty ON medico.id_specialty = specialty.id Where medico.id = ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1,id);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){//Definicion del next
                objMedico = new Medico();
                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNames(objResult.getString("medico.names"));
                objMedico.setLastNames(objResult.getString("medico.last_names"));
                objMedico.setIdSpecialty(objResult.getInt("medico.id_specialty"));

                Specialty objSpecialty = new Specialty();
                objSpecialty.setId(objMedico.getIdSpecialty());
                objSpecialty.setName(objResult.getString("specialty.name"));
                objSpecialty.setDescription(objResult.getString("specialty.description"));

                objMedico.setSpecialty(objSpecialty);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objMedico;
    }

    public ArrayList<Medico> findByIdSpecialty (int idSpecialty){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el Book que vamos a retornar
        Medico objMedico = null;
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        ArrayList<Medico>  MedicoList = new ArrayList<>();

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM  medico INNER JOIN specialty ON medico.id_specialty = specialty.id WHERE specialty.id = ?; ";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1, idSpecialty);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();


            while(objResult.next()){//Definicion del next

                objMedico = new Medico();
                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNames(objResult.getString("medico.names"));
                objMedico.setLastNames(objResult.getString("medico.last_names"));
                objMedico.setIdSpecialty(objResult.getInt("medico.id_specialty"));

                //Llenar el objeto Author para ingresar al objeto del libro
                Specialty objSpecialty = new Specialty();
                objSpecialty.setId(objMedico.getIdSpecialty());
                objSpecialty.setName(objResult.getString("specialty.name"));
                objSpecialty.setDescription(objResult.getString("specialty.description"));
                objMedico.setSpecialty(objSpecialty);

                MedicoList.add(objMedico);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return MedicoList;
    }
}
