/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gunjan
 */
@ManagedBean
@SessionScoped
public class ToDoBean {

    private int toDoId;
    private String ulid;
    private int allScheduleId;
    private int status;
    private String createdTS;
    private String lastUpdtTS;
    private int countItems;

    public ToDoBean(int toDoId, String ulid, int allScheduleId, int status, String createdTS, String lastUpdtTS, int countItems) {
        this.toDoId = toDoId;
        this.ulid = ulid;
        this.allScheduleId = allScheduleId;
        this.status = status;
        this.createdTS = createdTS;
        this.lastUpdtTS = lastUpdtTS;
        this.countItems = countItems;
    }

    
    /**
     * Creates a new instance of ToDoBean
     */
    public ToDoBean() {
//        ToDoBean todoBean = (ToDoBean) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("toDoBean"));
//        this.toDoId = todoBean.getToDoId();
//        this.ulid = todoBean.getUlid();
//        this.allScheduleId = todoBean.getAllScheduleId();
//        this.status = todoBean.getStatus();
//        this.createdTS = todoBean.getCreatedTS();
//        this.lastUpdtTS = todoBean.getLastUpdtTS();
    }

    /**
     * @return the toDoId
     */
    public int getToDoId() {
        return toDoId;
    }

    /**
     * @param toDoId the toDoId to set
     */
    public void setToDoId(int toDoId) {
        this.toDoId = toDoId;
    }

    /**
     * @return the ulid
     */
    public String getUlid() {
        return ulid;
    }

    /**
     * @param ulid the ulid to set
     */
    public void setUlid(String ulid) {
        this.ulid = ulid;
    }

    /**
     * @return the allScheduleId
     */
    public int getAllScheduleId() {
        return allScheduleId;
    }

    /**
     * @param allScheduleId the allScheduleId to set
     */
    public void setAllScheduleId(int allScheduleId) {
        this.allScheduleId = allScheduleId;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the createdTS
     */
    public String getCreatedTS() {
        return createdTS;
    }

    /**
     * @param createdTS the createdTS to set
     */
    public void setCreatedTS(String createdTS) {
        this.createdTS = createdTS;
    }

    /**
     * @return the lastUpdtTS
     */
    public String getLastUpdtTS() {
        return lastUpdtTS;
    }

    /**
     * @param lastUpdtTS the lastUpdtTS to set
     */
    public void setLastUpdtTS(String lastUpdtTS) {
        this.lastUpdtTS = lastUpdtTS;
    }

    /**
     * @return the countItems
     */
    public int getCountItems() {
        return countItems;
    }

    /**
     * @param countItems the countItems to set
     */
    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }

}
