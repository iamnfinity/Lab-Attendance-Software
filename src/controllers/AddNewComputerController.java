/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Computer;
/**
 * FXML Controller class
 *
 * @author aman9
 */
public class AddNewComputerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Text errorContainer;
    @FXML
    private TextField LabName;
    @FXML
    private TextField ComputerName;
    
    @FXML
    private void register(ActionEvent evt){
       if("".equals(LabName.getText()) || "".equals(ComputerName.getText())){
           errorContainer.setText("Computer or Lab Name is Empty");
       }
       else{
           Computer computer = new Computer();
           computer.setDetails(LabName.getText(), ComputerName.getText());
           try{
               FileOutputStream fos = new FileOutputStream("Computer.bga4");
	       ObjectOutputStream oos = new ObjectOutputStream(fos);
	       oos.writeObject(computer);
	       errorContainer.setText("Registration Successful!");
	       oos.close();
               Stage s = (Stage) errorContainer.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
               Scene scene = new Scene(root);
               s.setScene(scene);
               
           }
           catch(Exception e){
               System.out.println(e);
           }
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
