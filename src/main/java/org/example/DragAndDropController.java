package org.example;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

public class DragAndDropController extends Application {


    static ObservableList<TableViewModel> options = FXCollections.observableArrayList(
            new TableViewModel("",FXCollections.observableArrayList("Test"),""));

    @FXML
    private Pane dragPanel;

    @FXML
    private void createTable(ActionEvent event){
        Pane root = dragPanel;
        TableView tableView = new TableView();
        tableView.setPrefSize(240, 200);
        tableView.setEditable(true);
        TableColumn tableColumn1 = new TableColumn();
        TableColumn tableColumn2 = new TableColumn("Nazwa pola");
        TableColumn tableColumn3 = new TableColumn("Typ pola");
        TableColumn tableColumn4 = new TableColumn("Rozmiar pola");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("newRow"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("fieldName"));
        tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumn3.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("fieldType"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("fieldSize"));
        tableColumn4.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumn1.setReorderable(false);
        tableColumn2.setReorderable(false);
        tableColumn3.setReorderable(false);
        tableColumn4.setReorderable(false);
        tableColumn1.setMaxWidth(22);
//        tableColumn2.setReorderable(false);
//        tableColumn3.setReorderable(false);
//        tableColumn4.setReorderable(false);
        tableView.getColumns().add(tableColumn1);
        tableView.getColumns().add(tableColumn2);
        tableView.getColumns().add(tableColumn3);
        tableView.getColumns().add(tableColumn4);
        tableView.columnResizePolicyProperty();
        tableView.setItems(options);
        tableView.setEditable(true);
        root.getChildren().add(tableView);
        makeDraggable(tableView);
    }

    @FXML
    private void addLine(ActionEvent event){
        Pane root = dragPanel;

        Line line = new Line();
        line.setStartX(130.0f);
        line.setStartY(130.0f);
        line.setEndX(30.0f);
        line.setEndY(30.0f);
        root.getChildren().add(line);
        makeDraggable(line);
    }

    private double startX;
    private double startY;

    private void makeDraggable(Node node){
        node.setOnMousePressed(e->{
            startX = e.getSceneX()-node.getTranslateX();
            startY = e.getSceneY()-node.getTranslateY();
        });

        node.setOnMouseDragged(e->{
            node.setTranslateX(e.getSceneX()-startX);
            node.setTranslateY(e.getSceneY()-startY);

        });
    }

    public static void main(String [] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DragAndDropTest.fxml"));
        stage.setTitle("MainApp");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
