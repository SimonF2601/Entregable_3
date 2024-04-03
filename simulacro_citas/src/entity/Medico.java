package entity;

public class Medico {
    //Attributes
    private int id;
    private String names;
    private String lastNames;
    private int idSpecialty;

    private Specialty specialty;

    //Constructor

    public Medico() {
    }

    public Medico(int id, String names, String lastNames, int idSpecialty, Specialty specialty) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.idSpecialty = idSpecialty;
        this.specialty = specialty;
    }

    public Medico(String names, String lastNames, int idSpecialty) {
        this.names = names;
        this.lastNames = lastNames;
        this.idSpecialty = idSpecialty;
    }

    public Medico(int id, String names, String lastNames, int idSpecialty) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.idSpecialty = idSpecialty;
    }

    //Methods

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", idSpecialty=" + idSpecialty +
                '}';
    }

    public String medicoInformation(){
        return "▶ ID: " + id + "-  Name: " + names + " - Last names: " + lastNames + "- Specialty: " + specialty.getName() + "\n" ;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
