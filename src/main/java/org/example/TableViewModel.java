package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class TableViewModel {
    public CheckBox getNewRow() {
        return newRow;
    }

    private final CheckBox newRow;
    private final SimpleStringProperty fieldName;

    public String getFieldName() {
        return fieldName.get();
    }

    public SimpleStringProperty fieldNameProperty() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName.set(fieldName);
    }

    public ComboBox getFieldType() {
        return fieldType;
    }

    public String getFieldSize() {
        return fieldSize.get();
    }

    public SimpleStringProperty fieldSizeProperty() {
        return fieldSize;
    }

    public void setFieldSize(String fieldSize) {
        this.fieldSize.set(fieldSize);
    }

    private final ComboBox fieldType;

    private final SimpleStringProperty fieldSize;

    public TableViewModel(String fieldName, ObservableList data, String fieldSize) {
        this.newRow = new CheckBox();
        this.fieldName = new SimpleStringProperty(fieldName);
        this.fieldType = new ComboBox(data);
        this.fieldSize = new SimpleStringProperty(fieldSize);
    }


}
