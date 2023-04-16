package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {

    public static void generateDatabase(Database databaseName) throws IOException {
            String fileName = "NowyPlik";
            Table table;
            Field field;
            String script;
            int lastId;
            String database = databaseName.databaseName;
            script = "create database "+database+";\n";
            for(int i=0;i<databaseName.tables.size();i++){
                table = databaseName.tables.get(i);
                script+="create table "+table.getTableName()+"(\n"+"id "+" "+"INTEGER "+" PRIMARY KEY,"+"\n";
                for(int j=0;j<databaseName.tables.get(i).fields.size()-1;j++)
                {
                    field = table.getFields().get(j);
                    if(field.getDataType()=="VARCHAR"){
                        script+= field.getFieldName()+" "+field.dataType+"("+field.getDataSize()+")"+",\n";
                    }
                    else script+= field.getFieldName()+" "+field.dataType+",\n";
                }
                for(int k=0;k<databaseName.tables.get(i).getRelations().size();k++){
                    script+= "CONSTRAINT fk_"+databaseName.tables.get(i).getRelations().get(k).getForeignTableName()
                            +" FOREIGN KEY("+databaseName.tables.get(i).getRelations().get(k).getKeyName()+" REFERENCES "+databaseName.tables.get(i).getRelations().get(k).getForeignTableName()
                            +"("+databaseName.tables.get(i).getRelations().get(k).getKeyName()+"),\n";
                }
                lastId = databaseName.tables.get(i).fields.size()-1;
                if(databaseName.tables.get(i).getFields().get(lastId).getDataType()=="VARCHAR"){
                    script+= databaseName.tables.get(i).fields.get(lastId).getFieldName()+" "+databaseName.tables.get(i).fields.get(lastId).getDataType()+"("+databaseName.tables.get(i).fields.get(lastId).getDataSize()+")"+"\n);";
                }
                else script+= databaseName.tables.get(i).fields.get(lastId).getFieldName()+" "+databaseName.tables.get(i).fields.get(lastId).getDataType()+"\n);\n\n";
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(script);

            writer.close();
    }

}
