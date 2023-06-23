package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.NewTableController.database;


public class DbConnectionController extends Application {

    private static String url = "jdbc:postgresql://";
    private static String user = "";
    private static String password = "";
    private String address = "";
    private String port = "";

    @FXML
    private TextField loginDb;

    @FXML
    private PasswordField passwordDb;

    @FXML
    private TextField addressDb;

    @FXML
    private TextField portDb;

    @FXML
    private Button closeWindowButton;

    @FXML
    public void createDatabase(ActionEvent event) throws SQLException {
        url = "jdbc:postgresql://";
        user = "";
        password = "";
        address = "";
        port = "";
        user = loginDb.getText();
        password = passwordDb.getText();
        address = addressDb.getText();
        port = portDb.getText();
        url = url+address+":"+port+"/";
        System.out.println(user);
        System.out.println(password);
        System.out.println(url);
        Connection connection = connect();
        Statement statement = connection.createStatement();
        statement.execute("create database "+database.getDatabaseName()+";");
        connection.close();
        url = url+database.getDatabaseName();
        connection = connect();
        statement = connection.createStatement();
        statement.execute(DbScriptGenerator.exportDatabaseToScript());
        connection.close();
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DbConnectionView.fxml"));
        stage.setTitle("MainApp");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void closeWindow(ActionEvent action){
        Stage stage = (Stage)closeWindowButton.getScene().getWindow();
        stage.close();
    }

}
