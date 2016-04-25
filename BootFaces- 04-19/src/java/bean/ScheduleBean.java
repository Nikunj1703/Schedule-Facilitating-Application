/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Comparator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gunjan
 */
@ManagedBean
@SessionScoped
public class ScheduleBean {

     // table fields
    private int scheduleId;
    private int allScheduleId;
    private int sectionId;
    private int profId;
    private int roomId;
    private String days;
    private String classTime;
    private String createdTS;
    private String lastUpdtTS;

    //query fields
    private String courseCode;
    private String courseSection;
    private String professor;
    private String rooms;
    
    //intermediate Variables
    String fromAMPM = "";
    String toAMPM = "";
    private int fromHour;
    private String fromMinute;
    private int toHour;
    private String toMinute;

    public ScheduleBean(int scheduleId, int allScheduleId, int sectionId, int profId, int roomId, String days, String classTime, String createdTS, String lastUpdtTS, String courseCode, String courseSection, String professor, String rooms) {
        this.scheduleId = scheduleId;
        this.allScheduleId = allScheduleId;
        this.sectionId = sectionId;
        this.profId = profId;
        this.roomId = roomId;
        this.days = days;
        this.classTime = classTime;
        this.createdTS = createdTS;
        this.lastUpdtTS = lastUpdtTS;
        this.courseCode = courseCode;
        this.courseSection = courseSection;
        this.professor = professor;
        this.rooms = rooms;
    }

    public ScheduleBean(int scheduleId, int allScheduleId, int sectionId, int profId, int roomId, String days, String classTime) {
        this.scheduleId = scheduleId;
        this.allScheduleId = allScheduleId;
        this.sectionId = sectionId;
        this.profId = profId;
        this.roomId = roomId;
        this.days = days;
        this.classTime = classTime;
    }
    
    
    public ScheduleBean() {
    }

    /**
     * @return the scheduleId
     */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * @param scheduleId the scheduleId to set
     */
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
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
     * @return the sectionId
     */
    public int getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
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
     * @return the roomId
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the days
     */
    public String getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(String days) {
        this.days = days;
    }

    /**
     * @return the classTime
     */
    public String getClassTime() {
        return classTime;
    }

    /**
     * @param classTime the classTime to set
     */
    public void setClassTime(String classTime) {
        this.classTime = classTime;
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
     * @return the courseSection
     */
    public String getCourseSection() {
        return courseSection;
    }

    /**
     * @param courseSection the courseSection to set
     */
    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    /**
     * @return the professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     * @return the rooms
     */
    public String getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public static Comparator<ScheduleBean> CourseCodeComparator = new Comparator<ScheduleBean>() {

	public int compare(ScheduleBean s1, ScheduleBean s2) {
	   String CourseCode1 = s1.getCourseCode();
	   String CourseCode2 = s2.getCourseCode();

	   //ascending order
	   return CourseCode1.compareTo(CourseCode2);
          
    }};

    public String getFromAMPM() {
        return fromAMPM;
    }

    public void setFromAMPM(String fromAMPM) {
        this.fromAMPM = fromAMPM;
    }

    public String getToAMPM() {
        return toAMPM;
    }

    public void setToAMPM(String toAMPM) {
        this.toAMPM = toAMPM;
    }



    public int getFromHour() {
        return fromHour;
    }

    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }

    public String getFromMinute() {
        return fromMinute;
    }

    public void setFromMinute(String fromMinute) {
        this.fromMinute = fromMinute;
    }

    public int getToHour() {
        return toHour;
    }

    public void setToHour(int toHour) {
        this.toHour = toHour;
    }

    public String getToMinute() {
        return toMinute;
    }

    public void setToMinute(String toMinute) {
        this.toMinute = toMinute;
    }

  

        
   

}
