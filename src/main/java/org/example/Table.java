package org.example;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Table{
    String id;
    String tableName;
    List<Field> fields = new ArrayList<>();
    List<Relation> relations = new ArrayList<>();

    public static Table addTable(String id, String tableName, List<Field> fieldsList){
        return Table.builder().id(id).tableName(tableName).fields(fieldsList).relations(new ArrayList<>()).build();
    }

    public void addRelation(Relation relation){
        this.relations.add(relation);
    }

}
