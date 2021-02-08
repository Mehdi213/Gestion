package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Enseignant {
    private IntegerProperty ID;
    private StringProperty Nom;
    private StringProperty Prenom;
    private StringProperty MDP;
    private StringProperty Mail;
    private StringProperty Specialite;
    public Enseignant(String Nom ,String Prenom, String MDP, String Mail, String Specialite){
        this.Nom=new SimpleStringProperty(Nom);
        this.Prenom= new SimpleStringProperty(Prenom);
        this.MDP= new SimpleStringProperty(MDP);
        this.Mail= new SimpleStringProperty(Mail);
        this.Specialite= new SimpleStringProperty(Specialite);
    }
    public Enseignant(int ID,String Nom ,String Prenom,String MDP, String Mail, String Specialite){
        this.ID= new SimpleIntegerProperty(ID);
        this.Nom=new SimpleStringProperty(Nom);
        this.Prenom= new SimpleStringProperty(Prenom);
        this.MDP= new SimpleStringProperty(MDP);
        this.Mail= new SimpleStringProperty(Mail);
        this.Specialite= new SimpleStringProperty(Specialite);
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public String getMail() {
        return Mail.get();
    }

    public String getNom() {
        return Nom.get();
    }

    public String getPrenom() {
        return Prenom.get();
    }

    public String getSpecialite() {
        return Specialite.get();
    }

    public StringProperty mailProperty() {
        return Mail;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public void setNom(String nom) {
        this.Nom.set(nom);
    }

    public StringProperty prenomProperty() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        this.Prenom.set(prenom);
    }

    public void setMail(String mail) {
        this.Mail.set(mail);
    }

    public StringProperty specialiteProperty() {
        return Specialite;
    }

    public void setSpecialite(String specialite) {
        this.Specialite.set(specialite);
    }

    public StringProperty nomProperty() {
        return Nom;
    }

    public String getMDP() {
        return MDP.get();
    }

    public void setMDP(String MDP) {
        this.MDP.set(MDP);
    }

    public StringProperty MDPProperty() {
        return MDP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enseignant that = (Enseignant) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(Nom, that.Nom) &&
                Objects.equals(Prenom, that.Prenom) &&
                Objects.equals(Mail, that.Mail) &&
                Objects.equals(Specialite, that.Specialite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Nom, Prenom, Mail, Specialite);
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "ID=" + ID +
                ", Nom=" + Nom +
                ", Prenom=" + Prenom +
                ", Mail=" + Mail +
                ", Specialite=" + Specialite +
                '}';
    }
}
