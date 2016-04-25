/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.CourseBean;
import bean.CourseSectionBean;
import bean.ScheduleBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Shweta Vyas
 */
public class CourseDAOImpl implements CourseDAO{
//    private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/msabu_Sp16_sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    @Override
    public ArrayList findAllCourses(int allScheduleId) {
        ArrayList allCourses = new ArrayList();
        CourseBean aCourse;

        String query = "SELECT * FROM SFADMIN.COURSE";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aCourse = new CourseBean();
                aCourse.setCourse_code(rs.getString("COURSE_CODE"));
                aCourse.setCourse_name(rs.getString("COURSE_NAME"));
                int course_id = findCourseId(rs.getString("COURSE_CODE"));
                int numOfSections = numOfSections(course_id, allScheduleId);
                aCourse.setNumberOfSections(numOfSections);
                allCourses.add(aCourse);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllRooms()");
            System.err.println(e.getMessage());
        }
        return allCourses;
    }
    
    @Override
    public int numOfSections(int courseId, int allScheduleId){
        
        String query = "SELECT COUNT(*) FROM SFADMIN.COURSE_SECTION WHERE COURSE_ID = "+courseId+" AND ALL_SCH_ID = "+allScheduleId;
        int count = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllRooms()");
            System.err.println(e.getMessage());
        }
        return count;
    }

    @Override
    public boolean checkScheduleAndCourseInDb(int course_id, int all_sch_id){
        String query = "SELECT COUNT(*) FROM SFADMIN.COURSE_SECTION WHERE COURSE_ID = " + course_id +" AND ALL_SCH_ID = " + all_sch_id;
        int count = 0;
        boolean flag = false;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            if(rs.wasNull()){
                flag = true;
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllRooms()");
            System.err.println(e.getMessage());
        }
        return flag;
    }
    /*@Override
    public int updateCourseInfo(CourseBean updatedR) {
        String room_name = updatedRoom.getRoomName();
        String is_labString = updatedRoom.getIs_lab();
        String is_availableString = updatedRoom.getIs_available();
        int is_lab, is_available;
        if (is_labString.equalsIgnoreCase("lab")) {
            is_lab = 1;
        } else {
            is_lab = 0;
        }
        if (is_availableString.equalsIgnoreCase("available")) {
            is_available = 1;
        } else {
            is_available = 0;
        }
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String roomUpdateQuery = "UPDATE SFADMIN.ROOM SET "
                    + "IS_LAB = " + is_lab
                    + ", IS_AVAILABLE = " + is_available
                    + ", LAST_UPDT_TS = CURRENT TIMESTAMP"
                    + " WHERE ROOM_NAME = '" + room_name + "'";
            rowCount = stmt.executeUpdate(roomUpdateQuery);
            System.out.println("updateString =" + roomUpdateQuery);
            System.out.println(rowCount + "row updated");

            System.out.println(rowCount + "Room entry updated");

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert/select/update in UpdateThesis()");
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    */
    @Override
    public int addNewCourse(CourseBean newCourse){
        String course_code = newCourse.getCourse_code();
        String course_name = newCourse.getCourse_name();
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String addCourseQuery = "INSERT INTO SFADMIN.COURSE "
                    + "(COURSE_CODE, COURSE_NAME, CREATED_TS, LAST_UPDT_TS) VALUES ('"
                    + course_code
                    + "', '" + course_name
                    + "', CURRENT TIMESTAMP, CURRENT TIMESTAMP"
                    + ")";
            rowCount = stmt.executeUpdate(addCourseQuery);
            System.out.println("addString =" + addCourseQuery);
            System.out.println(rowCount + "row updated");

            System.out.println(rowCount + "Course entry added");

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewCourse()");
            System.err.println(e.getMessage());
        }
        
        
        return rowCount;
    }
    @Override
    public int findCourseId(String course_code){
        int course_id = 0;
        String query = "SELECT * FROM SFADMIN.COURSE WHERE COURSE_CODE = '" + course_code + "'";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
               course_id = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewCourse()");
            System.err.println(e.getMessage());
        }
        
        
        return course_id;
        
    }
    @Override
    public int addNewCourseSection(CourseSectionBean newCourseSection){
        int course_id = newCourseSection.getCourse_ID();
        int all_sch_id = newCourseSection.getAll_sch_id();
        String section_Num = newCourseSection.getSection_number();
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String addCourseSectionQuery = "INSERT INTO SFADMIN.COURSE_SECTION "
                    + "(COURSE_ID, ALL_SCH_ID, SECTION_NUMBER,CREATED_TS, LAST_UPDT_TS) VALUES ("
                    + course_id + ", "  
                    + all_sch_id + ", '" 
                    + section_Num + "', " 
                    + "CURRENT TIMESTAMP, CURRENT TIMESTAMP"
                    + ")";
            rowCount = stmt.executeUpdate(addCourseSectionQuery);
            System.out.println("addString =" + addCourseSectionQuery);
            System.out.println(rowCount + "row updated");

            System.out.println(rowCount + "Course entry added");

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewCourse()");
            System.err.println(e.getMessage());
        }
        
        
        return rowCount;
    }

      @Override
    public ArrayList<CourseBean> getAllCourses() {
          ArrayList<CourseBean> courseList = new ArrayList<CourseBean>();
        String query = "SELECT COURSE_ID,COURSE_CODE, COURSE_NAME FROM SFADMIN.COURSE";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              CourseBean cBean = new CourseBean();
              cBean.setCourse_id(rs.getInt("COURSE_ID"));
              cBean.setCourse_code(rs.getString("COURSE_CODE"));
              cBean.setCourse_name(rs.getString("COURSE_NAME"));
              courseList.add(cBean);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in getAllCourses()");
            System.err.println(e.getMessage());
        }
        return courseList;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
