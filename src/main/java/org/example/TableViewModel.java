package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class TableViewModel {

    public TableViewModel(ObservableList keyRow, String fieldNameRow, ObservableList fieldTypeRow, String fieldSizeRow, String checkRow) {
        this.keyRow = new ComboBox(keyRow);
        this.fieldNameRow = new SimpleStringProperty(fieldNameRow);
        this.fieldTypeRow = new ComboBox(fieldTypeRow);
        this.fieldSizeRow = new SimpleStringProperty(fieldSizeRow);
        this.uniqueRow = new CheckBox();
        this.notNullRow = new CheckBox();
        this.checkRow = new SimpleStringProperty(checkRow);
    }

    private final ComboBox keyRow;
    private final SimpleStringProperty fieldNameRow;
    private final ComboBox fieldTypeRow;
    private final SimpleStringProperty fieldSizeRow;
    private final CheckBox uniqueRow;
    private final CheckBox notNullRow;
    private final SimpleStringProperty checkRow;


    public ComboBox getKeyRow() {
        return keyRow;
    }

    public String getFieldNameRow() {
        return fieldNameRow.get();
    }

    public SimpleStringProperty fieldNameRowProperty() {
        return fieldNameRow;
    }

    public void setFieldNameRow(String fieldNameRow) {
        this.fieldNameRow.set(fieldNameRow);
    }

    public ComboBox getFieldTypeRow() {
        return fieldTypeRow;
    }

    public String getFieldSizeRow() {
        return fieldSizeRow.get();
    }

    public SimpleStringProperty fieldSizeRowProperty() {
        return fieldSizeRow;
    }

    public SimpleStringProperty checkRowProperty() {
        return checkRow;
    }

    public void setFieldSizeRow(String fieldSizeRow) {
        this.fieldSizeRow.set(fieldSizeRow);
    }

    public void setCheckRow(String checkRow) {
        this.checkRow.set(checkRow);
    }

    public CheckBox getUniqueRow() {
        return uniqueRow;
    }

    public CheckBox getNotNullRow() {
        return notNullRow;
    }

    public String getCheckRow() {
        return checkRow.get();
    }
}
