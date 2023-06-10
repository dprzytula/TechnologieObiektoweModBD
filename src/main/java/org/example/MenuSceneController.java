//package org.example;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//
//import java.io.*;
//import java.net.URISyntaxException;
//
//import static org.example.CreateDatabaseController.options;
//
//public class MenuSceneController {
//
//    @FXML
//    private Button addTableButtonMenu;
//
//    @FXML
//    private Button showERD;
//
//    @FXML
//    void menuAddTable(ActionEvent event) throws IOException {
//        Stage stage = new Stage();
//        Parent scene = FXMLLoader.load(getClass().getClassLoader().getResource("AddTableScene.fxml"));
//        stage.setScene(new Scene(scene, 880,520));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(addTableButtonMenu.getScene().getWindow());
//        stage.show();
//        options.clear();
//        options.add("");
//    }
//
//    @FXML
//    void menuRemoveTable(ActionEvent event) throws IOException {
//
//    }
//
//    @FXML
//    void menuAddRelation(ActionEvent event) throws IOException {
//        Stage stage = new Stage();
//        Parent scene = FXMLLoader.load(getClass().getClassLoader().getResource("AddRelationScene.fxml"));
//        stage.setScene(new Scene(scene, 880,520));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(addTableButtonMenu.getScene().getWindow());
//        stage.show();
//    }
//
//    @FXML
//    void menuDeleteRelation(ActionEvent event) throws IOException {
//
//    }
//
//    @FXML
//    void menuGenerateScript(ActionEvent event) throws IOException {
//       // Generator.generateDatabase(database);
//    }
//
//    @FXML
//    void menuExit(ActionEvent event) throws IOException {
//
//    }
//
//    @FXML
//    void menuViewFullDatabase(ActionEvent event) throws IOException, URISyntaxException {
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Diagram.fxml"));
//        stage.setTitle("MainApp");
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(showERD.getScene().getWindow());
//        stage.show();
//    }
//
//}
