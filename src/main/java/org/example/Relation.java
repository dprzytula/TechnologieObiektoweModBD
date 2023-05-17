package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relation {
    String mainTableId;
    String foreignTableId;

    public static Relation addRelation(String mainTableId, String foreignTableId){
        return Relation.builder().mainTableId(mainTableId).foreignTableId(foreignTableId).build();
    }

}
