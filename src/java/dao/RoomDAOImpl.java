/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import bean.RoomBean;

/**
 *
 * @author MinuRachel
 */
public class RoomDAOImpl implements RoomDAO {

//    private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/msabu_Sp16_sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    @Override
    public ArrayList findAllRooms() {
        ArrayList allRooms = new ArrayList();
        RoomBean aRoom;

        String query = "SELECT ROOM.ROOM_ID, ROOM.ROOM_NAME, ROOM.IS_LAB, ROOM.IS_AVAILABLE "
                + "FROM SFADMIN.ROOM";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aRoom = new RoomBean();
                aRoom.setRoomName(rs.getString("ROOM_NAME"));
                aRoom.setRoomId(Integer.parseInt(rs.getString("ROOM_ID")));
                int is_available = rs.getInt("IS_AVAILABLE");
                if(is_available==0){
                    aRoom.setIs_available("NotAvailable");
                }else if(is_available==1){
                    aRoom.setIs_available("Available");
                }
                int is_lab = rs.getInt("IS_LAB");
                if(is_lab==0){
                    aRoom.setIs_lab("Lecture");
                }else{
                    aRoom.setIs_lab("Lab");
                }
                allRooms.add(aRoom);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllRooms()");
            System.err.println(e.getMessage());
        }
        return allRooms;
    }

    @Override
    public int updateRoomInfo(RoomBean updatedRoom) {
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
    
    @Override
    public int addNewRoom(RoomBean newRoom){
        String room_name = newRoom.getRoomName();
        String is_labString = newRoom.getIs_lab();
        String is_availableString = newRoom.getIs_available();
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

            String addRoomQuery = "INSERT INTO SFADMIN.ROOM "
                    + "(ROOM_NAME, IS_LAB, IS_AVAILABLE, CREATED_TS, LAST_UPDT_TS) VALUES ('"
                    + room_name
                    + "', " + is_lab
                    + ", " + is_available
                    + ", CURRENT TIMESTAMP, CURRENT TIMESTAMP"
                    + ")";
            rowCount = stmt.executeUpdate(addRoomQuery);
            System.out.println("addString =" + addRoomQuery);
            System.out.println(rowCount + "row updated");

            System.out.println(rowCount + "Room entry added");

            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL insert in addNewRoom()");
            System.err.println(e.getMessage());
        }
        
        
        return rowCount;
    }
}
