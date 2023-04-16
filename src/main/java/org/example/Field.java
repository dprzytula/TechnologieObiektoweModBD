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
    Parameters parameters;

}
