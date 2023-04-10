package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Table{
    String tableName;
    List<Field> fields = new ArrayList<>();

    public static Table addTable(String tableName, List<Field> fieldsList){
        //field = Field.builder().fieldName(id.getCharacters().toString()).dataType("integer").build();

        //fields.add(field);
        return Table.builder().tableName(tableName).fields(fieldsList).build();
    }

}
