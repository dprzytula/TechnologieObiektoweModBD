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
            int lastId;
            String database = databaseName.databaseName;
            script = "create database "+database+";\n";
            for(int i=0;i<1;i++){
                table = databaseName.tables.get(i);
                script+="create table "+table.getTableName()+"(\n"+"id "+" "+"INTEGER "+" PRIMARY KEY,"+"\n";
                for(int j=0;j<databaseName.tables.get(i).fields.size()-1;j++)
                {
                    field = databaseName.tables.get(i).getFields().get(j);
                    script+= field.getFieldName()+" "+field.dataType+",\n";
                }
                lastId = databaseName.tables.get(i).fields.size()-1;
                script+= databaseName.tables.get(i).fields.get(lastId).getFieldName()+" "+databaseName.tables.get(i).fields.get(lastId).getDataType()+"\n);";
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(script);

            writer.close();
        }
        catch(IOException e){

        }
    }

}
