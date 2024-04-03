package entity;
import java.time.LocalDate; //Representa una fecha sin zona horaria en el sistema de calendario ISO-8601, como “2007-12-03”.
import java.time.LocalTime; //Representa la hora sin zona horaria en el sistema de calendario ISO-8601, como “10:15:30”.

public class Appointment {
    //Attributes
    private int id;
    private int idPatient;
    private int idMedico;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    //Constructor
    public Appointment() {
    }

    public Appointment(int id, int idPatient, int idMedico, LocalDate date, LocalTime time, String reason) {
        this.id = id;
        this.idPatient = idPatient;
        this.idMedico = idMedico;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    //Methods

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", idPatient=" + idPatient +
                ", idMedico=" + idMedico +
                ", date=" + date +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                '}';
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
