/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author LeBorg
 */
public class SaxParserController implements Initializable {
    
    @FXML
    private TextArea textArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     void printNodes(Nodes nodes)
    {
        for(int i = 0; i < nodes.getNodes().size(); i++) {
            //System.out.println(nodes.getNodes().get(i).getContent() + "  " + nodes.getNodes().get(i).getIndentationCount());
            System.out.println(tabs(nodes.getNodes().get(i).getIndentationCount()) + nodes.getNodes().get(i).getContent());
        }
    }
    
     String tabs(int count) {
        String start="";
        for(int i=0; i< count; i++) {
            start += "\t";
        }
        return start;
    }
    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file != null) {
            try
            {
                Nodes nodes = NodeBuilder.load(file);
                for(int i = 0; i < nodes.getNodes().size(); i++) {
                    //System.out.println(nodes.getNodes().get(i).getContent() + "  " + nodes.getNodes().get(i).getIndentationCount());
                    textArea.appendText(tabs(nodes.getNodes().get(i).getIndentationCount()) + nodes.getNodes().get(i).getContent() + "\n");
                }
//                Course course = NodeBuilder.load(file);
//                ArrayList<Student> students = course.getStudents();
//                for (Student student : students) {
//                    textArea.appendText(Integer.toString(student.getId()) + "\n");
//                    textArea.appendText(student.getPawprint() + "\n");
//                    textArea.appendText(student.getFirstName() + "\n");
//                    textArea.appendText(student.getLastName() + "\n");
//                    textArea.appendText(Double.toString(student.getGrade()) + "\n");
//                    textArea.appendText("-------------------------\n");
//                }
                
                
            } catch (Exception ex) {
                displayExceptionAlert("Exception parsing XML file.", ex);
            }
        }
    }
    
    @FXML
    private void handleAbout(ActionEvent event) {
        displayAboutAlert();
    }
    
    private void displayAboutAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("XML DOM Parser Example");
        alert.setContentText("This application was developed by Dale Musser for CS4330 at the University of Missouri.");
        
        TextArea textArea = new TextArea("This example illustrates how to parse XML using the DOM.");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    
}
