package rotacsufbo.view;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import rotacsufbo.ClassFlattener;
import rotacsufbo.FileExplorer;
import rotacsufbo.LayoutObfuscator;
import rotacsufbo.Obfuscator;

import java.io.*;
import java.util.ArrayList;

public class FilesLayoutController {
    private ObservableList<String> sourceFiles;


    @FXML
    private ComboBox<String> filesListComboBox;

    @FXML
    private ListView<String> filesListView;

    @FXML
    private TextArea codeTextArea;

    public void initialize(File src) {
        FileExplorer fe = new FileExplorer();
        fe.traverseFolder(src);
        ArrayList<String> shortenedFiles = new ArrayList<>();
        for (File f : fe.getFiles()) {
            shortenedFiles.add(f.toString().substring(src.toString().length()));
        }
        sourceFiles = FXCollections.observableArrayList(shortenedFiles);

        filesListComboBox.getItems().addAll(sourceFiles);

        filesListComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    BufferedReader in = new BufferedReader(new FileReader(new File(src + newValue)));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = in.readLine()) != null ) {
                        sb.append(line + "\n");
                    }

                    CompilationUnit cu = JavaParser.parse(sb.toString());

                    Obfuscator obfs = new Obfuscator(cu);
                    obfs.decommentate();
                    obfs.flatten();
                    codeTextArea.setText(obfs.getUnit().toString());


                    System.out.println(src + newValue);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



