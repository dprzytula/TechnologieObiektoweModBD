package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {
    String fieldName;
    Boolean primaryKey;
    Boolean unique;
    Boolean notNull;
    String fieldType;
    Integer fieldSize;
    String checkParam;
}
