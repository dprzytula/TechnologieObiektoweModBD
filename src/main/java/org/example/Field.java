package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {

    String fieldName;
    String dataType;
    String dataSize;

    public static Field createField(String fieldName, String dataType){
        return Field.builder().fieldName(fieldName).dataType(dataType).build();
    }

}
