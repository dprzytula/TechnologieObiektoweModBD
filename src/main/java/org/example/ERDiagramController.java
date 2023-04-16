package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.*;

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
        String plantUmlSource = "@startuml\n";
        plantUmlSource+="entity "+database.tables.get(0).getTableName()+"{"+"\n"+"}"+"\n";
        plantUmlSource+="entity "+database.tables.get(1).getTableName()+"{"+"\n"+"}"+"\n";
       // plantUmlSource+=""+database.tables.get(0).getTableName()+" }|--|| "+database.tables.get(1).getTableName()+"\n";
        plantUmlSource+="@enduml\n";
        return plantUmlSource;
    }

}
