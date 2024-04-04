package controller;

import entity.Patient;
import entity.Specialty;
import model.PatientModel;
import model.SpecialtyModel;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

public class PatientController {
    public static void create (){
        ////Create and use the Book model
        PatientModel objPatientModel = new PatientModel();


        ///Request data to fill the objPatient
        String names = JOptionPane.showInputDialog("Insert Patient names");
        String lastNames = JOptionPane.showInputDialog("Insert Patient last names");
        String dateBornStr = JOptionPane.showInputDialog("Insert Patient date born \n Enter in this format (yyyy-mm-dd)");
        String document = JOptionPane.showInputDialog("Insert Patient identification document");

        // Parsear la fecha de nacimiento de String a Date
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBorn = null;
            try {
                dateBorn =Date.valueOf(dateBornStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error parsing the date");
                e.printStackTrace();
                return; // Salir del método si la fecha no es válida
            }

        Patient objPatient = new Patient(names, lastNames, dateBorn, document);
        // //We call the insertion method
        //Here we call the function made in the model to retrieve the id of the object, i.e. we take the data to fill the record, and then return certain values.
        objPatient = (Patient) objPatientModel.insert(objPatient);

        JOptionPane.showMessageDialog(null, "Patient:\n "+objPatient.toString(),"Succesfull", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void getAllPatients(){
        PatientModel objPatientModel = new PatientModel();
        String listPatients = "Patients list \n";
        for(Object iterator: objPatientModel.findAll() ){

            Patient objPatient = (Patient) iterator;
            listPatients += objPatient.toString();
        }

        JOptionPane.showMessageDialog(null, listPatients);

    }

    /**
     * Este metodo tiene como funcion el
     * obtener todos los registros en una cadena en forma de listado.
     * @param listPatients es una cadena que guardara la lista de los autores
     * @return Lista con la información de los autores
     */
    public static String getAllPatients(String listPatients){
        PatientModel objPatientModel = new PatientModel();
        listPatients = "Patients list \n";

        for(Object iterator: objPatientModel.findAll() ){

            Patient objPatient = (Patient) iterator;

            listPatients += objPatient.toString();
        }

        return listPatients;
    }

    public static void delete(){
        PatientModel objPatientModel = new PatientModel();

        String listPatients = "";
        listPatients = getAllPatients(listPatients);

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null, listPatients + "\n Enter Patient's id to delete"));

        Patient objPatient = objPatientModel.findById(idDelete);

        if(objPatient == null){
            JOptionPane.showMessageDialog(null,"Patient not found");
        }else{
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what to delete the Specialty?\n "+ objPatient.toString());
            if (confirm == 0) objPatientModel.delete(objPatient);
        }
    }

    public static void update(){
        // 1. Utilizar el modelo
        PatientModel objPatientModel = new PatientModel();

        String listPatients = "";
        listPatients = getAllPatients(listPatients);

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPatients + "\nEnter the Patient's ID to edit: "));

        Patient objPatient = objPatientModel.findById(idUpdate);

        if (objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            String names = JOptionPane.showInputDialog(null, "Enter new Names:", objPatient.getNames());
            String lastNames = JOptionPane.showInputDialog(null, "Enter new last names:", objPatient.getLastNames());
            String dateBornStr = JOptionPane.showInputDialog(null, "Enter new date born (yyyy-MM-dd):", new SimpleDateFormat("yyyy-MM-dd").format(objPatient.getDateBorn()));
            String document = JOptionPane.showInputDialog(null, "Enter new identification document", objPatient.getIdentityDocument());

            // Parsear la fecha de nacimiento de String a Date
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBorn = null;
            try {
                dateBorn = Date.valueOf(dateBornStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error parsing the date");
                e.printStackTrace();
                return; // Salir del método si la fecha no es válida
            }
            objPatient.setNames(names);
            objPatient.setLastNames(lastNames);
            objPatient.setDateBorn(dateBorn);
            objPatient.setIdentityDocument(document);
            objPatientModel.update(objPatient);
        }
    }

//    public static void searchByName() {
//        PatientModel objPatientModel = new PatientModel();
//
//        String name = JOptionPane.showInputDialog(null, "Enter Patient's title to search 🔎🔎");
//
//        ArrayList<Patient> PatientList = objPatientModel.findByTitle(name);
//
//        if (PatientList.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Patient not found");
//        } else {
//            String list = "Coincidence with the title \" "+ name +" \" \n";
//            for (Patient Patient : PatientList) {
//                list += Patient.PatientInformationbyTitle() + "\n";
//            }
//            JOptionPane.showMessageDialog(null, "List Patients \n"+ list);
//        }
//    }

    public static void searchByDocument(){
        PatientModel objPatientModel = new PatientModel();

        String id = JOptionPane.showInputDialog(null, "Search by Patient ID\n Enter Patient's ID:");

        Patient objPatient = objPatientModel.findByDocument(id);

        if (objPatient == null) {
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            String infoPatient = "";
            infoPatient +=
                    "Patient information:\n\n" +
                            "ID: " + objPatient.getId() + "\n" +
                            "Names: " + objPatient.getNames() + "\n" +
                            "Last names: " + objPatient.getLastNames() + "\n" +
                            "Date born: " + objPatient.getDateBorn() + "\n" +
                            "Indetity Document:" + objPatient.getIdentityDocument();
            JOptionPane.showMessageDialog(null, "\n"+ infoPatient, "PatientStore-Patient", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
