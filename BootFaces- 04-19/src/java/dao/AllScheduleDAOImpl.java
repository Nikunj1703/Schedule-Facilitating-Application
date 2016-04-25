/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AllScheduleBean;
import bean.CourseBean;
import bean.ScheduleBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author my -laptop
 */
public class AllScheduleDAOImpl implements AllScheduleDAO{
    
    private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";
    
    @Override
    public int findAllScheduleIDs(String term,String year){
        ArrayList allCourses = new ArrayList();
        int count = 0;
        String scheduleName = term + "_" + year;
        String query = "SELECT COUNT(*) FROM SFADMIN.ALL_SCHEDULE WHERE SCHEDULE_NAME LIKE '%" + scheduleName +"%'";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
             count = rs.getInt(1);   
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllScheduleIDs()");
            System.err.println(e.getMessage());
        }
        return count;
    }
    
    @Override
    public AllScheduleBean addNewAllScheduleID(AllScheduleBean aScheduleBean, String term,String year){
        int count = findAllScheduleIDs(term, year);
        String scheduleName = term+"_"+year+"_"+(count+1);
        String description = "This schedule is for " + term + " " + year +". This is version " + (count+1);
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String addAllScheduleQuery = "INSERT INTO SFADMIN.ALL_SCHEDULE "
                    + "(SCHEDULE_NAME, DESCRIPTION, CREATED_TS, LAST_UPDT_TS) VALUES ('"
                    + scheduleName
                    + "', '" + description
                    + "', CURRENT TIMESTAMP, CURRENT TIMESTAMP"
                    + ")";
            rowCount = stmt.executeUpdate(addAllScheduleQuery);
            System.out.println("addString =" + addAllScheduleQuery);
            System.out.println(rowCount + "row updated");
            
            System.out.println(rowCount + "Schedule entry added");
            int allSchId = findAllScheduleId();
            aScheduleBean.setAllScheduleId(allSchId);//Adding just to be on safer side
            aScheduleBean.setAll_Sch_Id(allSchId);
            aScheduleBean.setScheduleDescription(description);
            aScheduleBean.setScheduleName(scheduleName);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewAllSchedule()");
            System.err.println(e.getMessage());
        }
        
        
        return aScheduleBean;
    }
    
    @Override
    public int findAllScheduleId(){
        int allSchId = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String query = "SELECT MAX(ALL_SCH_ID) FROM SFADMIN.ALL_SCHEDULE ";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                allSchId = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewAllSchedule()");
            System.err.println(e.getMessage());
        }
        
        
        
        return allSchId;
    }
    
    @Override
    public ArrayList<String> findScheduleVersionsFromDB(String term_Year){
        ArrayList<String> allScheduleVersionsList = new ArrayList<String>();
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String query = "SELECT SCHEDULE_NAME FROM SFADMIN.ALL_SCHEDULE WHERE SCHEDULE_NAME LIKE '"+term_Year+"%'";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                allScheduleVersionsList.add(rs.getString("SCHEDULE_NAME"));
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewAllSchedule()");
            System.err.println(e.getMessage());
        }
        return allScheduleVersionsList;
    }
    
    
    @Override
    public int findAllScheduleIDBySchVersionNameFromDB(String scheduleName){
       int allSchID = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String query = "SELECT ALL_SCH_ID FROM SFADMIN.ALL_SCHEDULE WHERE SCHEDULE_NAME = '"+scheduleName+"'";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                allSchID = Integer.parseInt(rs.getString("ALL_SCH_ID"));
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewAllSchedule()");
            System.err.println(e.getMessage());
        }
        return allSchID;
    }
    
    
    
}
