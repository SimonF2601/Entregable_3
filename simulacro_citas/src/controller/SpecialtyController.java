package controller;

import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;

public class SpecialtyController {

    public static void create(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();

        String name = JOptionPane.showInputDialog(null,"Insert name");
        String description = JOptionPane.showInputDialog(null,"Insert name");

        Specialty objSpecialty = new Specialty();

        objSpecialty.setName(name);
        objSpecialty.setDescription(description);

        objSpecialty = (Specialty) objSpecialtyModel.insert(objSpecialty);

        JOptionPane.showMessageDialog(null, "New Specialty's Data:\n"+ objSpecialty.toString(), "Succesfully inserted", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method has the function of
     * print all the records in a JOPtionPane message.
     */
    public static void getAllSpecialties(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        String listSpecialty = "Specialties list \n";

        for(Object iterator: objSpecialtyModel.findAll() ){

            Specialty objSpecialty = (Specialty) iterator;

            listSpecialty += objSpecialty.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listSpecialty);

    }

    /**
     * This method has the function of
     * get all the records in a string in the form of a list.
     * @param listSpecialty is a string that will store the list of authors.
     * @return List with the Specialties information.
     */
    public static String getAllSpecialties(String listSpecialty){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        listSpecialty = "Specialties list \n";

        for(Object iterator: objSpecialtyModel.findAll() ){

            Specialty objSpecialty = (Specialty) iterator;

            listSpecialty += objSpecialty.toString() + "\n";
        }

        return listSpecialty;
    }

    public static void delete(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        String listSpecialty = "";
        listSpecialty = getAllSpecialties(listSpecialty);

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null,listSpecialty + "\n Enter Specialty's id to delete"));

        Specialty objSpecialty = objSpecialtyModel.findById(idDelete);

        if(objSpecialty == null){
            JOptionPane.showMessageDialog(null,"Specialty not found");
        }else{
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure what to delete the Specialty?\n" + objSpecialty.toString());
            if (confirm == 0) objSpecialtyModel.delete(objSpecialty);
        }

    }

    public static void update(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();

        String listSpecialty = "";
        listSpecialty = getAllSpecialties(listSpecialty);

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listSpecialty+ "\nEnter the Specialty's ID to edit: "));

        Specialty objSpecialty = objSpecialtyModel.findById(idUpdate);

        if (objSpecialty == null){
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Enter new name:" , objSpecialty.getName());
            String description = JOptionPane.showInputDialog(null, "Enter new description:" , objSpecialty.getDescription());

            objSpecialty.setName(name);
            objSpecialty.setDescription(description);
            objSpecialtyModel.update(objSpecialty);
        }
    }
}
