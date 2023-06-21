package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relation {
    String firstTableId;
    String secondTableId;
    String relationType;

    public static Relation addRelation(String mainTableId, String foreignTableId, String relationType){
        return Relation.builder().firstTableId(mainTableId).secondTableId(foreignTableId).relationType(relationType).build();
    }

}
