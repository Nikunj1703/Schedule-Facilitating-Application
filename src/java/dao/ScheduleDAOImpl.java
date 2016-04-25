/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AllScheduleBean;
import bean.CourseBean;
import bean.ScheduleBean;
import bean.ToDoBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Gunjan
 */
public class ScheduleDAOImpl implements ScheduleDAO {

//    private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/msabu_Sp16_sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    @Override
    public ArrayList<ScheduleBean> getScheduleForReview(ToDoBean toDoBean) {
    System.out.println("In schdl DAOimpl");
        ArrayList<ScheduleBean> scheduleRows = new ArrayList<ScheduleBean>();
        String getScheduleQuery = "SELECT COURSE.COURSE_CODE,COURSE_SECTION.SECTION_NUMBER,PROFESSOR.PROF_NAME,DAYS,CLASS_TIME,ROOM.ROOM_NAME "
                + "FROM SFADMIN.COURSE_SECTION, SFADMIN.COURSE, SFADMIN.PROFESSOR, SFADMIN.ROOM, SFADMIN.SCHEDULE, SFADMIN.ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
        //        + "AND ALL_SCHEDULE.ALL_SCH_ID = 1";
        + "AND ALL_SCHEDULE.ALL_SCH_ID =" + toDoBean.getAllScheduleId() ;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(getScheduleQuery);
          while(rs.next()){
                ScheduleBean scheduleBean = new ScheduleBean();
                scheduleBean.setCourseCode(rs.getString(1));
                 scheduleBean.setCourseSection(rs.getString(2));
                  scheduleBean.setProfessor(rs.getString(3));
                   scheduleBean.setDays(rs.getString(4));
                    scheduleBean.setClassTime(rs.getString(5));
                     scheduleBean.setRooms(rs.getString(6));
                     scheduleRows.add(scheduleBean);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllScheduleIDs()");
            System.err.println(e.getMessage());
        }
        return scheduleRows;
    }
    
     @Override
    public ArrayList getCourseSections(int allScheduleID){
        ArrayList<ScheduleBean> courseSectionList = new ArrayList<ScheduleBean>();
        String query = "SELECT * FROM SFADMIN.COURSE_SECTION WHERE ALL_SCH_ID = "+allScheduleID;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              ScheduleBean sBean = new ScheduleBean();
              sBean.setAllScheduleId(allScheduleID);
              String courseCode = findCourseCode(Integer.parseInt(rs.getString("Course_ID")));
              sBean.setCourseCode(courseCode);
              //String sectionNumber = findSectionNumber(Integer.parseInt(rs.getString("SECTION_ID")));
              sBean.setSectionId(Integer.parseInt(rs.getString("SECTION_ID")));
              int profId = findProfessorId(Integer.parseInt(rs.getString("SECTION_ID")), allScheduleID);
              sBean.setProfId(profId);
              int roomId = findRoomId(Integer.parseInt(rs.getString("SECTION_ID")), allScheduleID);
              sBean.setRoomId(roomId);
              
              String day = findDays(Integer.parseInt(rs.getString("SECTION_ID")), allScheduleID);
              sBean.setDays(day);
              
              int scheduleId = findScheduleId(Integer.parseInt(rs.getString("SECTION_ID")), allScheduleID);
              sBean.setScheduleId(scheduleId);
              
              String classTime = findClassTime(Integer.parseInt(rs.getString("SECTION_ID")), allScheduleID);
              if(classTime.length() != 0){
                int fromHH = 0;
                String fromMM = "";
               

                int toHH = 0;
                String toMM = "";
                String toAMPM = "";
              
              //1:00 PM - 1:00 PM
                fromHH = Integer.parseInt(classTime.substring(0, classTime.indexOf(":")));
                fromMM = classTime.substring(classTime.indexOf(":")+1, classTime.indexOf(":")+3);
                

                String classTimeTo = classTime.substring(classTime.indexOf("-")+2);
                toHH = Integer.parseInt(classTimeTo.substring(0, classTimeTo.indexOf(":")));
                toMM = classTimeTo.substring(classTimeTo.indexOf(":")+1, classTimeTo.indexOf(":")+3);
                toAMPM = classTimeTo.substring(classTimeTo.indexOf(" ")+1);
                
                sBean.setFromHour(fromHH);
                sBean.setFromMinute(fromMM);
                
                
                sBean.setToHour(toHH);
                sBean.setToMinute(toMM);
                sBean.setToAMPM(toAMPM); 
              }
              courseSectionList.add(sBean);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        Collections.sort(courseSectionList, ScheduleBean.CourseCodeComparator);
        return courseSectionList;
    }
    
    
    
