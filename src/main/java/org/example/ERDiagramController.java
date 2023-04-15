package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ERDiagramController {

    @FXML
    private ImageView erdDiagram;

    @FXML
    void initialize() throws IOException {
            SourceStringReader reader = new SourceStringReader(generateErdDiagram());
            reader.generateImage(new FileOutputStream("src/main/resources/test.png"), new FileFormatOption(FileFormat.PNG, false));
            erdDiagram.setImage(new Image((new File("test.png")).toString()));
    }

    String generateErdDiagram(){
        StringBuilder plantUmlSource = new StringBuilder();
        plantUmlSource.append("@startuml\n");
        plantUmlSource.append("test1234 -> Bob: Authentication Request\n");
        plantUmlSource.append("Bob --> test1234: Authentication Response\n");
        plantUmlSource.append("@enduml");
        return plantUmlSource.toString();
    }

}
