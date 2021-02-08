package sample;

import Model.Email;
import Model.Etudiant;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.commons.mail.EmailException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Controller implements Initializable

        {  private static String smtp = "smtp.gmail.com";
            private static String email_from = "scolariteigmo@gmail.com";
            private static String email_password = "Mimouni1234567890";
            //private static String email_to = "email@gmail.com";
            private static boolean ssl = true;
            private static boolean tls = false;
            private static boolean debug_email = true;
            private static String subject = "(s)AINT";
            private static String port = "587";
            public Label urgent;
            ArrayList<Etudiant> sameID = new ArrayList<>();
            public TreeTableView<Etudiant> Tab = new TreeTableView();

            public Controller(){ }

            Etudiant etude =new Etudiant();

            public Label IdProLabel;
            public Label MoyGenLabel;
            public Label MoyUf1Label;
            public Label MoyUf2Label;
            public Label PrenomLabel;
            public Label NomLabel;
            public Label MailLabel;
            public JFXTextField MailText;
            public JFXTextField IdText;
            public JFXTextField NomText;
            public JFXTextField PrenomText;
            public JFXTextField IdProjetText;
            public JFXTextField MoyGenText;
            public JFXTextField MoyUF1Text;
            public JFXTextField MoyUF2Text;
            public JFXButton submit;


            public static ObservableList<Etudiant> list= FXCollections.observableArrayList();
            public ObservableList<Etudiant> getList() {
                return list;
            }
           //verifier si l'adresse Mail est valide ou pas
            public static boolean isValid(String email)
            {
                String formeEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";

                Pattern pattern = Pattern.compile(formeEmail);
                if (email == null)
                    return false;
                return pattern.matcher(email).matches();
            }

            // le CNX a la BDD
            public static Connection connect() {
                final String url = "jdbc:postgresql://localhost:5432/projet";
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(url);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                return conn;
            }
            // insertion d'un etudiant dans la BDD
            public static void insertEtudiant(Etudiant etudiant) {
                String SQL = "INSERT INTO Etudiant(mail,nom, prenom,idprojet,moygen ,moyuf1,moyuf2) "
                        + "VALUES(?,?,?,?,?,?,?);";
                long id = 0;
                try (Connection conn = connect()) {
                    try (PreparedStatement pstmt = conn.prepareStatement(SQL,
                            Statement.RETURN_GENERATED_KEYS)) {
                        pstmt.setString(1, etudiant.getMail());
                        pstmt.setString(2, etudiant.getNom());
                        pstmt.setString(3, etudiant.getPrenom());
                        pstmt.setInt(4, etudiant.getIdProjet());
                        pstmt.setFloat(5, etudiant.getMoyGen());
                        pstmt.setFloat(6, etudiant.getMoyUf1());
                        pstmt.setFloat(7, etudiant.getMoyUf2());
                        int affectedRows = pstmt.executeUpdate();
                        pstmt.close();

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            //la liste des etudiant qui ont deja choisi leur PFE
            public static void LoadEtudiant(){
                int i=0;
                try (Connection conn = connect()) {
                    String sql ="select * from etudiant";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet =statement.executeQuery(sql);
                    while (resultSet.next()){

                        String Mail= resultSet.getString("mail");
                        String Nom =resultSet.getString("nom");
                        String Prenom =resultSet.getString("prenom");
                        int IdProjet = resultSet.getInt("idProjet");
                        float Moygen= resultSet.getFloat("moygen");
                        float Moyuf1= resultSet.getFloat("moyuf1");
                        float Moyuf2= resultSet.getFloat("moyuf2");
                        list.add(new Etudiant(Mail,Nom,Prenom, IdProjet,Moygen,Moyuf1,Moyuf2)) ;
                        System.out.println(list.get(i));
                        i++;
                    }
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
            // Validation du formulaire avec les restriction TextField vide et type de la donnée du textField
    public  void valider(ActionEvent actionEvent) throws Exception {
        MailLabel.setText("");
        NomLabel.setText("");
        PrenomLabel.setText("");
        IdProLabel.setText("");
        MoyGenLabel.setText("");
        MoyUf1Label.setText("");
        MoyUf2Label.setText("");
        try{

                     if (MailText.getText().equals("")||MailText.getText().length()==0){
                         MailText.setUnFocusColor(Paint.valueOf("#ff0000"));
                         MailLabel.setText("   veuillez saisir votre Mail svp !!");
                         MailLabel.setTextFill(Paint.valueOf("#ff0000"));

                     }else
                     if (!isValid(MailText.getText()))
                        {
                            MailText.setUnFocusColor(Paint.valueOf("RED"));
                            MailLabel.setText("votre adresse n'est pas valide !!!");
                            MailLabel.setTextFill(Paint.valueOf("RED"));

                        }
                     else
                     if (NomText.getText().equals("")||NomText.getText().length()==0){
                         NomText.setUnFocusColor(Paint.valueOf("#ff0000"));
                         NomLabel.setText("    veuillez saisir votre Nom svp !!");
                         NomLabel.setTextFill(Paint.valueOf("#ff0000"));
                     }else
                     if (PrenomText.getText().equals("")||PrenomText.getText().length()==0){
                         PrenomText.setUnFocusColor(Paint.valueOf("#ff0000"));
                         PrenomLabel.setText(" veuillez saisir votre Prénom svp !!");
                         PrenomLabel.setTextFill(Paint.valueOf("#ff0000"));

                     }else
                     if (IdProjetText.getText().equals("")||IdProjetText.getText().length()==0){
                         IdProjetText.setUnFocusColor(Paint.valueOf("#ff0000"));
                         IdProLabel.setText(" veuillez saisir l'ID du projet svp !!");
                         IdProLabel.setTextFill(Paint.valueOf("#ff0000"));

                     }
                     else
                     if (MoyGenText.getText().equals("")||MoyGenText.getText().length()==0){
                         MoyGenText.setUnFocusColor(Paint.valueOf("#ff0000"));
                         MoyGenLabel.setText(" veuillez saisir votre Moy General svp !!");
                         MoyGenLabel.setTextFill(Paint.valueOf("#ff0000"));

                     }else
                     if (MoyUF1Text.getText().equals("")||MoyUF1Text.getText().length()==0){
                             MoyUF1Text.setUnFocusColor(Paint.valueOf("#ff0000"));
                             MoyUf1Label.setText(" veuillez saisir votre Moy UF1 svp !!");
                             MoyUf1Label.setTextFill(Paint.valueOf("#ff0000"));
                         }
                     else

                       if (MoyUF2Text.getText().equals("")||MoyUF2Text.getText().length()==0){
                             MoyUF2Text.setUnFocusColor(Paint.valueOf("#ff0000"));
                             MoyUf2Label.setText(" veuillez saisir votre Moy UF2 svp !!");
                             MoyUf2Label.setTextFill(Paint.valueOf("#ff0000"));
                         }else
                       if (Float.parseFloat(MoyGenText.getText().toString())<0 || Float.parseFloat(MoyGenText.getText())>20){
                         MoyGenText.setUnFocusColor(Paint.valueOf("RED"));
                         MoyGenLabel.setText("  votre Moy General n'est pas borné  !!!");
                         MoyGenLabel.setTextFill(Paint.valueOf("RED"));


                     }
                       else
                       if ((Float.parseFloat(MoyUF1Text.getText()))<0 || (Float.parseFloat(MoyUF1Text.getText()))>20){
                         MoyUF1Text.setUnFocusColor(Paint.valueOf("RED"));
                         MoyUf1Label.setText("   votre Moy UF1 n'est pas borné  !!!");
                         MoyUf1Label.setTextFill(Paint.valueOf("RED"));


                     }
                        else
                       if ((Float.parseFloat(MoyUF2Text.getText()))<0 || (Float.parseFloat(MoyUF2Text.getText()))>20){
                         MoyUF2Text.setUnFocusColor(Paint.valueOf("RED"));
                         MoyUf2Label.setText("   votre Moy UF2 n'est pas borné  !!!");
                         MoyUf2Label.setTextFill(Paint.valueOf("RED"));



                     }else;


        }
        catch (Exception e){


        try {
            Float.parseFloat(IdProjetText.getText());

        }catch (NumberFormatException e2){
            IdProjetText.setUnFocusColor(Paint.valueOf("RED"));
            IdProLabel.setText("votre Id Projet n'est pas valide !!!");
            IdProLabel.setTextFill(Paint.valueOf("RED"));}
        try {
            Float.parseFloat(MoyGenText.getText());

        }catch (NumberFormatException e1){
            MoyGenText.setUnFocusColor(Paint.valueOf("RED"));
            MoyGenLabel.setText("votre Moy General n'est pas valide !!!");
            MoyGenLabel.setTextFill(Paint.valueOf("RED"));}




        try {
            Float.parseFloat(MoyUF1Text.getText());
        }catch (NumberFormatException e2) {
            MoyUF1Text.setUnFocusColor(Paint.valueOf("RED"));
            MoyUf1Label.setText("votre Moy UF1 n'est pas valide !!!");
            MoyUf1Label.setTextFill(Paint.valueOf("RED"));}

        try {
            Float.parseFloat(MoyUF2Text.getText());
        }catch (NumberFormatException e3) {
            MoyUF2Text.setUnFocusColor(Paint.valueOf("RED"));
            MoyUf2Label.setText("votre Moy UF2 n'est pas valide !!!");
            MoyUf2Label.setTextFill(Paint.valueOf("RED"));}}
            Etudiant etudiant = new Etudiant(
                                    String.valueOf(MailText.getText()),
                                    String.valueOf(NomText.getText()),
                                    String.valueOf(PrenomText.getText()),
                                    Integer.parseInt(IdProjetText.getText()),
                                    Float.parseFloat(MoyGenText.getText()),
                                    Float.parseFloat(MoyUF1Text.getText()),
                                    Float.parseFloat(MoyUF2Text.getText()));
                            insertEtudiant(etudiant);
                            getList().add(etudiant);
                            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                            Parent root = FXMLLoader.load(getClass().getResource("Tab.fxml"));
                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("Resultat");
                            primaryStage.setScene(new Scene(root));
                            primaryStage.setResizable(false);
                            primaryStage.show();
                            etudiant.getList().removeAll(etudiant.getList());
                            LoadsameEtudiant(etudiant);

                            }

                    // initialisation des données de la TreeTableView  [Etudiant]
            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                TreeTableColumn MailCol = new TreeTableColumn("Mail");
                MailCol.setPrefWidth(180);
                TreeTableColumn NomCOl = new TreeTableColumn("Nom");
                NomCOl.setPrefWidth(85);
                TreeTableColumn PrenomCol = new TreeTableColumn("Prénom");
                PrenomCol.setPrefWidth(150);
                TreeTableColumn IdProjetCol =new TreeTableColumn("ID Projet");
                IdProjetCol.setPrefWidth(85);
                TreeTableColumn MoyGenCol = new TreeTableColumn("Moy Gen");
                MoyGenCol.setPrefWidth(85);
                TreeTableColumn MoyUF1Col = new TreeTableColumn("Moy Uf1");
                MoyUF1Col.setPrefWidth(85);
                TreeTableColumn MoyUF2Col = new TreeTableColumn("Moy Uf2");
                MoyUF2Col.setPrefWidth(85);
                Tab.getColumns().addAll(MailCol,NomCOl,PrenomCol,IdProjetCol,MoyGenCol,MoyUF1Col,MoyUF2Col);
                MailCol.setCellValueFactory(new TreeItemPropertyValueFactory<Etudiant,String>("Mail"));
                NomCOl.setCellValueFactory(new TreeItemPropertyValueFactory<Etudiant,String>("Nom"));
                PrenomCol.setCellValueFactory(new TreeItemPropertyValueFactory<Etudiant,String>("Prenom"));
                IdProjetCol.setCellValueFactory(new TreeItemPropertyValueFactory<Etudiant,Number>("IdProjet"));
                MoyGenCol.setCellValueFactory(new TreeItemPropertyValueFactory<Etudiant,Number>("MoyGen"));
                MoyUF1Col.setCellValueFactory(new TreeItemPropertyValueFactory<Etudiant,Number>("MoyUf1"));
                MoyUF2Col.setCellValueFactory(new TreeItemPropertyValueFactory<Etudiant,Number>("MoyUf2"));
                TreeItem root = new RecursiveTreeItem<>(list,RecursiveTreeObject::getChildren);
                Tab.setRoot(root);
                Tab.setShowRoot(false);




            }
            // traitement apres inscription de l'etudiant pour assurer que chaque etudiant a un projet diff des autres


            public void LoadsameEtudiant (Etudiant etudiant) throws Exception {
                try (Connection conn = connect()) {
                    String sql ="select * from etudiant where idprojet ="+etudiant.getIdProjet()+";";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet =statement.executeQuery(sql);
                    while (resultSet.next()){

                        String Mail= resultSet.getString("mail");
                        String Nom =resultSet.getString("nom");
                        String Prenom =resultSet.getString("prenom");
                        int IdProjet = resultSet.getInt("idProjet");
                        float Moygen= resultSet.getFloat("moygen");
                        float Moyuf1= resultSet.getFloat("moyuf1");
                        float Moyuf2= resultSet.getFloat("moyuf2");
                        sameID.add(new Etudiant(Mail,Nom,Prenom, IdProjet,Moygen,Moyuf1,Moyuf2)) ;

                    }
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(sameID.size()!=0){
               sameID.sort(new Comparator<Etudiant>() {
                   @Override
                   public int compare(Etudiant etudiant, Etudiant t1) {
                       return (int) (t1.getMoyGen()-etudiant.getMoyGen());
                   }
               });
                deleteEtudiant(etudiant);
                insertEtudiant(sameID.get(0));

                    //EnvoieEmail(sameID.get(i).getMail());
                    new Thread(() -> {
                        Email email = new Email(smtp, email_from, email_password, port, ssl, tls, debug_email);
                        email.sendSimpleEmail(sameID.get(1).getMail(), "PFE non validé !! ", "Votre Projet a été refuser cette cause \" +\n" +
                                "                                    \"est du a un autre etudiant qui l'a obtenu car\" +\n" +
                                "                                    \"sa moyenne et superieur a la votre alors veuillez \" +\n" +
                                "                                    \"refaire votre inscrption de les bref délai");

                    }).start();


                }else if (sameID.size()==0)insertEtudiant(new Etudiant(
                        etudiant.getMail(),
                        etudiant.getNom(),
                        etudiant.getPrenom(),
                        etudiant.getIdProjet(),
                        etudiant.getMoyGen(),
                        etudiant.getMoyUf1(),
                        etudiant.getMoyUf2()));
                }
            public int deleteEtudiant(Etudiant etudiant) {
            String SQL = "DELETE FROM Etudiant WHERE idprojet = ?";

            int affectedrows = 0;

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(SQL)) {

                pstmt.setInt(1, etudiant.getIdProjet());

                affectedrows = pstmt.executeUpdate();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return affectedrows;
        }




            public void Quitter(ActionEvent actionEvent) {
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        }

