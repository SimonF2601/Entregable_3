package controller;

import entity.Medico;
import entity.Specialty;
import model.MedicoModel;
import model.SpecialtyModel;
import utilities.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoController {
    public static void create (){
        ////Create and use the Book model
        MedicoModel objMedicoModel = new MedicoModel();
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();

        ///Request data to fill the objMedico
        String names = JOptionPane.showInputDialog("Insert Medico names");
        String lastNames = JOptionPane.showInputDialog("Insert Medico last names");

        //Creaccion de lista para recorrer comboBox
        Object[] arrSpecialty  =  Utils.listToArray(objSpecialtyModel.findAll());

        Specialty specialty = (Specialty) JOptionPane.showInputDialog(null,
                "Insert Specialty ",
                "Specialty medico",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrSpecialty,
                arrSpecialty[0]);

        //Create an instance of Specialty and fill in the objMedico2
        Medico objMedico = new Medico(names, lastNames, specialty.getId(), specialty);
        // //We call the insertion method
        //Here we call the function made in the model to retrieve the id of the object, i.e. we take the data to fill the record, and then return certain values.
        objMedico = (Medico) objMedicoModel.insert(objMedico);

        JOptionPane.showMessageDialog(null, "Medico:\n "+objMedico.medicoInformation(),"Succesfull", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void getAllMedicos(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = "Medicos list \n";
        for(Object iterator: objMedicoModel.findAll() ){

            Medico objMedico = (Medico) iterator;
            listMedicos += objMedico.medicoInformation();
        }

        JOptionPane.showMessageDialog(null, listMedicos);

    }

    /**
     * Este metodo tiene como funcion el
     * obtener todos los registros en una cadena en forma de listado.
     * @param listMedicos es una cadena que guardara la lista de los autores
     * @return Lista con la información de los autores
     */
    public static String getAllMedicos(String listMedicos){
        MedicoModel objMedicoModel = new MedicoModel();
        listMedicos = "Medicos list \n";

        for(Object iterator: objMedicoModel.findAll() ){

            Medico objMedico = (Medico) iterator;

            listMedicos += objMedico.medicoInformation();
        }

        return listMedicos;
    }

    public static void delete(){
        MedicoModel objMedicoModel = new MedicoModel();

        String listMedicos = "";
        listMedicos = getAllMedicos(listMedicos);

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null, listMedicos + "\n Enter Medico's id to delete"));

        Medico objMedico = objMedicoModel.findById(idDelete);

        if(objMedico == null){
            JOptionPane.showMessageDialog(null,"Medico not found");
        }else{
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what to delete the Specialty?\n "+ objMedico.medicoInformation());
            if (confirm == 0) objMedicoModel.delete(objMedico);
        }
    }

    public static void update(){
        // 1. Utilizar el modelo
        MedicoModel objMedicoModel = new MedicoModel();
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();

        String listMedicos = "";
        listMedicos = getAllMedicos(listMedicos);

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listMedicos+ "\nEnter the Medico's ID to edit: "));

        // Obtenemos un Specialty por el id ingresado
        Medico   objMedico = objMedicoModel.findById(idUpdate);

        if (objMedico == null){
            JOptionPane.showMessageDialog(null, "Medico not found");
        } else {
            String names = JOptionPane.showInputDialog(null, "Enter new Names:" , objMedico.getNames());
            String lastNames = JOptionPane.showInputDialog(null, "Select new Specialty's ID" , objMedico.getLastNames());
            Object[] arrSpecialty  =  Utils.listToArray(objSpecialtyModel.findAll());

            Specialty specialty = (Specialty) JOptionPane.showInputDialog(null,
                    "Insert Specialty ",
                    "Specialty medico",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    arrSpecialty,
                    arrSpecialty[0]);

            objMedico.setNames(names);
            objMedico.setLastNames(lastNames);
            objMedico.setIdSpecialty(specialty.getId());
            objMedico.setSpecialty(specialty);
            objMedicoModel.update(objMedico);

        }



    }

    public static void searchById(){
        MedicoModel objMedicoModel = new MedicoModel();

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Search by Medico ID\n Enter Medico's ID:"));

        Medico objMedico = objMedicoModel.findById(id);

        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "Medico not found");
        } else {
            String infoMedico = "";
            infoMedico +=
                    "Medico information:\n\n" +
                            "ID: " + objMedico.getId() + "\n" +
                            "Names: " + objMedico.getNames() + "\n" +
                            "Last names: " + objMedico.getLastNames() + "\n" +
                            "Specialty: " + objMedico.getSpecialty().getName() + "\n";
            JOptionPane.showMessageDialog(null, "\n"+ infoMedico, "MedicoStore-Medico", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void getByIdSpecialty(){
        MedicoModel objMedicoModel = new MedicoModel();
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();

        Object[] arrSpecialty  =  Utils.listToArray(objSpecialtyModel.findAll());

        Specialty specialty = (Specialty) JOptionPane.showInputDialog(null,
                "Insert Specialty ",
                "Specialty medico",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrSpecialty,
                arrSpecialty[0]);

        String listMedicos = "LIST " +specialty.getName() + " Medicos \n";
        for (Medico iterator:objMedicoModel.findByIdSpecialty(specialty.getId())){
            listMedicos += iterator.medicoInformation() + "\n";
        }
        JOptionPane.showMessageDialog(null,listMedicos);
    }
}
