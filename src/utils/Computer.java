
package utils;
import java.io.Serializable;

public class Computer implements Serializable{
    private String LabName = "";
    private String ComputerNumber = "";
    
    public void setDetails(String labName, String computerNumber){
        LabName = labName;
        this.ComputerNumber = computerNumber;
    }
    
    public String getName(){
        return this.LabName + " [" + this.ComputerNumber +"]";
    }
}
