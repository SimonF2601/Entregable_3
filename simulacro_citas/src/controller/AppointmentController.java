package controller;

import entity.Appointment;
import entity.Medico;
import entity.Patient;
import entity.Specialty;
import model.*;
import model.AppointmentModel;
import utilities.Utils;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class AppointmentController {
    public static void create (){
        ////Create and use the Book model
        AppointmentModel objAppointmentModel = new AppointmentModel();
        PatientModel objPatientModel = new PatientModel();
        MedicoModel objMedicoModel = new MedicoModel();

        Object[] arrPatient  =  Utils.listToArray(objPatientModel.findAll());
        Object[] arrMedico  =  Utils.listToArray(objMedicoModel.findAll());

        ///Request data to fill the objAppointment
        Patient patient = (Patient) JOptionPane.showInputDialog(null,
                "Select Patient ",
                "Riwi Appointment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrPatient,
                arrPatient[0]);

        Medico medico = (Medico) JOptionPane.showInputDialog(null,
                "Select Medico ",
                "Riwi Appointment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrMedico,
                arrMedico[0]);

        String strDate = JOptionPane.showInputDialog("Insert Appointment date: ");
        String time = JOptionPane.showInputDialog("Insert Appointment time");
        String reason = JOptionPane.showInputDialog("Insert Appointment reason");

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date =Date.valueOf(strDate);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error parsing the date");
            e.printStackTrace();
            return; // Salir del método si la fecha no es válida
        }


        //Create an instance of Specialty and fill in the objAppointment2
        Appointment objAppointment = new Appointment(patient, medico, date, time, reason);
        // //We call the insertion method
        //Here we call the function made in the model to retrieve the id of the object, i.e. we take the data to fill the record, and then return certain values.
        objAppointment = (Appointment) objAppointmentModel.insert(objAppointment);

        JOptionPane.showMessageDialog(null, "New Appointment:\n "+objAppointment.AppointmentInformation(),"Succesfull", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void getAllAppointments(){
        AppointmentModel objAppointmentModel = new AppointmentModel();
        String listAppointments = "Appointments list \n";
        for(Object iterator: objAppointmentModel.findAll() ){

            Appointment objAppointment = (Appointment) iterator;
            listAppointments += objAppointment.AppointmentInformation();
        }

        JOptionPane.showMessageDialog(null, listAppointments);

    }

    /**
     * Este metodo tiene como funcion el
     * obtener todos los registros en una cadena en forma de listado.
     * @param listAppointments es una cadena que guardara la lista de los autores
     * @return Lista con la información de los autores
     */
    public static String getAllAppointments(String listAppointments){
        AppointmentModel objAppointmentModel = new AppointmentModel();
        listAppointments = "Appointments list \n";

        for(Object iterator: objAppointmentModel.findAll() ){

            Appointment objAppointment = (Appointment) iterator;

            listAppointments += objAppointment.AppointmentInformation();
        }

        return listAppointments;
    }

    public static void delete(){
        AppointmentModel objAppointmentModel = new AppointmentModel();

        String listAppointments = "";
        listAppointments = getAllAppointments(listAppointments);

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null, listAppointments + "\n Enter Appointment's id to delete"));

        Appointment objAppointment = objAppointmentModel.findById(idDelete);

        if(objAppointment == null){
            JOptionPane.showMessageDialog(null,"Appointment not found");
        }else{
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what to delete the Specialty?\n "+ objAppointment.AppointmentInformation());
            if (confirm == 0) objAppointmentModel.delete(objAppointment);
        }
    }

    public static void update(){
        // 1. Utilizar el modelo
        AppointmentModel objAppointmentModel = new AppointmentModel();

        String listAppointments = "";
        listAppointments = getAllAppointments(listAppointments);

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAppointments+ "\nEnter the Appointment's ID to edit: "));

        // Obtenemos un Specialty por el id ingresado
        Appointment   objAppointment = objAppointmentModel.findById(idUpdate);

        if (objAppointment == null){
            JOptionPane.showMessageDialog(null, "Appointment not found");
        } else {
            String dateStr = JOptionPane.showInputDialog(null, "Enter new appointment date  (yyyy-MM-dd):", new SimpleDateFormat("yyyy-MM-dd").format(objAppointment.getDate()));

            // Parsear la fecha de nacimiento de String a Date
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = Date.valueOf(dateStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error parsing the date");
                e.printStackTrace();
            }
            objAppointment.setDate(date);
            objAppointmentModel.update(objAppointment);
        }
    }

//    public static void searchByName() {
//        AppointmentModel objAppointmentModel = new AppointmentModel();
//
//        String name = JOptionPane.showInputDialog(null, "Enter Appointment's title to search 🔎🔎");
//
//        ArrayList<Appointment> AppointmentList = objAppointmentModel.findByTitle(name);
//
//        if (AppointmentList.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Appointment not found");
//        } else {
//            String list = "Coincidence with the title \" "+ name +" \" \n";
//            for (Appointment Appointment : AppointmentList) {
//                list += Appointment.AppointmentInformationbyTitle() + "\n";
//            }
//            JOptionPane.showMessageDialog(null, "List Appointments \n"+ list);
//        }
//    }

    public static void searchByDate(){
        AppointmentModel objAppointmentModel = new AppointmentModel();

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Search by Appointment ID\n Enter Appointment's ID:"));

        Appointment objAppointment = objAppointmentModel.findById(id);

        if (objAppointment == null) {
            JOptionPane.showMessageDialog(null, "Appointment not found");
        } else {
            JOptionPane.showMessageDialog(null, "\n"+ objAppointment.AppointmentInformation(), "AppointmentStore-Appointment", JOptionPane.INFORMATION_MESSAGE);
        }
    }

//    public static void getByIdSpecialty(){
//        AppointmentModel objAppointmentModel = new AppointmentModel();
//        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
//
//        List<Object> list = objSpecialtyModel.findAll();
//        Specialty[] arrSpecialty = new  Specialty[list.size()];
//
//        int index = 0;
//        for (Object ite : list){
//            Specialty objSpecialty = (Specialty) ite;
//            arrSpecialty[index] = objSpecialty;
//            index++;
//        }
//
//        Specialty idSearch = (Specialty) JOptionPane.showInputDialog(null,
//                "Insert Specialtys ID to search",
//                "",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                arrSpecialty,
//                arrSpecialty[0].getName());
//
//        String listAppointments = "LIST " +idSearch.getName() + " AppointmentS \n";
//        for (Appointment iterator:objAppointmentModel.findByIdSpecialty(idSearch.getId())){
//            listAppointments += iterator.AppointmentInformation() + "\n";
//        }
//        JOptionPane.showMessageDialog(null,listAppointments);
//    }
}
