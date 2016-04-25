/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Nikunj
 */
public class ProfessorBean {
    private int professorId;
    private String professorName;
    private String ulid;
    private String employmentStatus;
    private String teachingStatus;
    private String createdTS;
    private String lastUpdatedTS;

    public ProfessorBean() {
    }
    
    
    public ProfessorBean(String ulid, String employmentStatus, String teachingStatus) {
        this.ulid = ulid;
        this.employmentStatus = employmentStatus;
        this.teachingStatus = teachingStatus;
    }

    public ProfessorBean(String professorName, String ulid, String employmentStatus, String teachingStatus) {
        this.professorName = professorName;
        this.ulid = ulid;
        this.employmentStatus = employmentStatus;
        this.teachingStatus = teachingStatus;
    }
    
    
    
    public ProfessorBean(int professorId, String professorName, String ulid, String employmentStatus, String teachingStatus, String createdTS, String lastUpdatedTS) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.ulid = ulid;
        this.employmentStatus = employmentStatus;
        this.teachingStatus = teachingStatus;
        this.createdTS = createdTS;
        this.lastUpdatedTS = lastUpdatedTS;
    }

    /**
     * @return the professorId
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * @param professorId the professorId to set
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }


    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getUlid() {
        return ulid;
    }

    public void setUlid(String ulid) {
        this.ulid = ulid;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getTeachingStatus() {
        return teachingStatus;
    }

    public void setTeachingStatus(String teachingStatus) {
        this.teachingStatus = teachingStatus;
    }

    public String getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(String createdTS) {
        this.createdTS = createdTS;
    }

    public String getLastUpdatedTS() {
        return lastUpdatedTS;
    }

    public void setLastUpdatedTS(String lastUpdatedTS) {
        this.lastUpdatedTS = lastUpdatedTS;
    }
    
    
}
