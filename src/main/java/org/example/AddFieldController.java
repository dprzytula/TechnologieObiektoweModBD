package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static org.example.AddTableSceneController.fieldsList;

public class AddFieldController {

    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Option 1",
                    "Option 2",
                    "Option 3"
            );

    @FXML
    private TextField name;

    @FXML
    private ComboBox type;

    @FXML
    private void initialize(){
        type.setItems(options);
    }
    @FXML
    void onClickAddFieldButton(ActionEvent event){
        fieldsList.add(Field.builder().fieldName(name.getText()).dataType(type.getSelectionModel().getSelectedItem().toString()).build());
    }
}
