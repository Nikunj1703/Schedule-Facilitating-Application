/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gunjan
 */
@ManagedBean
@SessionScoped
public class ReviewCommentBean {

    private int commentID;
    private String ulid;
    private int allScheduleId;
    private String commentText;
    private String createdTS;
    private String lastUpdtTS;

    public ReviewCommentBean(int commentID, String ulid, int allScheduleId, String commentText, String createdTS, String lastUpdtTS) {
        this.commentID = commentID;
        this.ulid = ulid;
        this.allScheduleId = allScheduleId;
        this.commentText = commentText;
        this.createdTS = createdTS;
        this.lastUpdtTS = lastUpdtTS;
    }

    
    /**
     * Creates a new instance of ToDoBean
     */
    public ReviewCommentBean() {
//        ToDoBean todoBean = (ToDoBean) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("toDoBean"));
//        this.toDoId = todoBean.getToDoId();
//        this.ulid = todoBean.getUlid();
//        this.allScheduleId = todoBean.getAllScheduleId();
//        this.status = todoBean.getStatus();
//        this.createdTS = todoBean.getCreatedTS();
//        this.lastUpdtTS = todoBean.getLastUpdtTS();
    }

    /**
     * @return the commentID
     */
    public int getCommentID() {
        return commentID;
    }

    /**
     * @param commentID the commentID to set
     */
    public void setCommentID(int commentID) {
        this.commentID = commentID;
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
     * @return the commentText
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * @param commentText the commentText to set
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
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

}