package sample;

import Model.Etudiant;
import Model.PFE;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Projet implements Initializable {
    private static Stage dialogStage;
    public TreeTableView<PFE> Tab;

    public Projet(){
    }
    public static ObservableList<PFE> list= FXCollections.observableArrayList();

    public ObservableList<PFE> getList() {
        return list;
    }
    // charger tous les pfe existant dans la BDD
    public static void LoadPFE(){
        int i =0;
        try (Connection conn = Controller.connect()) {
            String sql ="select * from pfe";
            Statement statement = conn.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){

                int Id= resultSet.getInt("idprojet");
                String Prof=resultSet.getString("encadreur");
                String  Nom =resultSet.getString("NomProjet");
                String description = resultSet.getString("description");
                list.add(new PFE(Id,Prof,Nom,description)) ;
                System.out.println(list.get(i));
                i++;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
      public  void INIT(){


    }
    // initialisation des données de la treeTableView [PFE]
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeTableColumn IdCol = new TreeTableColumn("Id");
        IdCol.setPrefWidth(40);
        TreeTableColumn EncadreurCol = new TreeTableColumn("Encadreur");
        EncadreurCol.setPrefWidth(115);
        TreeTableColumn  NomCol = new TreeTableColumn("Nom Projet");
        NomCol.setPrefWidth(115);
        TreeTableColumn DescriptionCol =new TreeTableColumn("Description");
        DescriptionCol.setPrefWidth(330);
        Tab.getColumns().addAll(IdCol,EncadreurCol,NomCol,DescriptionCol);
        IdCol.setCellValueFactory(new TreeItemPropertyValueFactory<PFE,Number>("IdProjet"));
        EncadreurCol.setCellValueFactory(new TreeItemPropertyValueFactory<PFE,String>("Prof"));
        NomCol.setCellValueFactory(new TreeItemPropertyValueFactory<PFE,String>("NomProjet"));
        DescriptionCol.setCellValueFactory(new TreeItemPropertyValueFactory<PFE,String>("Description"));
        TreeItem root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        Tab.setRoot(root);
        Tab.setShowRoot(false);


    }


    public void Choix(ActionEvent actionEvent) throws IOException {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        System.out.println(getList());
        System.out.println(Tab.getSortOrder());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Resultat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        Controller.LoadEtudiant();
    }
            // affichage des information du PFE dans une alerte information
    public void information(MouseEvent mouseEvent) {
        try{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Le projet consiste");
        alert.setHeaderText("ID :"+Tab.getSelectionModel().getSelectedItem().getValue().getIdProjet()+" \n"+Tab.getSelectionModel().getSelectedItem().getValue().getNomProjet().toString()+"\n"+"Proffesseur :"+Tab.getSelectionModel().getSelectedItem().getValue().getProf());
        alert.setContentText(("Description :  "+Tab.getSelectionModel().getSelectedItem().getValue().getDescription().toString()));
        alert.showAndWait();}
        catch (Exception e){

        }

}}
