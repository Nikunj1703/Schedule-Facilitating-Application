/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gunjan
 */
@ManagedBean
@SessionScoped
public class CoursePreferenceBean {
   
    private int coursePreferenceId;
    private int profId;
    private int courseId;
    private int labNeeded;
    private int interestLevel;
    private String comments;
    private String createdTS;
    private String lastUpdtTS;
    private ArrayList<CoursePreferenceBean> coursePrefList = new ArrayList<CoursePreferenceBean>();

    private String courseCode;
    private String courseName;

    public CoursePreferenceBean(int coursePreferenceId, int profId, int courseId, int labNeeded, String comments, int interestLevel) {
        this.coursePreferenceId = coursePreferenceId;
        this.profId = profId;
        this.courseId = courseId;
        this.labNeeded = labNeeded;
        this.comments = comments;
        this.interestLevel = interestLevel;
    }

    

    
   
    
    /**
     * Creates a new instance of CoursePreferenceBean
     */
    public CoursePreferenceBean() {
//          CoursePreferenceBean coursePreferenceBean = (CoursePreferenceBean) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("coursePreferenceBean"));
//                 this.coursePreferenceId = coursePreferenceBean.getCoursePreferenceId();
//        this.profId = coursePreferenceBean.getProfId();
//        this.courseId = coursePreferenceBean.getCourseId();
//        this.labNeeded = coursePreferenceBean.getLabNeeded();
//        this.comments = coursePreferenceBean.getComments();
//        this.createdTS = coursePreferenceBean.getCreatedTS();
//        this.lastUpdtTS = coursePreferenceBean.getLastUpdtTS();
    }

    /**
     * @return the coursePreferenceId
     */
    public int getCoursePreferenceId() {
        return coursePreferenceId;
    }

    /**
     * @param coursePreferenceId the coursePreferenceId to set
     */
    public void setCoursePreferenceId(int coursePreferenceId) {
        this.coursePreferenceId = coursePreferenceId;
    }

    /**
     * @return the profId
     */
    public int getProfId() {
        return profId;
    }

    /**
     * @param profId the profId to set
     */
    public void setProfId(int profId) {
        this.profId = profId;
    }

    /**
     * @return the courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the labNeeded
     */
    public int getLabNeeded() {
        return labNeeded;
    }

    /**
     * @param labNeeded the labNeeded to set
     */
    public void setLabNeeded(int labNeeded) {
        this.labNeeded = labNeeded;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
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
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the coursePrefList
     */
    public ArrayList<CoursePreferenceBean> getCoursePrefList() {
        return coursePrefList;
    }

    /**
     * @param coursePrefList the coursePrefList to set
     */
    public void setCoursePrefList(ArrayList<CoursePreferenceBean> coursePrefList) {
        this.coursePrefList = coursePrefList;
    }

    /**
     * @return the interestLevel
     */
    public int getInterestLevel() {
        return interestLevel;
    }

    /**
     * @param interestLevel the interestLevel to set
     */
    public void setInterestLevel(int interestLevel) {
        this.interestLevel = interestLevel;
    }

  
 
}
