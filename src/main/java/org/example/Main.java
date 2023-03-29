package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    Database database = new Database();
    Table table = new Table();
    @Override
    public void start(Stage stage) throws Exception{
        BorderPane root = new BorderPane();
        Button button = new Button("Dodaj tabele");
        Button button2 = new Button("Wyswietl tabele");
        Button button3 = new Button("Generuj skrypt");
        database = Database.builder().databaseName("nowaBaza").tables(new ArrayList<>()).build();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                database.tables.add(addTable());
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                generateScript(database);
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printTables();
            }
        });
        root.setLeft(button);
        root.setBottom(button2);
        root.setCenter(button3);
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void generateScript(Database databaseName){
                     try{
                         String fileName = "NowyPlik";
                         Table table = null;
                         Field field = null;
                         String script = null;
                         String database = databaseName.databaseName;
                         script = "create database "+database+";\n";
                         for(int i=0;i<1;i++){
                             table = databaseName.tables.get(i);
                             field = databaseName.tables.get(i).getFields().get(i);

                            script+="create table "+table.getTableName()+"(\n"+field.fieldName+" "+field.dataType+" PRIMARY KEY"+"\n);";
                         }

                         BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(script);

                    writer.close();
                }
                catch(IOException e){

                }
    }

    public void printTables(){
        System.out.println(database.databaseName);
        System.out.println(database.tables.get(0).getTableName());
        System.out.println(database.tables.get(0).tableName);
        System.out.println(database.tables.get(0).fields.get(0).getFieldName());
    }

    public Table addTable(){
        Field field = new Field();
        field = Field.builder().fieldName("id").dataType("integer").build();
        table = Table.builder().tableName("nowa").fields(new ArrayList<>()).build();
        table.fields.add(field);
        return table;
//        database = Database.builder()
//                .databaseName("nowabaza")
//                .tables(Table.builder()
//                        .tableName("nowa")
//                        .fields(Field.builder()
//                                .fieldName("id")
//                                .dataType("integer")
//                                .build())
//                        .build())
//                .build();
    }

    public static void main(String [] args){
        launch();
    }
}