package sample;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Choix {
    public JFXRadioButton Etudiant;
    public JFXRadioButton enseignant;
    private static Stage dialogStage;
    public ImageView View;
    public JFXRadioButton Admin;

    //choisir le mode de cnx [Admin,Enseignant,Etudiant]
    public void submit(ActionEvent actionEvent) throws IOException {
        if (enseignant.isSelected()){
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AuthentificationEnseignant.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Professeur");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();

        }else if (Etudiant.isSelected()){
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Projet.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Etudiant");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
            Projet.LoadPFE();

        } else if (Admin.isSelected()) {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("CnxAdmin.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Administrateur");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();


        }else
            if (!(enseignant.isSelected() && !(Etudiant.isSelected()) && !(Admin.isSelected()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("WARNING !!");
            alert.setContentText("veuillez selectionner un choix !");
            alert.showAndWait();

        }
    }

    public void selectetudiant(ActionEvent actionEvent) {
        enseignant.setSelected(false);
        Admin.setSelected(false);
    }

    public void selectenseignant(ActionEvent actionEvent) {
        Etudiant.setSelected(false);
        Admin.setSelected(false);
    }

    public void selectAdmin(ActionEvent actionEvent) {
        Etudiant.setSelected(false);
        enseignant.setSelected(false);
    }

}
