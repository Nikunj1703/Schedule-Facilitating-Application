/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.CoursePreferenceBean;
import bean.DayPreferenceBean;
import bean.ProfessorBean;
import bean.ReviewCommentBean;
import bean.ToDoBean;
import bean.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunjan
 */
public class UserDAOImpl implements UserDAO {

    private static int isAdmin = -1000;
    private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    @Override
    public int authenticate(User user) {

        Connection DBConn = null;

        String ulid;
        String password;
        String userId;
        String createdTS;
        String lastUpdtTS;

        String query1 = "SELECT COUNT(1) FROM USERS WHERE ULID='" + user.getUlid().toLowerCase().toString().trim() + "'";
        String query = "SELECT * FROM USERS WHERE ULID = '" + user.getUlid().toLowerCase().toString().trim() + "' AND PASSWORD = '" + user.getPassword().toString().trim() + "'";
        String query2 = "SELECT COUNT(1) FROM USERS WHERE ULID = '" + user.getUlid().toLowerCase().toString().trim() + "' AND PASSWORD = '" + user.getPassword().toString().trim() + "'";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            Statement stmt1 = DBConn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);
            int exists = 0; //0 = false, 1= true
            while (rs1.next()) {

                exists = Integer.parseInt(rs1.getString(1));
                System.out.println("CCHECK DB" + exists);
            }

