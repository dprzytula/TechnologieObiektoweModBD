package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {

    public static void generateDatabase(Database databaseName){
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

}
