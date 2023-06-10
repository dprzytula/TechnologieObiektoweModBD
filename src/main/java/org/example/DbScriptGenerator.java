package org.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.NewTableController.database;

public class DbScriptGenerator {

    public static String exportDatabaseToScript(){
        AtomicReference<String> script = new AtomicReference<>("");
        List<Table> tablesList = database.tables.stream().toList();

        tablesList.stream().forEach(table -> {
            script.set(script+"create table "+table.getTableName()+"("+");");
        });

        return script.get();
    }

}
