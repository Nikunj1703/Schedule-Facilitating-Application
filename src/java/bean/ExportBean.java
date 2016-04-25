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
public class ExportBean {
    
    private String year;
    private String sem;
    private String course;
    private String section;
    private String instructor;
    private String gradLabNote;
    private String sectionNote;
    private String otherNote;
    private String days;
    private String time;
    private String room;
    private String gradFlag;

    public ExportBean() {
        this.year = "";
        this.sem = "";
        this.course = "";
        this.section = "";
        this.instructor = "";
        this.gradLabNote = "";
        this.sectionNote = "";
        this.otherNote = "";
        this.days = "";
        this.time = "";
        this.room = "";
        this.gradFlag = "";
    }

    public ExportBean(String year, String sem, String course, String section, String instructor, String gradLabNote, String sectionNote, String otherNote, String days, String time, String room, String gradFlag) {
        this.year = year;
        this.sem = sem;
        this.course = course;
        this.section = section;
        this.instructor = instructor;
        this.gradLabNote = gradLabNote;
        this.sectionNote = sectionNote;
        this.otherNote = otherNote;
        this.days = days;
        this.time = time;
        this.room = room;
        this.gradFlag = gradFlag;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getGradLabNote() {
        return gradLabNote;
    }

    public void setGradLabNote(String gradLabNote) {
        this.gradLabNote = gradLabNote;
    }

    public String getSectionNote() {
        return sectionNote;
    }

    public void setSectionNote(String sectionNote) {
        this.sectionNote = sectionNote;
    }

    public String getOtherNote() {
        return otherNote;
    }

    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getGradFlag() {
        return gradFlag;
    }

    public void setGradFlag(String gradFlag) {
        this.gradFlag = gradFlag;
    }
    
    
    
    
}
