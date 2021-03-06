/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.CoursePreferenceBean;
import bean.DayPreferenceBean;
import bean.ProfessorBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gunjan
 */
public class PreferencesDAOImpl implements PreferencesDAO {

    private static int isAdmin = -1000;
    private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    @Override
    public boolean checkCoursePrefExists(ProfessorBean professorBean, int courseId) {
        Connection DBConn = null;
        boolean result = false;
        String countItemsQuery = "SELECT COUNT(1) FROM COURSE_PREFERENCE WHERE PROF_ID=" + professorBean.getProfessorId() + " AND COURSE_ID = " + courseId;
        //String query = "SELECT * FROM COURSE_PREFERENCE WHERE PROF_ID = " + professorBean.getProfessorId();

        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt1 = DBConn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(countItemsQuery);
            int exists = 0; //0 = false, 1= true
            while (rs1.next()) {

                exists = Integer.parseInt(rs1.getString(1));
                System.out.println("CCHECK DB" + exists);
            }
            if (exists >= 1) {
                result = true;
            } else {
                result = false;
            }

            rs1.close();
            stmt1.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<CoursePreferenceBean> fetchCoursePreference(ProfessorBean professorBean) {
        Connection DBConn = null;
        ArrayList<CoursePreferenceBean> coursePrefList = new ArrayList<CoursePreferenceBean>();
        String selectQuery = " SELECT COURSE_PREFERENCE_ID,COURSE_PREFERENCE.PROF_ID, COURSE_PREFERENCE.COURSE_ID, LAB_NEEDED, INTEREST_LEVEL, COURSE_COMMENTS, "
                + " COURSE_PREFERENCE.CREATED_TS, COURSE_PREFERENCE.LAST_UPDT_TS,COURSE.COURSE_CODE,COURSE.COURSE_NAME "
                + " FROM COURSE_PREFERENCE, COURSE "
                + " WHERE COURSE_PREFERENCE.COURSE_ID = COURSE.COURSE_ID "
                + " AND COURSE_PREFERENCE.PROF_ID = " + professorBean.getProfessorId();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            while (rs.next()) {
                CoursePreferenceBean coursePreferenceBean = new CoursePreferenceBean();
                coursePreferenceBean.setCoursePreferenceId(rs.getInt(1));
                coursePreferenceBean.setProfId(rs.getInt(2));
                coursePreferenceBean.setCourseId(rs.getInt(3));
                coursePreferenceBean.setLabNeeded(rs.getInt(4));
                coursePreferenceBean.setInterestLevel(rs.getInt(5));
                coursePreferenceBean.setComments(rs.getString(6));
                coursePreferenceBean.setCreatedTS(rs.getString(7));
                coursePreferenceBean.setLastUpdtTS(rs.getString(8));
                coursePreferenceBean.setCourseCode(rs.getString(9));
                coursePreferenceBean.setCourseName(rs.getString(10));
                coursePrefList.add(coursePreferenceBean);

            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return coursePrefList;
    }

    @Override
    public boolean checkDayPrefExists(ProfessorBean professorBean) {

        Connection DBConn = null;
        boolean result = false;
        String countItemsQuery = "SELECT COUNT(1) FROM DAY_PREFERENCE WHERE PROF_ID =" + professorBean.getProfessorId();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            Statement stmt1 = DBConn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(countItemsQuery);
            int exists = 0; //0 = false, 1= true
            while (rs1.next()) {

                exists = Integer.parseInt(rs1.getString(1));
            }

            if (exists == 1) {
                result = true;
            } else {
                result = false;
            }

            rs1.close();
            stmt1.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;

    }

    @Override
    public DayPreferenceBean fetchDayPreferences(ProfessorBean professorBean) {

        Connection DBConn = null;
        DayPreferenceBean dayPreferenceBean = new DayPreferenceBean();
        String query = "SELECT DAY_PREFERENCE_ID, PROF_ID, DAYS, PREFERRED_TIME, FINAL_COMMENTS, OTHER_INTERESTS, CREATED_TS, LAST_UPDT_TS FROM DAY_PREFERENCE WHERE PROF_ID = " + professorBean.getProfessorId();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                dayPreferenceBean.setDayPreferenceId(rs.getInt("DAY_PREFERENCE_ID"));
                dayPreferenceBean.setProfId(rs.getInt("PROF_ID"));
                dayPreferenceBean.setDays(rs.getString("DAYS"));
                String preferredTime = rs.getString("PREFERRED_TIME");
                dayPreferenceBean.setPreferredTime(rs.getString("PREFERRED_TIME"));

                if (preferredTime.length() != 0) {
                    int fromHH = 0;
                    String fromMM = "";

                    int toHH = 0;
                    String toMM = "";
                    String toAMPM = "";

                    //1:00 PM - 1:00 PM
                    fromHH = Integer.parseInt(preferredTime.substring(0, preferredTime.indexOf(":")));
                    fromMM = preferredTime.substring(preferredTime.indexOf(":") + 1, preferredTime.indexOf(":") + 3);

                    String preferredTimeTo = preferredTime.substring(preferredTime.indexOf("-") + 2);
                    toHH = Integer.parseInt(preferredTimeTo.substring(0, preferredTimeTo.indexOf(":")));
                    toMM = preferredTimeTo.substring(preferredTimeTo.indexOf(":") + 1, preferredTimeTo.indexOf(":") + 3);
                    toAMPM = preferredTimeTo.substring(preferredTimeTo.indexOf(" ") + 1);

                    dayPreferenceBean.setFromHour(fromHH);
                    dayPreferenceBean.setFromMinute(fromMM);

                    dayPreferenceBean.setToHour(toHH);
                    dayPreferenceBean.setToMinute(toMM);
                    dayPreferenceBean.setToAMPM(toAMPM);
                }
                dayPreferenceBean.setFinalComments(rs.getString("FINAL_COMMENTS"));
                dayPreferenceBean.setOtherInterests(rs.getString("OTHER_INTERESTS"));
                dayPreferenceBean.setCreatedTS(rs.getString("CREATED_TS"));
                dayPreferenceBean.setLastUpdtTS(rs.getString("LAST_UPDT_TS"));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return dayPreferenceBean;
    }

    @Override
    public int addNewCoursePreference(CoursePreferenceBean coursePreferenceBean) {
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String addRoomQuery = "INSERT INTO COURSE_PREFERENCE "
                    + "(PROF_ID,COURSE_ID,LAB_NEEDED,INTEREST_LEVEL, COURSE_COMMENTS,CREATED_TS,LAST_UPDT_TS) "
                    + "VALUES (" + coursePreferenceBean.getProfId() + "," + coursePreferenceBean.getCourseId() + ","
                    + coursePreferenceBean.getLabNeeded() + "," + coursePreferenceBean.getInterestLevel() + ",'" + coursePreferenceBean.getComments() + "', CURRENT TIMESTAMP, CURRENT TIMESTAMP )";
            rowCount = stmt.executeUpdate(addRoomQuery);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewRoom()");
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

    @Override
    public int updateCoursePreferences(CoursePreferenceBean coursePreferenceBean) {

        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String preferencesUpdateQuery = "UPDATE COURSE_PREFERENCE SET COURSE_ID=" + coursePreferenceBean.getCourseId()
                    + ", LAB_NEEDED=" + coursePreferenceBean.getLabNeeded() + " ,INTEREST_LEVEL=" + coursePreferenceBean.getInterestLevel() + " , COURSE_COMMENTS='" + coursePreferenceBean.getComments() + "', LAST_UPDT_TS = CURRENT_TIMESTAMP "
                    + " WHERE COURSE_PREFERENCE_ID=" + coursePreferenceBean.getCoursePreferenceId() + " AND PROF_ID=" + coursePreferenceBean.getProfId();
            rowCount = stmt.executeUpdate(preferencesUpdateQuery);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert/select/update in UPDATE COURSE_PREFERENCE");
            System.err.println(e.getMessage());
        }
        return rowCount;
    }

    @Override
    public int updateDayPreferences(DayPreferenceBean dayPreferenceBean) {
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String preferencesUpdateQuery = "UPDATE DAY_PREFERENCE SET DAYS='" + dayPreferenceBean.getDays()
                    + "' , PREFERRED_TIME = '" + dayPreferenceBean.getPreferredTime() + "', FINAL_COMMENTS = '" + dayPreferenceBean.getFinalComments() + "' , "
                    + " OTHER_INTERESTS = '" + dayPreferenceBean.getOtherInterests() + "', LAST_UPDT_TS=CURRENT_TIMESTAMP "
                    + "WHERE DAY_PREFERENCE_ID=" + dayPreferenceBean.getDayPreferenceId() + " AND PROF_ID=" + dayPreferenceBean.getProfId();
            rowCount = stmt.executeUpdate(preferencesUpdateQuery);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert/select/update in UPDATE COURSE_PREFERENCE");
            System.err.println(e.getMessage());
        }
        return rowCount;
    }

    @Override
    public int addDayPreferences(DayPreferenceBean dayPreferenceBean) {
          int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String preferencesInsertQuery = "INSERT INTO DAY_PREFERENCE "
                    + "(PROF_ID,DAYS,PREFERRED_TIME,FINAL_COMMENTS,OTHER_INTERESTS,CREATED_TS,LAST_UPDT_TS) " +
                "VALUES( " +dayPreferenceBean.getProfId()+", '" +dayPreferenceBean.getDays()+"' , '"+ dayPreferenceBean.getPreferredTime() + "', '" + dayPreferenceBean.getFinalComments() + "' , "
                    + "'" + dayPreferenceBean.getOtherInterests() + "', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP )";
           rowCount = stmt.executeUpdate(preferencesInsertQuery);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert/select/update in UPDATE DAY_PREFERENCE");
            System.err.println(e.getMessage());
        }
        return rowCount;  }
}
