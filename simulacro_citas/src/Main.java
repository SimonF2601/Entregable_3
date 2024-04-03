import controller.MedicoController;
import controller.SpecialtyController;
import database.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option = "";
        do {
            option = JOptionPane.showInputDialog(null, """
                    PRINCIPAL MENU
                    1. Management Specialties
                    2. Management Medicos
                    3. Management Patients
                    4. Management Appointments
                    5. Log out
                                    
                    CHOOSE AN OPTION:
                    """, "Riwi Appointment", JOptionPane.QUESTION_MESSAGE);

            switch (option) {

                case "1":
                    String optionMenuInternos = "";
                    do {
                        optionMenuInternos = JOptionPane.showInputDialog(null, """
                        SPECIALTIES MENU
                        1. Add an specialty
                        2. Edit an specialty
                        3. Delete an specialty
                        4. List all an specialty
                        5. Search an specialty 
                        6. Go back
                                        
                        CHOOSE AN OPTION:
                        """, "Riwi Appointment - Specialties", JOptionPane.QUESTION_MESSAGE);

                        switch (optionMenuInternos) {
                            case "1":
                                SpecialtyController.create();
                                break;
                            case "2":
                                SpecialtyController.update();
                                break;
                            case "3":
                                SpecialtyController.delete();
                                break;
                            case "4":
                                SpecialtyController.getAllSpecialties();
                                break;
                            case "5":
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "Going to main menu..📨", "Riwi Appointment", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Option not allowed", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                    }while(!optionMenuInternos.equals("6"));

                    break;
                case "2":

                    do {
                        optionMenuInternos = JOptionPane.showInputDialog(null, """
                        MECICOS MENU
                        1. Add a medico
                        2. Edit a medico
                        3. Delete a medico
                        4. List all medicos
                        5. Search a medico 
                        6. Go back
                                        
                        CHOOSE AN OPTION:
                        """, "Riwi Appointment - Medicos", JOptionPane.QUESTION_MESSAGE);

                        switch (optionMenuInternos) {
                            case "1":
                                MedicoController.create();
                                break;
                            case "2":
                                MedicoController.update();
                                break;
                            case "3":
                                MedicoController.delete();
                                break;
                            case "4":
                                MedicoController.getAllMedicos();
                                break;
                            case "5":
                                MedicoController.getByIdSpecialty();
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "Going to main menu..📨", "Riwi Appointment", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Option not allowed", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                    }while(!optionMenuInternos.equals("6"));

                    break;
                case "3":

                    do {
                        optionMenuInternos = JOptionPane.showInputDialog(null, """
                        PATIENTS MENU
                        1. Add a patient
                        2. Edit a patient
                        3. Delete a patient
                        4. List all patients
                        5. Search a patient 
                        6. Go back
                                        
                        CHOOSE AN OPTION:
                        """, "Riwi Appointment - Patients", JOptionPane.QUESTION_MESSAGE);

                        switch (optionMenuInternos) {
                            case "1":
                                break;
                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                            case "5":
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "Going to main menu..📨", "Riwi Appointment", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Option not allowed", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                    }while(!optionMenuInternos.equals("6"));

                    break;

                case "4":
                    do {
                        optionMenuInternos = JOptionPane.showInputDialog(null, """
                        MECICOS MENU
                        1. Add an appointment
                        2. Edit an appointment
                        3. Delete an appointment
                        4. List all appointments
                        5. Search an appointment 
                        6. Go back
                                        
                        CHOOSE AN OPTION:
                        """, "Riwi Appointment - Appointments", JOptionPane.QUESTION_MESSAGE);

                        switch (optionMenuInternos) {
                            case "1":
                                break;
                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                            case "5":
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "Going to main menu..📨", "Riwi Appointment", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Option not allowed", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                    }while(!optionMenuInternos.equals("6"));
                    break;

                case "5":
                    JOptionPane.showMessageDialog(null, "Logging out of the system ...🏥🏥", "Good bye", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Option not allowed", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }while(!option.equals("5"));


    }
}