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
public class ReportBean {
    private String course_code;
    private String section_number;
    private String professorName;
    private String days;
    private String classTime;
    private String RoomName;
    private String classesByRoom;
    
    public ReportBean(){
        course_code = "";
        section_number = "";
        professorName = "";
        days = "";
        RoomName = "";
        classTime = "";  
        classesByRoom = "";
    }

    public ReportBean(String course_code, String section_number, String professorName, String days, String classTime, String RoomName, String classesByRoom) {
        this.course_code = course_code;
        this.section_number = section_number;
        this.professorName = professorName;
        this.days = days;
        this.classTime = classTime;
        this.RoomName = RoomName;
        this.classesByRoom = classesByRoom;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getSection_number() {
        return section_number;
    }

    public void setSection_number(String section_number) {
        this.section_number = section_number;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getClassesByRoom() {
        return classesByRoom;
    }

    public void setClassesByRoom(String classesByRoom) {
        this.classesByRoom = classesByRoom;
    }
    
    
}
