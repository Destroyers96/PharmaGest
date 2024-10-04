package com.example.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

public class LoginController {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button changeButton;
    @FXML
    private Button nonButton;
    @FXML
    private Button maintenanceButton;
    @FXML
    private Button returnButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button utilisateurButton;
    @FXML
    private Button returnmaintenanceButton;

    @FXML
    public void loginButtonOnAction(ActionEvent e) throws IOException {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            // Validation BDD
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please enter username and password!");
        }
    }
    @FXML
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT COUNT(0) FROM public.\"utilisateur\"\n" +
                "WHERE \"identifiant\" ='" + usernameTextField.getText() + "' AND \"mot_de_passe\" = '" + passwordPasswordField.getText() + "';";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                    Scene nouvelleScene = new Scene(loader.load());
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(nouvelleScene);
                    stage.setTitle(usernameTextField.getText());
                }else{
                    loginMessageLabel.setText("Connexion invalide. Veuillez réessayer.");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void cancelButtonOnAction(ActionEvent e) {
        System.exit(0);
    }
    @FXML
    public void changeButtonOnAction(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene nouvelleScene = new Scene(loader.load());
        Stage stage = (Stage) changeButton.getScene().getWindow();
        stage.setScene(nouvelleScene);
        stage.setTitle("Login");
    }
    @FXML
    public void leaveButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Déconnexion.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 195, 80));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    @FXML
    public void nonButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) nonButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void maintenanceButtonOnAction(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Maintenance.fxml"));
        Scene nouvelleScene = new Scene(loader.load());
        Stage stage = (Stage) maintenanceButton.getScene().getWindow();
        stage.setScene(nouvelleScene);
    }
    @FXML
    public void returnButtonOnAction(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Scene nouvelleScene = new Scene(loader.load());
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.setScene(nouvelleScene);
    }
    @FXML
    public void utilisateurButtonOnAction(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Utilisateur.fxml"));
        Scene nouvelleScene = new Scene(loader.load());
        Stage stage = (Stage) utilisateurButton.getScene().getWindow();
        stage.setScene(nouvelleScene);
    }
    @FXML
    public void returnmaintenanceButtonOnAction(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Maintenance.fxml"));
        Scene nouvelleScene = new Scene(loader.load());
        Stage stage = (Stage) returnmaintenanceButton.getScene().getWindow();
        stage.setScene(nouvelleScene);
    }
}