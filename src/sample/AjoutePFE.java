package sample;

import Model.Etudiant;
import Model.PFE;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.desktop.PrintFilesEvent;
import java.io.IOException;
import java.sql.*;

public class AjoutePFE {

    public JFXTextField Prof;
    public JFXTextField NomProjet;
    public JFXTextArea Description;
    public Label labelId;
    public Label labelNom;
    public Label labeldecrption;
  // insrtion d'un Projet

    public void insertProjet(PFE pfe) throws SQLException {
        String SQL = "INSERT INTO PFE(encadreur,nomprojet,description) "
                + "VALUES(?,?,?);";
        long id = 0;
        try (Connection conn = Controller.connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1,pfe.getProf());
                pstmt.setString(2, pfe.getNomProjet());
                pstmt.setString(3, pfe.getDescription());
                int affectedRows = pstmt.executeUpdate();
                pstmt.close();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }

 // verification  pour  permettre l'ajout d'un projet a la BDD
    public void Ajoute(ActionEvent actionEvent) throws IOException, SQLException {
        labelId.setText("");
        labelNom.setText("");
        labeldecrption.setText("");
    try{
        if (Prof.getText().equals("")|| Prof.getText().length()==0){
            Prof.setUnFocusColor(Paint.valueOf("#ff0000"));
            labelId.setText("vous n'avez pas saisi votre nom !!");
            labelId.setTextFill(Paint.valueOf("#ff0000" ));

        }else if (NomProjet.getText().equals("")||NomProjet.getText().length()==0){
            NomProjet.setUnFocusColor(Paint.valueOf("#ff0000"));
            labelNom.setText(" vous n'avez pas saisi le nom du projet !!");
            labelNom.setTextFill(Paint.valueOf("#ff0000"));

        }else if (Description.getText().equals("")||Description.getText().length()==0){
            Description.setUnFocusColor(Paint.valueOf("#ff0000"));
            labeldecrption.setText(" veuillez remplir la description svp !!");
            labeldecrption.setTextFill(Paint.valueOf("#ff0000"));
        }}catch (Exception e){
        System.out.println( e.getMessage());
    }


        PFE pfe = new PFE(String.valueOf(Prof.getText()), String.valueOf(NomProjet.getText()), String.valueOf(Description.getText()));
        insertProjet(pfe);
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Fin.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Resultat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
