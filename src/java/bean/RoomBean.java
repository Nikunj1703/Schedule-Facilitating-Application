/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author MinuRachel
 */
public class RoomBean {
    private String RoomName;
    private String is_lab;
    private String is_available;
    private boolean editable;
    private int roomId;
    
    public RoomBean(){
        RoomName = "";
        is_lab = "";
        is_available = "";
        editable = false;
    }

    public RoomBean(String RoomName, String is_lab, String is_available) {
        this.RoomName = RoomName;
        this.is_lab = is_lab;
        this.is_available = is_available;
    }

    /**
     * @return the RoomName
     */
    public String getRoomName() {
        return RoomName;
    }

    /**
     * @param RoomName the RoomName to set
     */
    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    /**
     * @return the is_lab
     */
    public String getIs_lab() {
        return is_lab;
    }

    /**
     * @param is_lab the is_lab to set
     */
    public void setIs_lab(String is_lab) {
        this.is_lab = is_lab;
    }

    /**
     * @return the is_available
     */
    public String getIs_available() {
        return is_available;
    }

    /**
     * @param is_available the is_available to set
     */
    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    
}
