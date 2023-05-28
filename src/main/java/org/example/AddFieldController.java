package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static org.example.CreateDatabaseController.options;

public class AddFieldController {

    ObservableList<String> types =
            FXCollections.observableArrayList(
                    "INTEGER",
                    "DATE_TIME",
                    "VARCHAR"
            );

    @FXML
    private TextField name;

    @FXML
    private ComboBox type;

    @FXML
    private TextField fieldSize;

    @FXML
    private CheckBox primaryKey;

    @FXML
    private CheckBox notNull;

    @FXML
    private CheckBox unique;

    @FXML
    private void initialize(){
        fieldSize.setVisible(false);
        type.setItems(types);
    }

    @FXML
    void selectDataType(){
        if(type.getSelectionModel().getSelectedItem().toString()=="VARCHAR") fieldSize.setVisible(true);
        else fieldSize.setVisible(false);
    }

    @FXML
    void onClickAddFieldButton(ActionEvent event){
        Parameters parameters = new Parameters();
        if(type.getSelectionModel().getSelectedItem().toString()=="VARCHAR"){
            if(primaryKey.isSelected()) parameters = Parameters.builder().primaryKey(primaryKey.isSelected()).notNull(false).unique(false).build();
            else parameters = Parameters.builder().primaryKey(false).notNull(notNull.isSelected()).unique(unique.isSelected()).build();
          //  fieldsList.add(Field.builder().fieldName(name.getText()).dataType(type.getSelectionModel().getSelectedItem().toString()).dataSize(fieldSize.getText()).parameters(parameters).build());
            if(options.get(options.size()-1)=="") options.add(options.size()-1,name.getText()+" "+type.getSelectionModel().getSelectedItem().toString()+" "+fieldSize.getText());
            else options.add(options.size(), name.getText()+" "+type.getSelectionModel().getSelectedItem().toString()+" "+fieldSize.getText());
        }
        else {
            if(primaryKey.isSelected()) parameters = Parameters.builder().primaryKey(primaryKey.isSelected()).notNull(false).unique(false).build();
            else parameters = Parameters.builder().primaryKey(false).notNull(notNull.isSelected()).unique(unique.isSelected()).build();
           // fieldsList.add(Field.builder().fieldName(name.getText()).dataType(type.getSelectionModel().getSelectedItem().toString()).parameters(parameters).build());
            if(options.get(options.size()-1)=="") options.add(options.size()-1, name.getText()+" "+type.getSelectionModel().getSelectedItem().toString());
            else options.add(options.size(),name.getText()+" "+type.getSelectionModel().getSelectedItem().toString());
        }
    }
}
