package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static org.example.AddTableSceneController.fieldsList;
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
        if(type.getSelectionModel().getSelectedItem().toString()=="VARCHAR"){
            fieldsList.add(Field.builder().fieldName(name.getText()).dataType(type.getSelectionModel().getSelectedItem().toString()).dataSize(fieldSize.getText()).build());
            if(options.get(options.size()-1)=="") options.add(options.size()-1,name.getText()+" "+type.getSelectionModel().getSelectedItem().toString()+" "+fieldSize.getText());
            else options.add(options.size(), name.getText()+" "+type.getSelectionModel().getSelectedItem().toString()+" "+fieldSize.getText());
        }
        else {
            fieldsList.add(Field.builder().fieldName(name.getText()).dataType(type.getSelectionModel().getSelectedItem().toString()).build());
            if(options.get(options.size()-1)=="") options.add(options.size()-1, name.getText()+" "+type.getSelectionModel().getSelectedItem().toString());
            else options.add(options.size(),name.getText()+" "+type.getSelectionModel().getSelectedItem().toString());
        }
    }
}
