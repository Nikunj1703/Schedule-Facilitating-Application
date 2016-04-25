/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.ExportBean;
import bean.ReportBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MinuRachel
 */
public class ReportDAOImpl implements ReportDAO {

    private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    @Override
    public ArrayList populateCourseSectionReport(int allSchId) {
        ArrayList allCourseSectionList = new ArrayList();
        ReportBean aReport;

        String query = "SELECT COURSE.COURSE_CODE,COURSE_SECTION.SECTION_NUMBER,PROFESSOR.PROF_NAME,DAYS,CLASS_TIME,ROOM.ROOM_NAME "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + " ORDER BY COURSE_CODE, SECTION_NUMBER ASC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aReport = new ReportBean();
                aReport.setCourse_code(rs.getString("COURSE_CODE"));
                aReport.setSection_number(rs.getString("SECTION_NUMBER"));
                aReport.setProfessorName(rs.getString("PROF_NAME"));
                aReport.setDays(rs.getString("DAYS"));
                aReport.setClassTime(rs.getString("CLASS_TIME"));
                aReport.setRoomName(rs.getString("ROOM_NAME"));
                allCourseSectionList.add(aReport);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in populateCourseSectionReport()");
            System.err.println(e.getMessage());
        }
        return allCourseSectionList;
    }

    @Override
    public ArrayList populateInstructorReport(int allSchId) {
        ArrayList allCourseSectionList = new ArrayList();
        ReportBean aReport;

        String query = "SELECT COURSE.COURSE_CODE,COURSE_SECTION.SECTION_NUMBER,PROFESSOR.PROF_NAME,DAYS,CLASS_TIME,ROOM.ROOM_NAME "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + " ORDER BY COURSE_CODE, SECTION_NUMBER ASC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aReport = new ReportBean();
                aReport.setCourse_code(rs.getString("COURSE_CODE"));
                aReport.setSection_number(rs.getString("SECTION_NUMBER"));
                aReport.setProfessorName(rs.getString("PROF_NAME"));
                aReport.setDays(rs.getString("DAYS"));
                aReport.setClassTime(rs.getString("CLASS_TIME"));
                aReport.setRoomName(rs.getString("ROOM_NAME"));
                allCourseSectionList.add(aReport);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in populateInstructorReport()");
            System.err.println(e.getMessage());
        }
        return allCourseSectionList;
    }

    @Override
    public ArrayList populateTimeTableReport(int allSchId) {
        ArrayList allCourseSectionList = new ArrayList();
        ReportBean aReport;

        String query = "SELECT COURSE.COURSE_CODE,COURSE_SECTION.SECTION_NUMBER,PROFESSOR.PROF_NAME,DAYS,CLASS_TIME,ROOM.ROOM_NAME "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + " ORDER BY COURSE_CODE, SECTION_NUMBER ASC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aReport = new ReportBean();
                aReport.setCourse_code(rs.getString("COURSE_CODE"));
                aReport.setSection_number(rs.getString("SECTION_NUMBER"));
                aReport.setProfessorName(rs.getString("PROF_NAME"));
                aReport.setDays(rs.getString("DAYS"));
                aReport.setClassTime(rs.getString("CLASS_TIME"));
                aReport.setRoomName(rs.getString("ROOM_NAME"));
                allCourseSectionList.add(aReport);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in populateTimeTableReport()");
            System.err.println(e.getMessage());
        }
        return allCourseSectionList;
    }

    @Override
    public ArrayList findLabRooms() {
        ArrayList allLabRooms = new ArrayList();

        String query = "SELECT ROOM_NAME FROM SFADMIN.ROOM WHERE IS_LAB=1";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                allLabRooms.add(rs.getString("ROOM_NAME"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findLabRooms()");
            System.err.println(e.getMessage());
        }
        return allLabRooms;
    }

    @Override
    public ArrayList findLectureRooms() {
        ArrayList allLectureRooms = new ArrayList();

        String query = "SELECT ROOM_NAME FROM SFADMIN.ROOM WHERE IS_LAB=0";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                allLectureRooms.add(rs.getString("ROOM_NAME"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findLectureRooms()");
            System.err.println(e.getMessage());
        }
        return allLectureRooms;
    }

    @Override
    public ArrayList findMondayWednesdayClassesReport(int allSchId) {
        ArrayList allMWCourseSectionList = new ArrayList();
        ReportBean aReport;

        String query = "SELECT DAYS,ROOM.ROOM_NAME,CLASS_TIME,COURSE.COURSE_CODE,COURSE_SECTION.SECTION_NUMBER,PROFESSOR.PROF_NAME "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + "AND DAYS LIKE 'M%' "
                + "ORDER BY COURSE_CODE, SECTION_NUMBER ASC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aReport = new ReportBean();
                aReport.setCourse_code(rs.getString("COURSE_CODE"));
                aReport.setSection_number(rs.getString("SECTION_NUMBER"));
                aReport.setProfessorName(rs.getString("PROF_NAME"));
                aReport.setDays(rs.getString("DAYS"));
                aReport.setClassTime(rs.getString("CLASS_TIME"));
                aReport.setRoomName(rs.getString("ROOM_NAME"));
                allMWCourseSectionList.add(aReport);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findMondayWednesdayClassesReport()");
            System.err.println(e.getMessage());
        }
        return allMWCourseSectionList;
    }

    @Override
    public ArrayList findMondayWednesdayLectureTimeSlots(int allSchId) {
        ArrayList allMWTimeSlotList = new ArrayList();

        String query = "SELECT DISTINCT(CLASS_TIME) "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + "AND DAYS LIKE 'M%' "
                + "AND ROOM.IS_LAB=0 "
                + "GROUP BY CLASS_TIME "
                + "ORDER BY CLASS_TIME DESC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                allMWTimeSlotList.add(rs.getString("CLASS_TIME"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findMondayWednesdayTimeSlots()");
            System.err.println(e.getMessage());
        }
        return allMWTimeSlotList;
    }

    @Override
    public ArrayList findMondayWednesdayLabTimeSlots(int allSchId) {
        ArrayList allMWTimeSlotList = new ArrayList();

        String query = "SELECT DISTINCT(CLASS_TIME) "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + "AND DAYS LIKE 'M%' "
                + "AND ROOM.IS_LAB=1 "
                + "GROUP BY CLASS_TIME "
                + "ORDER BY CLASS_TIME DESC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                allMWTimeSlotList.add(rs.getString("CLASS_TIME"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findMondayWednesdayTimeSlots()");
            System.err.println(e.getMessage());
        }
        return allMWTimeSlotList;
    }

    @Override
    public ArrayList findTuesdayThursdayClassesReport(int allSchId) {
        ArrayList allTRCourseSectionList = new ArrayList();
        ReportBean aReport;

        String query = "SELECT DAYS,ROOM.ROOM_NAME,CLASS_TIME,COURSE.COURSE_CODE,COURSE_SECTION.SECTION_NUMBER,PROFESSOR.PROF_NAME "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + "AND DAYS LIKE 'T%' "
                + "ORDER BY COURSE_CODE, SECTION_NUMBER ASC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aReport = new ReportBean();
                aReport.setCourse_code(rs.getString("COURSE_CODE"));
                aReport.setSection_number(rs.getString("SECTION_NUMBER"));
                aReport.setProfessorName(rs.getString("PROF_NAME"));
                aReport.setDays(rs.getString("DAYS"));
                aReport.setClassTime(rs.getString("CLASS_TIME"));
                aReport.setRoomName(rs.getString("ROOM_NAME"));
                allTRCourseSectionList.add(aReport);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findMondayWednesdayClassesReport()");
            System.err.println(e.getMessage());
        }
        return allTRCourseSectionList;
    }

    @Override
    public ArrayList findTuesdayThursdayLectureTimeSlots(int allSchId) {
        ArrayList allTRTimeSlotList = new ArrayList();

        String query = "SELECT DISTINCT(CLASS_TIME) "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + "AND DAYS LIKE 'T%' "
                + "AND ROOM.IS_LAB=0"
                + "GROUP BY CLASS_TIME "
                + "ORDER BY CLASS_TIME DESC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                allTRTimeSlotList.add(rs.getString("CLASS_TIME"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findMondayWednesdayTimeSlots()");
            System.err.println(e.getMessage());
        }
        return allTRTimeSlotList;
    }

    @Override
    public ArrayList findTuesdayThursdayLabTimeSlots(int allSchId) {
        ArrayList allTRTimeSlotList = new ArrayList();

        String query = "SELECT DISTINCT(CLASS_TIME) "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + "AND DAYS LIKE 'T%' "
                + "AND ROOM.IS_LAB = 1 "
                + "GROUP BY CLASS_TIME "
                + "ORDER BY CLASS_TIME DESC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                allTRTimeSlotList.add(rs.getString("CLASS_TIME"));
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findMondayWednesdayTimeSlots()");
            System.err.println(e.getMessage());
        }
        return allTRTimeSlotList;
    }

    //this method is used in the send for Review use case
    @Override
    public int addNewReviewer(ArrayList ulidList, int allSchId) {
        int rowCount = 0;
        int totalRows = 0;
        for (int i = 0; i < ulidList.size(); i++) {

            rowCount = 0;
            try {
                DBHelper.loadDriver(driver);
                Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
                Statement stmt = DBConn.createStatement();

                String addToDoEntry = "INSERT INTO SFADMIN.TO_DO "
                        + "(ULID, ALL_SCH_ID, STATUS, CREATED_TS, LAST_UPDT_TS) VALUES ('"
                        + ulidList.get(i)
                        + "', " + allSchId
                        + ", 1, CURRENT TIMESTAMP, CURRENT TIMESTAMP)";
                rowCount = stmt.executeUpdate(addToDoEntry);
                totalRows++;
                System.out.println("addString =" + addToDoEntry);
                System.out.println(rowCount + "row added");

                System.out.println(rowCount + "Todo entry added");

                DBConn.close();
            } catch (SQLException e) {
                System.err.println("ERROR: Problems with SQL insert in addNewReviewer()");
                System.err.println(e.getMessage());
            }
        }
        if (totalRows == ulidList.size()) {
            return totalRows;
        } else {
            return 0;
        }
    }

    //this methods are used for the export schedule use case
    @Override
    public ArrayList populateExportTable(int allSchId) {
        ArrayList allCourseSectionList = new ArrayList();
        ExportBean anExport;

        String query = "SELECT ALL_SCHEDULE.SCHEDULE_NAME, COURSE.COURSE_CODE, COURSE_SECTION.SECTION_NUMBER, PROFESSOR.PROF_NAME, DAYS,CLASS_TIME, ROOM.ROOM_NAME "
                + "FROM COURSE_SECTION, COURSE, PROFESSOR, ROOM, SCHEDULE, ALL_SCHEDULE "
                + "WHERE COURSE_SECTION.SECTION_ID = SCHEDULE.SECTION_ID "
                + "AND COURSE_SECTION.COURSE_ID = COURSE.COURSE_ID "
                + "AND COURSE_SECTION.ALL_SCH_ID = SCHEDULE.ALL_SCH_ID "
                + "AND PROFESSOR.PROF_ID = SCHEDULE.PROF_ID "
                + "AND ROOM.ROOM_ID = SCHEDULE.ROOM_ID "
                + "AND SCHEDULE.ALL_SCH_ID = ALL_SCHEDULE.ALL_SCH_ID "
                + "AND ALL_SCHEDULE.ALL_SCH_ID = " + allSchId
                + " ORDER BY COURSE_CODE, SECTION_NUMBER ASC";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                anExport = new ExportBean();
                String scheduleName = rs.getString("SCHEDULE_NAME");
                String sem = scheduleName.substring(0, scheduleName.indexOf("_"));
                String year = scheduleName.substring(scheduleName.indexOf("_")+1, scheduleName.lastIndexOf("_"));
                anExport.setSem(sem);
                anExport.setYear(year);
                anExport.setCourse(rs.getString("COURSE_CODE"));
                anExport.setSection(rs.getString("SECTION_NUMBER"));
                anExport.setInstructor(rs.getString("PROF_NAME"));
                anExport.setDays(rs.getString("DAYS"));
                anExport.setTime(rs.getString("CLASS_TIME"));
                anExport.setRoom(rs.getString("ROOM_NAME"));
                anExport.setGradFlag("");
                anExport.setGradLabNote("");
                anExport.setOtherNote("");
                anExport.setSectionNote("");
                allCourseSectionList.add(anExport);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in populateExportTable()");
            System.err.println(e.getMessage());
        }
        return allCourseSectionList;
    }
}