            Statement stmt2 = DBConn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);
            int valid = 0; //0 = false, 1= true
            while (rs2.next()) {

                valid = Integer.parseInt(rs2.getString(1));
                System.out.println("CCHECK DB VALID" + valid);
            }
            if (exists != 0 && valid == 0) {
                isAdmin = -3;  //ulid/pwd combo incorrect 
            }
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // if username exists only then check for login
            if (exists != 0 && valid != 0) {

                while (rs.next()) {

                    userId = rs.getString("USER_ID");
                    ulid = rs.getString("ULID");
                    password = rs.getString("PASSWORD");
                    isAdmin = Integer.parseInt(rs.getString("ISADMIN"));
                    createdTS = rs.getString("CREATED_TS");
                    lastUpdtTS = rs.getString("LAST_UPDT_TS");

                    System.out.println("C>" + createdTS + " | U>" + ulid + " | P>" + password);
                }
            } // else set isAdmin to unregistered user i.e = -2
            else if (exists == 0 && valid == 0) {
                isAdmin = -2;
            }

            rs.close();
            rs1.close();
            stmt.close();
            stmt1.close();
        } catch (Exception e) {
            isAdmin = -1;
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return isAdmin;
    }

    @Override
    public int checkUlidPresent(String ulid) {
        Connection DBConn = null;
        String query1 = "SELECT COUNT(1) FROM USERS WHERE ULID='" + ulid.toLowerCase().toString().trim() + "'";
        int exists = 0;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            
            Statement stmt1 = DBConn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);
             //0 = false, 1= true
            while (rs1.next()) {

                exists = Integer.parseInt(rs1.getString(1));
                System.out.println("CCHECK DB" + exists);
            }
        } catch (Exception e) {
            isAdmin = -1;
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        return exists;
    }
    
    @Override
    public boolean isFacultyUlid(String ulid){
        Connection DBConn = null;
        String query1 = "SELECT COUNT(1) FROM PROFESSOR WHERE ULID='" + ulid.toLowerCase().toString().trim() + "'";
        int exists = 0;
        boolean isFaculty=false;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            
            Statement stmt1 = DBConn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);
             //0 = false, 1= true
            while (rs1.next()) {

                exists = Integer.parseInt(rs1.getString(1));
                if(exists==1){
                    isFaculty = true;
                }
                System.out.println("CCHECK DB" + exists);
            }
        } catch (Exception e) {
            isAdmin = -1;
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }   
        return isFaculty;
    }
    
    @Override
    public int createAccount(User user){
    int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String addRoomQuery = "INSERT INTO USERS "
                    + "(ULID,PASSWORD,ISADMIN,CREATED_TS,LAST_UPDT_TS) " 
                    + "VALUES ('"+user.getUlid()+"','"+user.getPassword()+"',0,"
                    +"CURRENT TIMESTAMP, CURRENT TIMESTAMP)";
            rowCount = stmt.executeUpdate(addRoomQuery);
           
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewRoom()");
            System.err.println(e.getMessage());
        }
        
        
        return rowCount;
    }

    @Override
    public boolean checkToDoItemExists(ProfessorBean professorBean
    ) {
        Connection DBConn = null;
        String countItemsQuery = "SELECT COUNT(1) FROM TO_DO WHERE ULID='" + professorBean.getUlid().toLowerCase().toString().trim() + "'";
        boolean result = false;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            Statement stmt1 = DBConn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(countItemsQuery);
            int exists = 0; //0 = false, 1= true
            while (rs1.next()) {

                exists = Integer.parseInt(rs1.getString(1));
                System.out.println("checkToDoItemExists" + exists);
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
        } finally {
            try {
                DBConn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ToDoBean fetchToDoItems(ProfessorBean professorBean
    ) {
        Connection DBConn = null;
        ToDoBean toDoBean = new ToDoBean();
        String query = "SELECT * FROM TO_DO WHERE ULID = '" + professorBean.getUlid().toLowerCase().toString().trim() + "'";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            int i = 0;
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // if username exists only then check for login
            while (rs.next()) {

                toDoBean.setToDoId(rs.getInt("TO_DO_ID"));
                toDoBean.setUlid(rs.getString("ULID"));
                toDoBean.setAllScheduleId(rs.getInt("ALL_SCH_ID"));
                toDoBean.setStatus(rs.getInt("STATUS"));
                toDoBean.setCreatedTS(rs.getString("CREATED_TS"));
                toDoBean.setLastUpdtTS(rs.getString("LAST_UPDT_TS"));
                toDoBean.setCountItems(++i);
                System.out.println(" values set in the TODO bean");
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return toDoBean;
    }

    @Override
    public boolean updateToDoStatusDB(ToDoBean toDoBean, ProfessorBean professorBean
    ) {
        Connection DBConn = null;
        int rowCount = 100;
        try {

            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            Statement stmt = DBConn.createStatement();

            String updateStatusQuery = "UPDATE SFADMIN.TO_DO SET STATUS = " + toDoBean.getStatus() + " WHERE TO_DO_ID = " + toDoBean.getToDoId() + " AND ULID='" + professorBean.getUlid() + "'";
            rowCount = stmt.executeUpdate(updateStatusQuery);
            System.out.println("addString =" + updateStatusQuery);
            System.out.println(rowCount + "row updated");
            DBConn.close();
            if (rowCount == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewCourse()");
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean checkCommentsExists(ProfessorBean professorBean, ToDoBean toDoBean
    ) {

        Connection DBConn = null;
        boolean result = false;
        String countItemsQuery = "SELECT COUNT(1) FROM REVIEW_COMMENT WHERE ULID='" + professorBean.getUlid().toLowerCase().toString().trim() + "' AND ALL_SCH_ID=" + toDoBean.getAllScheduleId();
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
    public ReviewCommentBean fetchComments(ProfessorBean professorBean, ToDoBean toDoBean
    ) {
        Connection DBConn = null;
        ReviewCommentBean reviewCommentBean = new ReviewCommentBean();
        String query = "SELECT * FROM REVIEW_COMMENT WHERE ULID = '" + professorBean.getUlid().toLowerCase().toString().trim() + "' AND ALL_SCH_ID=" + toDoBean.getAllScheduleId();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                reviewCommentBean.setCommentID(rs.getInt("COMMENT_ID"));
                reviewCommentBean.setUlid(rs.getString("ULID"));
                reviewCommentBean.setAllScheduleId(rs.getInt("ALL_SCH_ID"));
                reviewCommentBean.setCommentText(rs.getString("COMMENT_TEXT"));
                reviewCommentBean.setCreatedTS(rs.getString("CREATED_TS"));
                reviewCommentBean.setLastUpdtTS(rs.getString("LAST_UPDT_TS"));
                System.out.println(" values set in the reviewCommentBean bean");
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return reviewCommentBean;
    }

    @Override
    public boolean updateComments(ReviewCommentBean reviewCommentBean, ToDoBean toDoBean
    ) {

        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String updateCommentsQuery = "UPDATE REVIEW_COMMENT SET "
                    + " COMMENT_TEXT='" + reviewCommentBean.getCommentText() + "' WHERE COMMENT_ID = " + reviewCommentBean.getCommentID()
                    + " AND ALL_SCH_ID =" + reviewCommentBean.getAllScheduleId();
            rowCount = stmt.executeUpdate(updateCommentsQuery);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert/select/update in UpdateThesis()");
            System.err.println(e.getMessage());
        }

        if (rowCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addComments(ReviewCommentBean reviewCommentBean, ToDoBean toDoBean
    ) {
        int rowCount = 0;
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            String addCommentsQuery = " INSERT INTO REVIEW_COMMENT (ULID,ALL_SCH_ID,COMMENT_TEXT,CREATED_TS,LAST_UPDT_TS) "
                    + "VALUES ('" + reviewCommentBean.getUlid() + "'," + reviewCommentBean.getAllScheduleId() + ",'" + reviewCommentBean.getCommentText() + "'"
                    + ",CURRENT TIMESTAMP, CURRENT TIMESTAMP)";
            rowCount = stmt.executeUpdate(addCommentsQuery);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewRoom()");
            System.err.println(e.getMessage());
        }

        if (rowCount == 1) {
            return true;
        } else {
            return false;
        }
    }
}
