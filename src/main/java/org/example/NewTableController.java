package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewTableController extends Application {

    Database database = new Database();

    public static List<TableView> tablesToGenerate = new ArrayList<>();

    @FXML
    private AnchorPane anchorNamePanel;

    @FXML
    private Button addTableButton;

    @FXML
    private Pane databasePanel;

    @FXML
    private TextField tableNameField;

    @FXML
    private TextField columnAmounts;

    @FXML
    private Button createTableButton;

    private double startX;
    private double startY;

    public static int idTable=0;
    public static int columnAmount= 0;

    public static String generateId(){
        String id = Integer.toString(idTable);
        idTable++;
        return id;
    }

    @FXML
    public void createTableTest(ActionEvent event){
        Stage stage = (Stage)createTableButton.getScene().getWindow();
        List<TableColumn> columns = new ArrayList<>();
        TableView newTable = new TableView();
        ObservableList<TableViewModel> options = FXCollections.observableArrayList();
        newTable.setId(generateId());
        TableColumn tableNameColumn = new TableColumn<>(tableNameField.getText());
        TableColumn keyColumn = new TableColumn<>("Key");
        TableColumn fieldNameColumn = new TableColumn<>("Field name");
        TableColumn fieldTypeColumn = new TableColumn<>("Field type");
        TableColumn fieldSizeColumn = new TableColumn<>("Field size");
        TableColumn uniqueColumn = new TableColumn<>("Unique");
        TableColumn notNullColumn = new TableColumn<>("Not null");
        TableColumn checkColumn = new TableColumn<>("Check");
        keyColumn.setMinWidth(10);
        fieldNameColumn.setMinWidth(75);
        fieldTypeColumn.setMinWidth(40);
        fieldSizeColumn.setMinWidth(20);
        uniqueColumn.setMinWidth(30);
        notNullColumn.setMinWidth(30);
        checkColumn.setMinWidth(30);
        fieldNameColumn.setEditable(true);
        fieldSizeColumn.setEditable(true);
        checkColumn.setEditable(true);
        fieldNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fieldSizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        checkColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        keyColumn.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("keyRow"));
        fieldNameColumn.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("fieldNameRow"));
        fieldTypeColumn.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("fieldTypeRow"));
        fieldSizeColumn.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("fieldSizeRow"));
        uniqueColumn.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("uniqueRow"));
        notNullColumn.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("notNullRow"));
        checkColumn.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("checkRow"));
        columns.add(keyColumn);
        columns.add(fieldNameColumn);
        columns.add(fieldTypeColumn);
        columns.add(fieldSizeColumn);
        columns.add(uniqueColumn);
        columns.add(notNullColumn);
        columns.add(checkColumn);

        columns.stream().forEach(column->{
            column.setReorderable(false);
        });


        columns.stream().forEach(column->{
            tableNameColumn.getColumns().add(column);
        });

        newTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        newTable.setPrefSize(350, 220);
        newTable.setEditable(true);
        newTable.getColumns().add(tableNameColumn);
        columnAmount = Integer.parseInt(columnAmounts.getText());

        for(int i=0;i<columnAmount;i++) options.add(new TableViewModel(FXCollections.observableArrayList("PK","FK"),"",FXCollections.observableArrayList("Test"),"",""));
        newTable.setItems(options);
        tablesToGenerate.add(newTable);
        stage.close();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    public void readScript(ActionEvent event){
        List<Node> tables = databasePanel.getChildren().stream().filter(element -> element.getTypeSelector().equals("TableView")).toList();
        List<TableView> tableViews = new ArrayList<>();
        tables.forEach((table)->{
            TableView tableView = (TableView) table;
            tableViews.add(tableView);
        });
        tableViews.forEach((table)->{
            List<Field> fields = new ArrayList<>();
            TableColumn tableView = (TableColumn) table.getColumns().get(0);
            for (int j = 0; j < table.getItems().size(); j++) {
                TableColumn keyColumn = (TableColumn) tableView.getColumns().get(0);
                TableColumn nameColumn = (TableColumn) tableView.getColumns().get(1);
                TableColumn typeColumn = (TableColumn) tableView.getColumns().get(2);
                TableColumn sizeColumn = (TableColumn) tableView.getColumns().get(3);
                TableColumn uniqueColumn = (TableColumn) tableView.getColumns().get(4);
                TableColumn notNullColumn = (TableColumn) tableView.getColumns().get(5);
                TableColumn checkColumn = (TableColumn) tableView.getColumns().get(6);
                ComboBox keyColumnValue = (ComboBox) keyColumn.getCellObservableValue(j).getValue();
                Object nameColumnValue = nameColumn.getCellObservableValue(j).getValue();
                ComboBox typeColumnValue = (ComboBox) typeColumn.getCellObservableValue(j).getValue();
                Object sizeColumnValue = sizeColumn.getCellObservableValue(j).getValue();
                CheckBox uniqueColumnValue = (CheckBox) uniqueColumn.getCellObservableValue(j).getValue();
                CheckBox notNullColumnValue = (CheckBox) notNullColumn.getCellObservableValue(j).getValue();
                Object checkColumnValue = checkColumn.getCellObservableValue(j).getValue();
                Boolean primaryKey = false;
                Boolean foreignKey = false;
                if(keyColumnValue.getSelectionModel().getSelectedItem().equals("PK")){
                    primaryKey = true;
                }
                else if(keyColumnValue.getSelectionModel().getSelectedItem().equals("FK")){
                    foreignKey = true;
                }
                else if(keyColumnValue.getSelectionModel().getSelectedItem() == null){
                    primaryKey = false;
                    foreignKey = false;
                }
                String fieldName = nameColumnValue.toString();
                String fieldType = typeColumnValue.getSelectionModel().getSelectedItem().toString();
                Integer fieldSize = Integer.valueOf(sizeColumnValue.toString());
                Boolean unique = uniqueColumnValue.isSelected();
                Boolean notNull = notNullColumnValue.isSelected();
                String checkParam = checkColumnValue.toString();
                fields.add(Field.builder().fieldName(fieldName).fieldType(fieldType).fieldSize(fieldSize).primaryKey(primaryKey)
                        .foreignKey(foreignKey).unique(unique).notNull(notNull).checkParam(checkParam).build());
            }
            database.tables.add(Table.addTable(tableView.getId(), tableView.getText(), fields));
        });
    }

    @FXML
    public void createTable(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("TableNameWindow.fxml"));
        stage.setTitle("MainApp");
        stage.setMinWidth(300);
        stage.setMinHeight(200);
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            databasePanel.getChildren().add(tablesToGenerate.get(0));
            makeDraggable(tablesToGenerate.get(0));
            tablesToGenerate.clear();
        });
        databasePanel.setOnScroll(
                new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        double zoomFactor = 1.05;
                        double deltaY = event.getDeltaY();

                        if (deltaY < 0){
                            zoomFactor = 0.95;
                        }
                        databasePanel.setScaleX(databasePanel.getScaleX() * zoomFactor);
                        databasePanel.setScaleY(databasePanel.getScaleY() * zoomFactor);
                        event.consume();
                    }
                });

    }



    private void makeDraggable(Node node){
        node.setOnMousePressed(e->{
            startX = e.getSceneX()-node.getTranslateX();
            startY = e.getSceneY()-node.getTranslateY();
        });

        node.setOnMouseDragged(e->{
            node.setTranslateX(e.getSceneX()-startX);
            node.setTranslateY(e.getSceneY()-startY);
           // drawLines();
        });
    }

    public void drawLines(){
        Pane root = databasePanel;
        Line l = new Line();
        TableView tb = (TableView) databasePanel.lookup("#0");
        TableView tb2 = (TableView) databasePanel.lookup("#1");
        l.setStartX(tb.getBoundsInParent().getMaxX());
        l.setStartY(tb.getBoundsInParent().getMaxY());
        l.setEndX(tb2.getBoundsInParent().getMinX());
        l.setEndY(tb2.getBoundsInParent().getMinY());
        List<Node> lines = databasePanel.getChildren().stream().filter(element -> element.getTypeSelector().equals("Line")).toList();
        lines.stream().forEach(node -> databasePanel.getChildren().remove(node));
        root.getChildren().add(l);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("NewTableScene.fxml"));
        stage.setTitle("MainApp");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setScene(new Scene(root));
        stage.show();
    }



}
