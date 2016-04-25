/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RoomDAO;
import dao.RoomDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import bean.RoomBean;

/**
 *
 * @author MinuRachel
 */
@ManagedBean
@SessionScoped
public class RoomController {

    private RoomBean theRoomModel;
    private List<RoomBean> roomList;
    private RoomBean selectedRoom;
    private boolean editable;
    private String updateStatus;
    private boolean isRoomEnabled;

    public List<RoomBean> getRoomList() {
        RoomDAO aRoomList = new RoomDAOImpl();
        roomList = aRoomList.findAllRooms();
        return roomList;
    }

    public void setRoomList(List<RoomBean> roomList) {
        this.roomList = roomList;
    }

    public RoomController() {
        theRoomModel = new RoomBean();
//        selectedRoom = new RoomBean();
        this.editable = false;

    }

    /**
     * @return the theRoomModel
     */
    public RoomBean getTheRoomModel() {
        return theRoomModel;
    }

    /**
     * @param theRoomModel the theRoomModel to set
     */
    public void setTheRoomModel(RoomBean theRoomModel) {
        this.theRoomModel = theRoomModel;
    }

    /**
     * @return the selectedRoom
     */
    public RoomBean getSelectedRoom() {
        return selectedRoom;
    }

    /**
     * @param selectedRoom the selectedRoom to set
     */
    public void setSelectedRoom(RoomBean selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public String saveAction() {
        RoomBean updatedRoom;
        RoomDAO updateRoomsDAO = new RoomDAOImpl();
        int status = 0;
        //get all existing value but set "editable" to false 
        for (RoomBean aRoom : roomList) {
            System.out.println(aRoom.getRoomName());
            System.out.println(aRoom.getIs_lab());
            System.out.println(aRoom.getIs_available());
            System.out.println();
            updatedRoom = new RoomBean(aRoom.getRoomName(), aRoom.getIs_lab(), aRoom.getIs_available());
            status = updateRoomsDAO.updateRoomInfo(updatedRoom);
            if (status == 0) {
                setUpdateStatus("Could not update your submission. Please check your values.");
                break;
            }
        }
        if (status == 1) {
            setUpdateStatus("All Changes are Saved Successfully!");
        }
        this.setEditable(false);
        //return to current page
        return null;

    }

    public String editAction() {
        this.setEditable(true);
        return null;
    }

    public String stopEditAction() {
        this.setEditable(false);
        return null;
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

    /**
     * @return the updateStatus
     */
    public String getUpdateStatus() {
        return updateStatus;
    }

    /**
     * @param updateStatus the updateStatus to set
     */
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String addNewRoom() {
        RoomDAO addRoomDAO = new RoomDAOImpl();
        int rowCount = addRoomDAO.addNewRoom(theRoomModel);
        if (rowCount == 1) {
            updateStatus = "New Room Added Successfully";
        } else {
            updateStatus = "Errors encountered while adding new room. Please try again later.";
        }
        return null;
    }

    public String enableRooms() {
        this.setIsRoomEnabled(true);

        return null;
    }

    /**
     * @return the isRoomEnabled
     */
    public boolean isIsRoomEnabled() {
        return isRoomEnabled;
    }

    /**
     * @param isRoomEnabled the isRoomEnabled to set
     */
    public void setIsRoomEnabled(boolean isRoomEnabled) {
        this.isRoomEnabled = isRoomEnabled;
    }

}
