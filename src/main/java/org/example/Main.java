package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @FXML
    private TextField name0;

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private TextField name3;

    @FXML
    private TextField name4;

    @FXML
    private TextField name5;

    @FXML
    private TextField name6;

    @FXML
    private ComboBox type0;

    @FXML
    private ComboBox type1;

    @FXML
    private ComboBox type2;

    @FXML
    private ComboBox type3;

    @FXML
    private ComboBox type4;

    @FXML
    private ComboBox type5;

    @FXML
    private ComboBox type6;

    @FXML
    private Button addTableButton;

    @FXML
    private Button generateScriptButton;


    @FXML
    private Button deleteTableButton;

    @FXML
    private Button addRelationButton;

    Table table = new Table();

    final Database database = Database.builder().databaseName("nowaBaza").tables(new ArrayList<>()).build();

    public static void main(String [] args){
        launch();
    }

    @FXML
    void onClickGenerateScriptButton(ActionEvent event){
        Generator.generateDatabase(database);
    }

    @FXML
    void onClickAddTableButton(ActionEvent event){
        List<Field> fieldsList = new ArrayList<>();
        for(int i=0;i<3;i++){
            fieldsList.add(Field.createField(name0.getCharacters().toString(), name1.getCharacters().toString()));
        }
        database.tables.add(Table.addTable(fieldsList));

    }





    @FXML
    void onClickViewTablesButton(ActionEvent event){

        System.out.println(database.databaseName);
        System.out.println(database.tables.get(0).getTableName());
//        System.out.println(database.tables.get(0).tableName);
//        System.out.println(database.tables.get(0).fields.get(0).getFieldName());
    }

    @FXML
    void onClickViewAddTableForm(ActionEvent event){
        name2.setVisible(true);
        name3.setVisible(true);
        name4.setVisible(true);
        addTableButton.setVisible(true);
        generateScriptButton.setVisible(true);
        deleteTableButton.setVisible(true);
        addRelationButton.setVisible(false);
    }

    @FXML
    void onClickViewAddRelationForm(ActionEvent event) throws NoSuchFieldException, IllegalAccessException {
        name2.setVisible(false);
        name3.setVisible(false);
        name4.setVisible(false);
        addTableButton.setVisible(false);
        generateScriptButton.setVisible(false);
        deleteTableButton.setVisible(false);
        addRelationButton.setVisible(true);
    }

    public void onStart(){
        ObservableList<String> dataTypes = FXCollections.observableArrayList("INTEGER","VARCHAR(255)","DECIMAL","DATE");
        type0.setItems(dataTypes);
        type1.setItems(dataTypes);
        type2.setItems(dataTypes);
        type3.setItems(dataTypes);
        type4.setItems(dataTypes);
        type5.setItems(dataTypes);
        type6.setItems(dataTypes);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        stage.setTitle("MainApp");
        stage.setScene(new Scene(root, 1280,720));
        stage.show();
    }
}