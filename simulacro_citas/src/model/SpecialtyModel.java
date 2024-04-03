package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;

import javax.swing.*;
import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Specialty objSpecialty = (Specialty) obj;

        try {

            String sql = "INSERT INTO specialty (name,description) VALUES (?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objSpecialty.getName());
            objPrepare.setString(2, objSpecialty.getDescription());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while   (objResult.next()) {
                objSpecialty.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Specialty inserted successfully");

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Failed to insert\n ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return objSpecialty;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listSpecialty = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM specialty;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Specialty objSpecialty = new Specialty();

                objSpecialty.setId(objResult.getInt(1));
                objSpecialty.setName(objResult.getString(2));
                objSpecialty.setDescription(objResult.getString(3));

                listSpecialty.add(objSpecialty);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ConfigDB.closeConnection();
        return listSpecialty;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Specialty objSpecialty = (Specialty) obj;

        boolean isUpdate = false;

        try{
            String sql = "UPDATE specialty SET name = ? , description = ? WHERE id = ?';";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setString(1, objSpecialty.getName());
            objPrepare.setString(2, objSpecialty.getDescription());
            objPrepare.setInt(3, objSpecialty.getId());

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

        Specialty objSpecialty = (Specialty) obj;

        try {
            String sql = "DELETE FROM specialty WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objSpecialty.getId());

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

    public Specialty findById (int id){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar
        Specialty objSpecialty = null;

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM specialty Where id = ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1,id);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){//Definicion del next
                objSpecialty = new Specialty();
                objSpecialty.setId(objResult.getInt("id"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objSpecialty;
    }

    public ArrayList<Specialty> findByName (String name){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar
        Specialty objSpecialty = null;
        ArrayList<Specialty>  specialtyList = new ArrayList<>();

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM specialty WHERE name like ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setString(1,"%"+name);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();


            while(objResult.next()){//Definicion del next

                objSpecialty = new Specialty();
                objSpecialty.setId(objResult.getInt("id"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));
                specialtyList.add(objSpecialty);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return specialtyList;
    }
}
