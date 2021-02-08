package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Admin extends RecursiveTreeObject<Admin> {
    private IntegerProperty ID;
    private StringProperty Mail;
    private StringProperty MDP;
    public Admin(int ID, String MDP){
        this.ID= new SimpleIntegerProperty(ID);
        this.MDP= new SimpleStringProperty(MDP);
    }

    public int getID() {
        return ID.get();

    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getMail() {
        return Mail.get();
    }

    public StringProperty mailProperty() {
        return Mail;
    }

    public StringProperty MDPProperty() {
        return MDP;
    }

    public String toString() {
        return "Admin{" +
                "ID=" + ID +
                ", Mail=" + Mail +
                ", MDP=" + MDP +
                '}';
    }

    public void setMail(String mail) {
        this.Mail.set(mail);
    }

    public void setMDP(String MDP) {
        this.MDP.set(MDP);
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(ID, admin.ID) &&
                Objects.equals(Mail, admin.Mail) &&
                Objects.equals(MDP, admin.MDP);
    }

    public int hashCode() {
        return Objects.hash(ID, Mail, MDP);
    }

    public String getMDP() {
        return MDP.get();
    }
}
