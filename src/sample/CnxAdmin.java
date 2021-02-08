package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class CnxAdmin {
    private static Stage dialogStage;
    public JFXTextField IdAdmin;
    public Label IdLabel;
    public Label MdpLabel;
    public JFXPasswordField MdpAdmin;


    // CNX de l'administrateur pour ajouter un enseignant
    public void Cnx(ActionEvent actionEvent) throws SQLException, IOException {
        try {


        IdLabel.setText("");
        MdpLabel.setText("");

        if (IdAdmin.getText().equals("")|| IdAdmin.getText().length()==0){
            IdAdmin.setUnFocusColor(Paint.valueOf("#ff0000"));
            IdLabel.setText("                Vous n'avez pas saisi votre ID !!");
            IdLabel.setTextFill(Paint.valueOf("#ff0000"));

        }
        if (MdpAdmin.getText().equals("")||MdpAdmin.getText().length()==0){
            MdpAdmin.setUnFocusColor(Paint.valueOf("#ff0000"));
            MdpLabel.setText("              Vous n'avez pas saisi votre MDP !!");
            MdpLabel.setTextFill(Paint.valueOf("#ff0000"));
        }
        try {
            Integer.parseInt(IdAdmin.getText());
        }catch (NumberFormatException e){
            IdAdmin.setUnFocusColor(Paint.valueOf("#ff0000"));
            IdLabel.setText("                        Votre ID est incorrecte  !!");
            IdLabel.setTextFill(Paint.valueOf("#ff0000"));

        }

        try (Connection conn = Controller.connect()) {
            System.out.println(IdAdmin.getText());
            int ID = Integer.parseInt(IdAdmin.getText());
            String MDP = String.valueOf(MdpAdmin.getText());
            String sql ="SELECT mdp FROM Admin WHERE id ='"+ID+"';" ;
            Statement statement = conn.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            System.out.println(sql);
            while (resultSet.next()){
                // int identifiant = resultSet.getInt("id");
                String motdepasse =resultSet.getString("mdp");
                System.out.println(motdepasse);
                if (MDP.equals(motdepasse)){
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("Ajouteprof.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Resultat");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.setResizable(false);
                    primaryStage.show();
                } else if (motdepasse!=MDP){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Invalid Fields");
                    alert.setHeaderText("ERROR !!");
                    alert.setContentText("Votre ID ou MDP sont incorrecte !!");
                    alert.showAndWait();
                }}
}}catch (Exception e){Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("ERROR !!");
            alert.setContentText("Votre ID n'est Correcte !!");
            alert.showAndWait();}}}
