package org.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.NewTableController.database;

public class DbScriptGenerator {

    public static String exportDatabaseToScript(){
        AtomicReference<String> script = new AtomicReference<>("");
        List<Table> tablesList = database.tables.stream().toList();

        tablesList.stream().forEach(table -> {
            script.set(
                    script+"create table "+table.getTableName()+"(\n"+
                    getFieldsFromTableObject(table)+");\n");
        });

        return script.get();
    }

    public static String getFieldsFromTableObject(Table tableName){
        var fieldsList = tableName.getFields();
        AtomicReference<String> tableFieldsToScript = new AtomicReference<>("");
        for(int i=0; i<fieldsList.size()-1;i++){
            if(fieldsList.get(i).fieldType=="DATE" || fieldsList.get(i).fieldType=="INT" || fieldsList.get(i).fieldType=="BIGINT") tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(i).getFieldName()+" "+fieldsList.get(i).fieldType+",\n");
            else tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(i).getFieldName()+" "+fieldsList.get(i).fieldType+"("+fieldsList.get(i).getFieldSize()+"),\n");
        }
        if(fieldsList.get(fieldsList.size()-1).fieldType=="DATE" || fieldsList.get(fieldsList.size()-1).fieldType=="INT" || fieldsList.get(fieldsList.size()-1).fieldType=="BIGINT") tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(fieldsList.size()-1).getFieldName()+" "
                +fieldsList.get(fieldsList.size()-1).fieldType+"\n");
            else tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(fieldsList.size()-1).getFieldName()+" "
                    +fieldsList.get(fieldsList.size()-1).fieldType+"("+fieldsList.get(fieldsList.size()-1).getFieldSize()+")\n");
        return tableFieldsToScript.get();
    }


}