    public int findScheduleId(int sectionId, int allScheduleId ){
        String query = "SELECT SCHEDULE_ID FROM SFADMIN.SCHEDULE WHERE SECTION_ID = "+sectionId+" AND ALL_SCH_ID = "+allScheduleId;
        
        int scheduleId = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              scheduleId = Integer.parseInt(rs.getString("SCHEDULE_ID"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        
        return scheduleId;
    
    }
    
    public String findClassTime(int sectionId, int allScheduleId ){
        String query = "SELECT CLASS_TIME FROM SFADMIN.SCHEDULE WHERE SECTION_ID = "+sectionId+" AND ALL_SCH_ID = "+allScheduleId;
       
        String classTime = "";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              classTime = rs.getString("CLASS_TIME");
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        return classTime;
    }
    public String findDays(int sectionId, int allScheduleId){
        String query = "SELECT DAYS FROM SFADMIN.SCHEDULE WHERE SECTION_ID = "+sectionId+" AND ALL_SCH_ID = "+allScheduleId;
       
        String day = "";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              day = rs.getString("DAYS");
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        return day;
    }
    
    
    
    public int findRoomId(int sectionId, int allScheduleId){
        String query = "SELECT ROOM_ID FROM SFADMIN.SCHEDULE WHERE SECTION_ID = "+sectionId+" AND ALL_SCH_ID = "+allScheduleId;
        int roomId = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              roomId = Integer.parseInt(rs.getString("ROOM_ID"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        return roomId;
    }
    
    
    public int findProfessorId(int sectionId, int allScheduleId){
        String query = "SELECT PROF_ID FROM SFADMIN.SCHEDULE WHERE SECTION_ID = "+sectionId+" AND ALL_SCH_ID = "+allScheduleId;
        int profId = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              profId = Integer.parseInt(rs.getString("PROF_ID"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        return profId;
    }
    public String findSectionNumber(int sectionID){
        String query = "SELECT SECTION_NUMBER FROM SFADMIN.COURSE_SECTION WHERE SECTION_ID = "+sectionID;
        String sectionNumber = "";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              sectionNumber = rs.getString("SECTION_NUMBER");
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        return sectionNumber;
    }
    public String findCourseCode(int courseId){
        String query = "SELECT COURSE_CODE FROM SFADMIN.COURSE WHERE COURSE_ID = "+courseId;
        String courseCode = "";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              courseCode = rs.getString("COURSE_CODE");
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        return courseCode;
    }
    //11:00 - 12:15 PM
    @Override
    public int addScheduleRow(ScheduleBean aSchedule){
        String classTime = aSchedule.getFromHour()+":"+aSchedule.getFromMinute()+" - "+aSchedule.getToHour()+":"+aSchedule.getToMinute()+" "+aSchedule.getToAMPM();
        String days = aSchedule.getDays().substring(1, aSchedule.getDays().length()-1);
        days = days.replace(", ", "");
        String insertString = "INSERT INTO SFADMIN.SCHEDULE (ALL_SCH_ID, SECTION_ID, PROF_ID, ROOM_ID,DAYS,CLASS_TIME,CREATED_TS,LAST_UPDT_TS) VALUES ("
                    + aSchedule.getAllScheduleId()
                    + "," + aSchedule.getSectionId()
                    + "," + aSchedule.getProfId()
                    + "," + aSchedule.getRoomId()
                    + ",'" + days
                    + "','" + classTime
                    + "', CURRENT_TIMESTAMP"
                    +", CURRENT_TIMESTAMP )" ;
        
        int rowCount = 0;
        
        try{
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            
                    
            rowCount = stmt.executeUpdate(insertString);
            System.out.println("Insert String: "+insertString);

            stmt.close();
            DBConn.close();
        }catch (SQLException e){
            System.err.println("ERROR: Problems with SQL select in findAllProfessors()");
            System.err.println(e.getMessage());
        }
        return rowCount;
        
    }
    
    
    
    
    @Override
    public int updateScheduleRow(ScheduleBean aSchedule){
        String classTime = aSchedule.getFromHour()+":"+aSchedule.getFromMinute()+" - "+aSchedule.getToHour()+":"+aSchedule.getToMinute()+" "+aSchedule.getToAMPM();
        String days = aSchedule.getDays().substring(1, aSchedule.getDays().length()-1);
        days = days.replace(", ", "");
        String updateString = "UPDATE SFADMIN.SCHEDULE SET PROF_ID = "+aSchedule.getProfId()+", "+
                              "ROOM_ID = " + aSchedule.getRoomId() + ", " +
                              "DAYS = '" + days + "', " + 
                              "CLASS_TIME = '" + classTime + "', " +
                              "LAST_UPDT_TS = CURRENT_TIMESTAMP " +
                              "WHERE SCHEDULE_ID = " + aSchedule.getScheduleId();                    
                   
        
        int rowCount = 0;
        
        try{
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
     
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("Update String: "+updateString);

            stmt.close();
            DBConn.close();
        }catch (SQLException e){
            System.err.println("ERROR: Problems with SQL select in findAllProfessors()");
            System.err.println(e.getMessage());
        }
        return rowCount;
        
    }
    
    @Override
    public ArrayList<ScheduleBean> findScheduleBeanForModifyFromDB(int allScheduleId){
        String query = "SELECT * FROM SFADMIN.SCHEDULE WHERE ALL_SCH_ID = "+allScheduleId;
        int scheduleId = 0;
        int sectionId = 0;
        int profId = 0;
        int roomId = 0;
        String days = "";
        String classTime = "";
        ArrayList<ScheduleBean> scheduleBeanArrList = new ArrayList<ScheduleBean>();
        ScheduleBean scheduleBean;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              scheduleId = Integer.parseInt(rs.getString("SCHEDULE_ID"));
              sectionId = Integer.parseInt(rs.getString("SECTION_ID"));
              profId = Integer.parseInt(rs.getString("PROF_ID"));
              roomId = Integer.parseInt(rs.getString("ROOM_ID"));
              days = rs.getString("DAYS");
              classTime = rs.getString("CLASS_TIME");
              scheduleBean = new ScheduleBean(scheduleId, allScheduleId, sectionId, profId, roomId, days, classTime);
              scheduleBeanArrList.add(scheduleBean);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        
        return scheduleBeanArrList;
    }
    
    @Override
    public int insertCourseSectionForSaveAsSchedule(ScheduleBean aSchedule, String sectionNumber, int courseId){
        
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String addCourseSectionFromSaveAsQuery = "INSERT INTO SFADMIN.COURSE_SECTION "
                    + "(COURSE_ID, ALL_SCH_ID, SECTION_NUMBER, CREATED_TS, LAST_UPDT_TS) VALUES ("
                    + courseId+", "
                    + aSchedule.getAllScheduleId()+", '"
                    + sectionNumber+"', "
                    + " CURRENT TIMESTAMP, CURRENT TIMESTAMP"
                    + ")";
            rowCount = stmt.executeUpdate(addCourseSectionFromSaveAsQuery);
            System.out.println("addString =" + addCourseSectionFromSaveAsQuery);
            System.out.println(rowCount + "row updated");
            
            System.out.println(rowCount + "Schedule entry added");
            
            int sectionId = findSectionId();
            aSchedule.setSectionId(sectionId);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewAllSchedule()");
            System.err.println(e.getMessage());
        }
        
        
        
        return rowCount;
    }
    
    public int findSectionId(){
        int sectionId = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String query = "SELECT MAX(SECTION_ID) FROM SFADMIN.COURSE_SECTION ";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                sectionId = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewAllSchedule()");
            System.err.println(e.getMessage());
        }
        
        
        
        return sectionId;
    }

    //129,130,131
    @Override
    public ArrayList<ScheduleBean> getConflictingSchedule(int allScheduleId,String scheduleIds) {
      System.out.println("In schdl DAOimpl");
        ArrayList<ScheduleBean> scheduleRows = new ArrayList<ScheduleBean>();
        String getScheduleQuery = "SELECT COURSE.COURSE_CODE,COURSE_SECTION.SECTION_NUMBER,PROFESSOR.PROF_NAME,DAYS,CLASS_TIME,ROOM.ROOM_NAME "
                + "FROM SFADMIN.COURSE_SECTION, SFADMIN.COURSE, SFADMIN.PROFESSOR, SFADMIN.ROOM, SFADMIN.SCHEDULE, SFADMIN.ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
        //        + "AND ALL_SCHEDULE.ALL_SCH_ID = 1";
        + "AND ALL_SCHEDULE.ALL_SCH_ID =" + allScheduleId +" AND SCHEDULE.SCHEDULE_ID IN ("+scheduleIds+")" ;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(getScheduleQuery);
          while(rs.next()){
                ScheduleBean scheduleBean = new ScheduleBean();
                scheduleBean.setCourseCode(rs.getString(1));
                 scheduleBean.setCourseSection(rs.getString(2));
                  scheduleBean.setProfessor(rs.getString(3));
                   scheduleBean.setDays(rs.getString(4));
                    scheduleBean.setClassTime(rs.getString(5));
                     scheduleBean.setRooms(rs.getString(6));
                     scheduleRows.add(scheduleBean);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllScheduleIDs()");
            System.err.println(e.getMessage());
        }
        return scheduleRows;
    }
    
   @Override
   public int findMaxScheduleId(){
       int schId = 0;
           try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String query = "SELECT MAX(SCHEDULE_ID) FROM SFADMIN.SCHEDULE ";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                schId = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewAllSchedule()");
            System.err.println(e.getMessage());
        }
        
        return schId;
        
   }

}
