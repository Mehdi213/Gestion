package sample;

import Model.Enseignant;
import Model.Etudiant;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Ajouteprof {


    public JFXTextField Nom;
    public JFXTextField Prenom;
    public JFXTextField Mail;
    public JFXTextField Specialite;
    public Label NomLabel;
    public Label PrenomLabel;
    public Label MdpLabel;
    public Label MailLabel;
    public Label specialitelabel;
    public JFXPasswordField MDP;

    // insrtion d'un Enseignant dans la BDD
    public void insertProf(Enseignant enseignant) {
        String SQL = "INSERT INTO Enseignant(nom, prenom,mdp,mail,specialite) "
                + "VALUES(?,?,?,?,?);";
        try (Connection conn = Controller.connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, enseignant.getNom());
                pstmt.setString(2, enseignant.getPrenom());
                pstmt.setString(3, enseignant.getMDP());
                pstmt.setString(4, enseignant.getMail());
                pstmt.setString(5, enseignant.getSpecialite());
                int affectedRows = pstmt.executeUpdate();
                pstmt.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       // verification  lors de l'ajout de l'enseignant
    public void Ajoute(ActionEvent actionEvent) throws IOException {
        NomLabel.setText("");
        PrenomLabel.setText("");
        MailLabel.setText("");
        MdpLabel.setText("");
        specialitelabel.setText("");

        if (Nom.getText().equals("") || Nom.getText().length()==0) {
            Nom.setUnFocusColor(Paint.valueOf("#ff0000"));
            NomLabel.setText("vous n'avez pas saisi le nom !!");
            NomLabel.setTextFill(Paint.valueOf("#ff0000" )); }
        else if (Prenom.getText().equals("") || Prenom.getText().length()==0) {
            Prenom.setUnFocusColor(Paint.valueOf("#ff0000"));
            PrenomLabel.setText("vous n'avez pas saisi le Prenom !!");
            PrenomLabel.setTextFill(Paint.valueOf("#ff0000" ));}
        else if (MDP.getText().equals("") || MDP.getText().length()==0){
            MDP.setUnFocusColor(Paint.valueOf("#ff0000"));
            MdpLabel.setText("vous n'avez pas saisi le MDP !!");
            MdpLabel.setTextFill(Paint.valueOf("#ff0000" )); }
        else if (Mail.getText().equals("") || Mail.getText().length()==0) {
          Mail.setUnFocusColor(Paint.valueOf("#ff0000"));
          MailLabel.setText("vous n'avez pas saisi le Mail !!");
          MailLabel.setTextFill(Paint.valueOf("#ff0000" )); }
        else  if (!Controller.isValid(Mail.getText().toString())){
            Mail.setUnFocusColor(Paint.valueOf("#ff0000"));
            MailLabel.setText(" votre adresse mail n'est pas valide !!");
            MailLabel.setTextFill(Paint.valueOf("#ff0000" ));

        }
        else if (Specialite.getText().equals("") || Specialite.getText().length()==0){
          Specialite.setUnFocusColor(Paint.valueOf("#ff0000"));
          specialitelabel.setText("vous n'avez pas saisi la specialité  !!");
          specialitelabel.setTextFill(Paint.valueOf("#ff0000" )); }
        else
        insertProf(new Enseignant(Nom.getText(), Prenom.getText(),MDP.getText() , Mail.getText(), Specialite.getText()));
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("QuitterProf.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Resultat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
