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

    String tableName;
    List<Field> fields = new ArrayList<>();

}
