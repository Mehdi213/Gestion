package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Fin {

    public void Quitter(ActionEvent actionEvent) throws IOException {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void Ajouter(ActionEvent actionEvent) throws IOException {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutePFE.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Resultat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
