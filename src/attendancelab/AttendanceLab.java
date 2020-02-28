package attendancelab;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utils.Computer;

/**
 *
 * @author aman9
 * Main handler class and application starting 
 * point and views are decided based on some functions
 */
public class AttendanceLab extends Application {
    
    /**
     * Function to check if data file exists or not
     * @return boolean : If File Exists 
     */
    private boolean checkIfFileExists(){
        boolean fileExist;
        try{
            FileInputStream is = new FileInputStream("Computer.bga4");
            ObjectInputStream ois = new ObjectInputStream(is);
            Computer computer = (Computer) ois.readObject();
            fileExist = true;
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println(e);
            fileExist = false;
        }
        return fileExist;
    }
    
    
    // Main Overrided function to start the application
    @Override
    public void start(Stage stage) throws Exception {
        
        // Root View will render if no view is change
        String view = "/views/AddNewComputer.fxml";
        
        // If data file exist change view to Login View
        if(checkIfFileExists()){
            view = "/views/Login.fxml";
        }
        
        // Supply FXML File accoringly accoring to the view to render 
        Parent root = FXMLLoader.load(getClass().getResource(view));
        
        Scene scene = new Scene(root);
        
        // Set Full Screen Parameters for full screen applction
        stage.setAlwaysOnTop(true);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setFullScreenExitHint("Please Log In....");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setOnCloseRequest(event->{
            event.consume();
        });
        // Full Screen Parameters End
        stage.onHidingProperty();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
    
}
