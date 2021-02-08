package sample;

import Model.Etudiant;
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

public class AuthetificationEnseignant {
    private static Stage dialogStage;
     public JFXTextField idEns;
    public JFXPasswordField MdpEns;
    public Label labelID;
    public Label labekmdp;


    // authentification pour permettre d'ajouter un Projet
    public void Cnx(ActionEvent actionEvent) {

        labekmdp.setText("");
        labelID.setText("");

        if (idEns.getText().equals("")|| idEns.getText().length()==0){
            idEns.setUnFocusColor(Paint.valueOf("#ff0000"));
            labelID.setText("   Vous n'avez pas saisi votre ID !!");
            labelID.setTextFill(Paint.valueOf("#ff0000"));

        }else
        if (MdpEns.getText().equals("")||MdpEns.getText().length()==0){
            MdpEns.setUnFocusColor(Paint.valueOf("#ff0000"));
            labekmdp.setText("     Vous n'avez pas saisi votre MDP !!");
            labekmdp.setTextFill(Paint.valueOf("#ff0000"));
        }else
            try {
                Integer.parseInt(idEns.getText());
            }catch (NumberFormatException e){ idEns.setUnFocusColor(Paint.valueOf("#ff0000"));
                labelID.setText("          Votre ID est incorrecte  !!");
                labelID.setTextFill(Paint.valueOf("#ff0000"));

            }
        try (Connection conn = Controller.connect()) {
                int ID = Integer.parseInt(idEns.getText());
                String MDP = String.valueOf(MdpEns.getText());
                String sql ="SELECT mdp FROM enseignant WHERE id ='"+ID+"';" ;
                Statement statement = conn.createStatement();
                ResultSet resultSet =statement.executeQuery(sql);
                System.out.println(sql);
                while (resultSet.next()){
                  //int identifiant = resultSet.getInt("id");
                  String motdepasse =resultSet.getString("mdp");
                    System.out.println(motdepasse);
                  if (MDP.equals(motdepasse)){
                      ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                      Parent root = FXMLLoader.load(getClass().getResource("AjoutePFE.fxml"));
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
                  }

                  statement.close();
                }

            } catch (SQLException | IOException e) {
                e.printStackTrace();


    }
}}
