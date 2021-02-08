package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Etudiant extends RecursiveTreeObject<Etudiant> {
    private IntegerProperty IdEtudiant;
    private StringProperty Mail;
    private StringProperty Nom;
    private StringProperty Prenom;
    private IntegerProperty IdProjet;
    private FloatProperty MoyGen;
    private  FloatProperty MoyUf1;
    private  FloatProperty MoyUf2;
    private  ObservableList<Etudiant> list = FXCollections.observableArrayList();


    public Etudiant (String Mail, String Nom, String Prenom,int IdProjet,float MoyGen,float MoyUf1,float MoyUf2){
        this.Mail=new SimpleStringProperty(Mail);
        this.Nom = new SimpleStringProperty(Nom);
        this.Prenom=new SimpleStringProperty(Prenom);
        this.IdProjet = new SimpleIntegerProperty(IdProjet);
        this.MoyGen=new SimpleFloatProperty(MoyGen);
        this.MoyUf1= new SimpleFloatProperty(MoyUf1);
        this.MoyUf2= new SimpleFloatProperty(MoyUf2); }
    public Etudiant ( int IdEtudiant,String Mail, String Nom, String Prenom,int IdProjet,float MoyGen,float MoyUf1,float MoyUf2){
        this.IdEtudiant= new SimpleIntegerProperty(IdEtudiant);
        this.Mail=new SimpleStringProperty(Mail);
        this.Nom = new SimpleStringProperty(Nom);
        this.Prenom=new SimpleStringProperty(Prenom);
        this.IdProjet = new SimpleIntegerProperty(IdProjet);
        this.MoyGen=new SimpleFloatProperty(MoyGen);
        this.MoyUf1= new SimpleFloatProperty(MoyUf1);
        this.MoyUf2= new SimpleFloatProperty(MoyUf2); }

    public Etudiant() {

    }

    public int getIdEtudiant() {
        return IdEtudiant.get();
    }

    public void setIdEtudiant(int idEtudiant) {
        this.IdEtudiant.set(idEtudiant);
    }

    public IntegerProperty idEtudiantProperty() {
        return IdEtudiant;
    }

    public void setIdProjet(int idProjet) {
        this.IdProjet.set(idProjet);
    }

    public void setMail(String mail) {
        this.Mail.set(mail);
    }


    public ObservableList getList() {
        return list;
    }

    public void setList(ObservableList list) {
        this.list = list;
    }

    public void setMoyGen(float moyGen) {
        this.MoyGen.set(moyGen);
    }
    public void setMoyUf1(float moyUf1) {
        this.MoyUf1.set(moyUf1);
    }
    public void setPrenom(String prenom) {
        this.Prenom.set(prenom);
    }
    public void setNom(String nom) {
        this.Nom.set(nom);
    }

    public float getMoyGen() {
        return MoyGen.get();
    }
    public int getIdProjet() {
        return IdProjet.get();
    }

    public String getNom() {
        return Nom.get();
    }
    public String getPrenom() {
        return Prenom.get();
    }

    public float getMoyUf1() {
        return MoyUf1.get();
    }
    public void setMoyUf2(float moyUf2) {
        this.MoyUf2.set(moyUf2);
    }
    public float getMoyUf2() {
        return MoyUf2.get();
    }
    public FloatProperty moyUf2Property() {
        return MoyUf2;
    }

    public StringProperty nomProperty() {
        return Nom;
    }

    public StringProperty prenomProperty() {
        return Prenom;
    }

    public FloatProperty moyUf1Property() {
        return MoyUf1;
    }

    public FloatProperty moyGenProperty() {
        return MoyGen;
    }

    public IntegerProperty idProjetProperty() {
        return IdProjet;
    }

    public String getMail() {
        return Mail.get();
    }

    public StringProperty mailProperty() {
        return Mail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(Mail, etudiant.Mail) &&
                Objects.equals(Nom, etudiant.Nom) &&
                Objects.equals(Prenom, etudiant.Prenom) &&
                Objects.equals(IdProjet, etudiant.IdProjet) &&
                Objects.equals(MoyGen, etudiant.MoyGen) &&
                Objects.equals(MoyUf1, etudiant.MoyUf1) &&
                Objects.equals(MoyUf2, etudiant.MoyUf2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Mail, Nom, Prenom, IdProjet, MoyGen, MoyUf1, MoyUf2);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "Mail=" + Mail +
                ", Nom=" + Nom +
                ", Prenom=" + Prenom +
                ", IdProjet=" + IdProjet +
                ", MoyGen=" + MoyGen +
                ", MoyUf1=" + MoyUf1 +
                ", MoyUf2=" + MoyUf2 +
                '}';
    }

    @Override
    public ObservableList<Etudiant> getChildren() {
        return super.getChildren();
    }

    @Override
    public void setChildren(ObservableList<Etudiant> children) {
        super.setChildren(children);
    }
}



