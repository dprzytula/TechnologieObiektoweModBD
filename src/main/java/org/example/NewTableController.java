package org.example;

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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class NewTableController {

    public static Database database = new Database();

    public static List<Relation> relations = new ArrayList<>();

    private static List<Table> emptyTables = new ArrayList<>();

    public static List<TableView> tablesToGenerate = new ArrayList<>();

    @FXML
    private Button addTableButton;

    @FXML
    private Button databaseCreatorButton;

    @FXML
    private Button addRelationViewButton;

    @FXML
    private Button closeTableWindow;

    @FXML
    private Pane databasePanel;

    @FXML
    private TextField tableNameField;

    @FXML
    private TextField columnAmounts;

    @FXML
    private Button createTableButton;

    @FXML
    private Button closeInfoWindowButton;

    private static ObservableList<String> keyTypes = FXCollections.observableArrayList("PK");
    private static ObservableList<String> dataTypes = FXCollections.observableArrayList("INT","BIGINT", "VARCHAR","DATE");

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

        for(int i=0;i<columnAmount;i++) options.add(new TableViewModel(keyTypes,"",dataTypes,"",""));
        newTable.setItems(options);
        tablesToGenerate.add(newTable);
        stage.close();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    public void addRelationView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddRelationScene.fxml"));
        stage.setScene(new Scene(root, 880,520));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addRelationViewButton.getScene().getWindow());
        stage.show();
    }

    @FXML
    public void openDatabaseCreator(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DbConnectionView.fxml"));
        stage.setScene(new Scene(root, 880,520));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(databaseCreatorButton.getScene().getWindow());
        stage.show();
    }

    @FXML
    public void readScript(ActionEvent event) throws IOException {
        TableColumn tableColumnView = null, keyColumn = null, nameColumn = null, typeColumn = null,
                sizeColumn = null, uniqueColumn = null, notNullColumn = null, checkColumn = null;
        ComboBox keyColumnValue = null, typeColumnValue = null;
        Object nameColumnValue = null, sizeColumnValue = null, checkColumnValue = null;
        CheckBox uniqueColumnValue = null, notNullColumnValue = null;
        String fieldName = null, fieldSize = null, fieldType = null, checkParam = null;
        Boolean primaryKey = null, unique = null, notNull = null;

        List<Field> fields = new ArrayList<>();
        List<TableView> tableViews = new ArrayList<>();

        database.tables.clear();
        database.tables = emptyTables;

        List<Node> tables = databasePanel.getChildren().stream().filter(element -> element.getTypeSelector().equals("TableView")).toList();

        for (Node table : tables) {
            TableView tableView = (TableView) table;
            tableViews.add(tableView);
        }

        for (TableView tableView : tableViews) {
            fieldName = null; fieldSize = "0"; fieldType = null; checkParam = null;
            primaryKey = false; unique = null; notNull = null;
            fields.clear();
            tableColumnView = (TableColumn) tableView.getColumns().get(0);
            keyColumn = (TableColumn) tableColumnView.getColumns().get(0);
            nameColumn = (TableColumn) tableColumnView.getColumns().get(1);
            typeColumn = (TableColumn) tableColumnView.getColumns().get(2);
            sizeColumn = (TableColumn) tableColumnView.getColumns().get(3);
            uniqueColumn = (TableColumn) tableColumnView.getColumns().get(4);
            notNullColumn = (TableColumn) tableColumnView.getColumns().get(5);
            checkColumn = (TableColumn) tableColumnView.getColumns().get(6);
            for (int j = 0; j < tableView.getItems().size(); j++) {
                keyColumnValue = (ComboBox) keyColumn.getCellObservableValue(j).getValue();
                nameColumnValue = nameColumn.getCellObservableValue(j).getValue();
                typeColumnValue = (ComboBox) typeColumn.getCellObservableValue(j).getValue();
                sizeColumnValue = sizeColumn.getCellObservableValue(j).getValue();
                uniqueColumnValue = (CheckBox) uniqueColumn.getCellObservableValue(j).getValue();
                notNullColumnValue = (CheckBox) notNullColumn.getCellObservableValue(j).getValue();
                checkColumnValue = checkColumn.getCellObservableValue(j).getValue();

                if(keyColumnValue.equals("PK")) primaryKey = true;
                if(nameColumnValue.toString()!=null) fieldName = nameColumnValue.toString();
                if(typeColumnValue.getSelectionModel().getSelectedItem()!=null) fieldType = typeColumnValue.getSelectionModel().getSelectedItem().toString();
                if(uniqueColumnValue.toString()!=null) unique = uniqueColumnValue.isSelected();
                if(notNullColumnValue.toString()!=null) notNull = notNullColumnValue.isSelected();
                if(checkColumnValue.toString()!=null) checkParam = checkColumnValue.toString();
                if(!sizeColumnValue.equals("")) fieldSize = sizeColumnValue.toString();

                fields.add(Field.builder()
                        .fieldName(String.valueOf(fieldName))
                        .fieldType(String.valueOf(fieldType))
                        .fieldSize(Integer.valueOf(fieldSize))
                        .primaryKey(Boolean.valueOf(String.valueOf(primaryKey)))
                        .unique(Boolean.valueOf(String.valueOf(unique)))
                        .notNull(Boolean.valueOf(String.valueOf(notNull)))
                        .checkParam(String.valueOf(checkParam)).build());
            }
                if(TableValidator.validateTableToAdd(fields)) database.tables.add(Table.addTable(tableView.getId(), tableColumnView.getText(), fields));
                else {
                    Stage stageError = new Stage();
                    stageError.setTitle("Error");
                    Label label = new Label("Tabela "+tableColumnView.getText()+" nie została zapisana!");
                    Button button = new Button("Rozumiem");
                    button.setId("closeInfoWindowButton");
                    label.setLayoutY(20);
                    label.setLayoutX(24);
                    button.setOnAction(event1 -> {
                        stageError.close();
                    });
                    button.setLayoutX(75);
                    button.setLayoutY(50);
                    Pane layout = new Pane();
                    layout.setMinSize(180, 100);
                    layout.getChildren().add(label);
                    layout.getChildren().add(button);
                    Scene scene = new Scene(layout, 210, 100);
                    stageError.setScene(scene);
                    stageError.show();

                }

        }

        Stage stageInfo = new Stage();
        Parent rootInfo = FXMLLoader.load(getClass().getClassLoader().getResource("ScriptSavedScene.fxml"));
        stageInfo.setScene(new Scene(rootInfo));
        stageInfo.setTitle("Info");
        stageInfo.show();

    }

    @FXML
    public void createTable(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("TableNameWindow.fxml"));
        stage.setTitle("MainApp");
        stage.setMinWidth(300);
        stage.setMinHeight(200);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addTableButton.getScene().getWindow());
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
            clearPanel();
            drawLine();
        });

        node.setOnMouseDragged(e->{
            node.setTranslateX(e.getSceneX()-startX);
            node.setTranslateY(e.getSceneY()-startY);
            clearPanel();
            drawLine();
        });

        node.setOnMouseReleased(e->{
            drawLine();
        });

    }


    @FXML
    public void closeAddTableView(ActionEvent action){
        Stage stage = (Stage)closeTableWindow.getScene().getWindow();
        stage.close();
    }


    public void clearPanel(){
        databasePanel.getChildren().stream().filter(element -> element.getTypeSelector().equals("Line")).forEach(line-> databasePanel.getChildren().remove(line));
    }


    public void drawLine(){
        AtomicReference<TableView> tb = new AtomicReference<>(new TableView());
        AtomicReference<TableView> tb2 = new AtomicReference<>(new TableView());
        relations.forEach(relation -> {
            Line l = new Line();
            tb.set((TableView) databasePanel.lookup(relation.firstTableId));
            tb2.set((TableView) databasePanel.lookup(relation.secondTableId));
            l.setStartX(tb.get().getBoundsInParent().getMaxX());
            l.setStartY(tb.get().getBoundsInParent().getMaxY());
            l.setEndX(tb2.get().getBoundsInParent().getMinX());
            l.setEndY(tb2.get().getBoundsInParent().getMinY());
            if(relation.getRelationType()=="N:N"){
                l.setStrokeWidth(5);
            }
            databasePanel.getChildren().add(l);
        });
    }

    @FXML
    public void drawLines(ActionEvent event){
            AtomicReference<TableView> tb = new AtomicReference<>(new TableView());
            AtomicReference<TableView> tb2 = new AtomicReference<>(new TableView());
            relations.forEach(relation -> {
                Line l = new Line();
                tb.set((TableView) databasePanel.lookup(relation.firstTableId));
                tb2.set((TableView) databasePanel.lookup(relation.secondTableId));
                l.setStartX(tb.get().getBoundsInParent().getMaxX());
                l.setStartY(tb.get().getBoundsInParent().getMaxY());
                l.setEndX(tb2.get().getBoundsInParent().getMinX());
                l.setEndY(tb2.get().getBoundsInParent().getMinY());
                if(relation.getRelationType().equals("N:N")){
                    l.setStrokeWidth(10);
                }
                databasePanel.getChildren().add(l);
            });
    }


    @FXML
    public void closeInfoWindow(ActionEvent action){
        Stage stage = (Stage)closeInfoWindowButton.getScene().getWindow();
        stage.close();
    }
}
