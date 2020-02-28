/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.sql.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.regex.*;
import javafx.scene.text.Text;

// Import database connector 
import utils.DataTasks;
/**
 *
 * @author aman9
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private ComboBox subjects;
    @FXML
    private TextField EnrollmentNumber;
    @FXML
    private Text Error;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void doLogin(ActionEvent evt) throws SQLException{
        System.out.println("Button Click Detected");
        subjects.setDisable(false);
        
        
    }
    @FXML
    public void setSubjects(KeyEvent evt){
        if(EnrollmentNumber.getText().length() >= 12){
            Pattern pattern = Pattern.compile("[02060208]+[a-zA-z]{2}\\d{6}");
            Matcher m = pattern.matcher(EnrollmentNumber.getText());
            if(m.matches()){
                subjects.setDisable(false);
                DataTasks dataTasks = new DataTasks();
                Map<String,Object> subjectData = dataTasks.getSubjects(EnrollmentNumber.getText());
                if((boolean)subjectData.get("error") == false){
                    ArrayList list = (ArrayList) subjectData.get("subjects");
                    ObservableList<String> items = FXCollections.observableArrayList(list);
                    subjects.setItems(items);
                }
                else{
                    subjects.setDisable(true);
                    subjects.getSelectionModel().clearSelection();
                    Error.setText(subjectData.get("message").toString());
                }
            }
        }
        // If Enrollment Number Is Less Then 12 Characters Disable Field
        else{
            subjects.setDisable(true);
            subjects.getSelectionModel().clearSelection();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}