package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.CreateDatabaseController.database;
import static org.example.CreateDatabaseController.options;

public class DragAndDropController extends Application {


    static ObservableList<TableViewModel> options = FXCollections.observableArrayList(
            new TableViewModel("",FXCollections.observableArrayList("Test"),""));

    @FXML
    private Pane elementsPanel;

    @FXML
    private Button addTableButtonMenu;

    public static List<Node> tables = new ArrayList<>();

    private int idTable=0;

    Relation relation = new Relation();

    public String generateId(int idTable){
        String id = Integer.toString(idTable);
        return id;
    }

    @FXML
    private void createTable(ActionEvent event) throws IOException {
        Pane root = elementsPanel;
        TableView tableView = new TableView();
        tableView.setPrefSize(300, 200);
        tableView.setEditable(true);
        tableView.setId(generateId(idTable));
        idTable+=1;
        TableColumn tableColumnName = new TableColumn("Nazwa tabelku");
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
        tableColumnName.getColumns().add(tableColumn1);
        tableColumnName.getColumns().add(tableColumn2);
        tableColumnName.getColumns().add(tableColumn3);
        tableColumnName.getColumns().add(tableColumn4);
        tableView.getColumns().add(tableColumnName);
        tableView.columnResizePolicyProperty();
        tableView.setItems(options);
        tableView.setEditable(true);
        root.getChildren().add(tableView);
        makeDraggable(tableView);
    }

    @FXML
    public void addRelation(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent scene = FXMLLoader.load(getClass().getClassLoader().getResource("AddRelationScene.fxml"));
        stage.setScene(new Scene(scene, 880,520));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addTableButtonMenu.getScene().getWindow());
        stage.show();
    }

    public void drawLines(){
        Pane root = elementsPanel;
        Line l = new Line();
        TableView tb = (TableView) elementsPanel.lookup("#0");
        TableView tb2 = (TableView) elementsPanel.lookup("#1");
        l.setStartX(tb.getBoundsInParent().getMaxX());
        l.setStartY(tb.getBoundsInParent().getMaxY());
        l.setEndX(tb2.getBoundsInParent().getMinX());
        l.setEndY(tb2.getBoundsInParent().getMinY());
        List<Node> lines = elementsPanel.getChildren().stream().filter(element -> element.getTypeSelector().equals("Line")).toList();
        lines.stream().forEach(node -> elementsPanel.getChildren().remove(node));
        root.getChildren().add(l);
    }

    @FXML
    public void generateScript(ActionEvent event) throws IOException {

        List<Node> tables = elementsPanel.getChildren().stream().filter(element -> element.getTypeSelector().equals("TableView")).toList();

        System.out.println(tables.get(0).getTypeSelector());
        tables.stream().forEach(node -> System.out.println(node));

        Generator.generateDatabase(database);
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
            drawLines();
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
