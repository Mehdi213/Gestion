package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class QuitterProf {
    public void Quitter(MouseEvent mouseEvent) {
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();

    }

    public void Plus(MouseEvent mouseEvent) throws IOException {
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AjouteProf.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Resultat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
