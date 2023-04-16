package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relation {
    String foreignTableName;
    String keyName;

    public static Relation addRelation(String tableName){
        return Relation.builder().foreignTableName(tableName).keyName(tableName+"_id").build();
    }

}
