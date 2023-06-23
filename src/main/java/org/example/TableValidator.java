package org.example;

import java.util.List;

public class TableValidator {

    public static Boolean validateTableToAdd(List<Field> fieldsToCheck){
        for (Field field : fieldsToCheck) {
            if(field.getFieldName()=="" || field.getFieldType()==null) return false;
        }
        return true;
    }

}
