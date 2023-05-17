package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.CreateDatabaseController.database;

public class ERDiagramController {

    @FXML
    private ImageView erdDiagram;

    @FXML
    void initialize() throws IOException {
        SourceStringReader reader = new SourceStringReader(generateErdDiagram());
        reader.generateImage(new FileOutputStream("src/main/resources/test.png"), new FileFormatOption(FileFormat.PNG, false));
        InputStream stream = new FileInputStream("src/main/resources/test.png");
        erdDiagram.setImage(new Image(stream));
        stream.close();
    }

    String generateErdDiagram(){
        AtomicReference<String> finalPlantUmlSource = new AtomicReference<>("@startuml\n");
        database.tables.forEach(table->{
            finalPlantUmlSource.set(finalPlantUmlSource +"entity "+table.getTableName()+"{"+"\n"+"}"+"\n");
        });
//        database.tables.forEach(table->{
//            table.getRelations().forEach(relation ->
//                    finalPlantUmlSource.set(finalPlantUmlSource +table.getTableName()+" }|--|| "+relation.getForeignTableName()+"\n")
//                    );
//        });


//        plantUmlSource+="entity "+database.tables.get(0).getTableName()+"{"+"\n"+"}"+"\n";
//        plantUmlSource+="entity "+database.tables.get(1).getTableName()+"{"+"\n"+"}"+"\n";
       // plantUmlSource+=""+database.tables.get(0).getTableName()+" }|--|| "+database.tables.get(1).getTableName()+"\n";
        finalPlantUmlSource.set(finalPlantUmlSource+"@enduml\n");
        return finalPlantUmlSource.get().toString();
    }

}
