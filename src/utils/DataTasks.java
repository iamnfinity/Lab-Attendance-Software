package utils;

// Import Java Sql Package 
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * File Will perform read and write operations in the data base
 * @author aman9
 */
public class DataTasks {
    
    private final String DBIPADDRESS = "localhost";
    private final String DBNAME = "attendance_db";
    
    // Credentials 
    private final String DBUSER = "root";
    private final String DBPASS = "";
    // Credentials End
    
    private Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://"+DBIPADDRESS+":3306/"+DBNAME,DBUSER,DBPASS);  
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Some Error Occured During Connection");
        }
        return conn;
    }
    
    
    /**
     * Will return subjects according to Roll Number 
     * @param RollNumber
     * @return Subject List 
     */
    public Map<String,Object> getSubjects(String RollNumber){
        ResultSet rs = null;
        Map<String, Object> subjectData = new HashMap<>();
        subjectData.put("error", true);
        subjectData.put("message", "Looks Like You Are Not Registered With Us\n Register First!");
        subjectData.put("subjects", "");
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://"+DBIPADDRESS+":3306/"+DBNAME,DBUSER,DBPASS);
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT BranchId, SemesterId FROM bga4_users WHERE RollNo='"+RollNumber+"';"); 
            if(rs.next()){
                int BranchId = rs.getInt(1);
                int SemesterId = rs.getInt(2);
                rs = stmt.executeQuery("SELECT Name FROM bga4_subjects WHERE BranchId="+BranchId+" AND SemId="+SemesterId+";");
                ArrayList<String> Subjects = new ArrayList<>();
                while (rs.next()) {                    
                    Subjects.add(rs.getString(1));
                }
                subjectData.replace("subjects", Subjects);
                subjectData.replace("error", false);
            }
            else{
                System.out.println("No Data For Roll Number");
            }
            
            con.close();      
        }
        catch(Exception e){
            System.out.println("There is Some Result Fetching Data!");
            System.out.println(e.toString());
        }
        return  subjectData;
    }
}
