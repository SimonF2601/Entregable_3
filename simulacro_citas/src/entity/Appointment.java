package entity;

import java.sql.Date;

public class Appointment {
    //Attributes
    private int id;

    private Patient patient;

    private Medico medico;
    private Date date;
    private String time;
    private String reason;


    //Constructor
    public Appointment() {
    }

    public Appointment(Patient patient, Medico medico, Date date, String time, String reason) {
        this.patient = patient;
        this.medico = medico;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    //Methods

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", idPatient=" + patient.getId() +
                ", idMedico=" + medico.getId() +
                ", date=" + date +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                '}';
    }

    public String AppointmentInformation (){
        return "Appointment Information \n" +
                "Patient name: " + patient.getNames() + " " + patient.getLastNames() + "\n" +
                "Medico name : " + medico.getNames() + " " + medico.getLastNames() +"\n" +
                "Date: " + date + "\n" +
                "time: " + time + "\n" +
                "reasons: " + reason;
    }

    //Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
