package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.IDN;

public class PFE  extends RecursiveTreeObject <PFE> {
    private  IntegerProperty IdProjet;
    private StringProperty Prof;
    private StringProperty NomProjet;
    private StringProperty Description;
    private  ObservableList list= FXCollections.observableArrayList();
    public  PFE(String Prof , String Nomprojet, String Description){
        this.Prof=new SimpleStringProperty(Prof);
        this.NomProjet= new SimpleStringProperty(Nomprojet);
        this.Description=new SimpleStringProperty(Description);

    }

    public  PFE(){

    }

    public  ObservableList getList() {
        return list;
    }

    public void setIdProjet(int idProjet) {
        this.IdProjet.set(idProjet);
    }

    public void setList(ObservableList list) {
        this.list = list;
    }

    public  PFE(int Idprojet, String Prof , String Nomprojet, String Description){
        this.IdProjet = new SimpleIntegerProperty(Idprojet);
        this.Prof=new SimpleStringProperty(Prof);
        this.NomProjet= new SimpleStringProperty(Nomprojet);
        this.Description=new SimpleStringProperty(Description);

    }


    public String getProf() {
        return Prof.get();
    }

    public StringProperty profProperty() {
        return Prof;
    }

    public void setProf(String prof) {
        this.Prof.set(prof);
    }

    public String getDescription() {
        return Description.get();
    }

    public String getNomProjet() {
        return NomProjet.get();
    }

    public StringProperty descriptionProperty() {
        return Description;
    }

    public StringProperty nomProjetProperty() {
        return NomProjet;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public void setNomProjet(String nomProjet) {
        this.NomProjet.set(nomProjet);
    }

    public int getIdProjet() {
        return IdProjet.get();
    }

    public IntegerProperty idProjetProperty() {
        return IdProjet;
    }


    @Override
    public String toString() {
        return "PFE{" +
                "IdProjet=" + IdProjet +
                ", Prof=" + Prof +
                ", NomProjet=" + NomProjet +
                ", Description=" + Description +
                '}';
    }
}
