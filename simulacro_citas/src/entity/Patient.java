package entity;
import java.sql.Date;

public class Patient {
    //Attributes
    private int id;
    private String names;
    private String lastNames;
    private Date dateBorn;
    private String identityDocument;

    //Constructor

    public Patient() {
    }

    public Patient(int id, String names, String lastNames, Date dateBorn, String identityDocument) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.dateBorn = dateBorn;
        this.identityDocument = identityDocument;
    }

    public Patient(String names, String lastNames, Date dateBorn, String identityDocument) {
        this.names = names;
        this.lastNames = lastNames;
        this.dateBorn = dateBorn;
        this.identityDocument = identityDocument;
    }
    //Methods

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", dateBorn=" + dateBorn +
                ", identityDocument='" + identityDocument + '\'' +
                '}';
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

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }
}
