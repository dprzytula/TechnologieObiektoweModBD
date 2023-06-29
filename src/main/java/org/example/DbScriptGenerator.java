package org.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.NewTableController.database;
import static org.example.NewTableController.relations;

public class DbScriptGenerator {

    public static String exportDatabaseToScript(){
        AtomicReference<String> script = new AtomicReference<>("");
        List<Table> tablesList = database.tables.stream().toList();

        tablesList.stream().forEach(table -> {
            script.set(
                    script+"create table "+table.getTableName()+"(\n"+
                    getFieldsFromTableObject(table)+");\n");
        });
        script.set(script+getOneToManyRelationsTable());
        return script.get();
    }

    public static String getFieldsFromTableObject(Table tableName){
        var fieldsList = tableName.getFields();
        AtomicReference<String> tableFieldsToScript = new AtomicReference<>("");
        for(int i=0; i<fieldsList.size()-1;i++){
            if(fieldsList.get(i).fieldType=="DATE" || fieldsList.get(i).fieldType=="INT" || fieldsList.get(i).fieldType=="BIGINT") tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(i).getFieldName()+" "+fieldsList.get(i).fieldType);
            else tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(i).getFieldName()+" "+fieldsList.get(i).fieldType+"("+fieldsList.get(i).getFieldSize()+")");
            if(fieldsList.get(i).primaryKey) tableFieldsToScript.set(tableFieldsToScript+" PRIMARY KEY,\n");
            else tableFieldsToScript.set(tableFieldsToScript+",\n");
        }
        if(fieldsList.get(fieldsList.size()-1).fieldType=="DATE" || fieldsList.get(fieldsList.size()-1).fieldType=="INT" || fieldsList.get(fieldsList.size()-1).fieldType=="BIGINT") tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(fieldsList.size()-1).getFieldName()+" "
                +fieldsList.get(fieldsList.size()-1).fieldType+"\n");
            else tableFieldsToScript.set(tableFieldsToScript+fieldsList.get(fieldsList.size()-1).getFieldName()+" "
                    +fieldsList.get(fieldsList.size()-1).fieldType+"("+fieldsList.get(fieldsList.size()-1).getFieldSize()+")\n");
        if(fieldsList.get(fieldsList.size()-1).primaryKey) tableFieldsToScript.set(tableFieldsToScript+" PRIMARY KEY\n");
        return tableFieldsToScript.get();
    }

    public static String getOneToManyRelationsTable (){
        String script = "";
        String tableOne = "";
        String tableTwo = "";
        for (Relation relation : relations) {
            for (Table table : database.tables) {
                if(table.getId().equals(relation.getFirstTableId().substring(1))) tableOne = table.getTableName();
                if(table.getId().equals(relation.getSecondTableId().substring(1))) tableTwo = script+table.getTableName();
            }
            if(relation.getRelationType()=="1:1")
            {
                script = script +
                        "ALTER TABLE "+tableOne+" ADD COLUMN "+tableTwo+"_id INTEGER UNIQUE;\n"+
                        "ALTER TABLE "+ tableOne+ " ADD CONSTRAINT fk_"+tableOne+"_"+tableTwo+" FOREIGN KEY ("+tableTwo+"_id) UNIQUE REFERENCES "+tableTwo+ "(id);\n";
            }
                if(relation.getRelationType()=="N:1") {
                    script = script +
                            "ALTER TABLE " + tableTwo + " ADD COLUMN " + tableOne + "_id INTEGER;\n" +
                            "ALTER TABLE " + tableTwo + " ADD CONSTRAINT fk_" + tableTwo + "_" + tableOne + " FOREIGN KEY (" + tableOne + "_id) REFERENCES " + tableOne + "(id);\n";
                }
                if(relation.getRelationType()=="1:N") {
                    script = script +
                            "ALTER TABLE " + tableOne + " ADD COLUMN " + tableTwo + "_id INTEGER;" +
                            "ALTER TABLE " + tableOne + " ADD CONSTRAINT fk_" + tableOne + "_" + tableTwo + " FOREIGN KEY (" + tableTwo + "_id) REFERENCES " + tableTwo + "(id);\n";
                }
                if(relation.getRelationType()=="N:N") {
                    script = script +
                            "CREATE TABLE " + tableOne + "_" + tableTwo + "(\n" + tableOne + "_id INTEGER,\n" + tableTwo + "_id INTEGER\n);\n" +
                            "ALTER TABLE " + tableOne + "_" + tableTwo + " ADD CONSTRAINT pkmm_" + tableOne + "_" + tableTwo + " PRIMARY KEY (" + tableOne + "_id, " + tableTwo + "_id);\n" +
                            "ALTER TABLE " + tableOne + "_" + tableTwo + " ADD CONSTRAINT fkmm_" + tableOne + "_" + tableTwo + " FOREIGN KEY (" + tableOne + "_id) REFERENCES " + tableTwo + "(id);\n" +
                            "ALTER TABLE " + tableOne + "_" + tableTwo + " ADD CONSTRAINT fkmm_" + tableTwo + "_" + tableOne + " FOREIGN KEY (" + tableTwo + "_id) REFERENCES " + tableOne + "(id);\n";
                }
                }
        return script;
    }


}
