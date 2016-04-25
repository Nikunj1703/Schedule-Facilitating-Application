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
 * @author it3680110
 */
@ManagedBean
@SessionScoped
public class AllScheduleBean {

      private int allScheduleId;
    private String scheduleName;
    private String scheduleDescription;
    private String createdTS;
    private String lastUpdtTS;
     private int all_Sch_Id;

    public AllScheduleBean(int allScheduleId, String scheduleName, String scheduleDescription, String createdTS, String lastUpdtTS) {
        this.allScheduleId = allScheduleId;
        this.scheduleName = scheduleName;
        this.scheduleDescription = scheduleDescription;
        this.createdTS = createdTS;
        this.lastUpdtTS = lastUpdtTS;
    }
    
    
    /**
     * Creates a new instance of AllScheduleBean
     */
    public AllScheduleBean() {
    }

    /**
     * @return the allScheduleId
     */
    public int getAllScheduleId() {
        return allScheduleId;
    }
     public int getAll_Sch_Id() {
        return all_Sch_Id;
    }

    public void setAll_Sch_Id(int all_Sch_Id) {
        this.all_Sch_Id = all_Sch_Id;
    }
    

    /**
     * @param allScheduleId the allScheduleId to set
     */
    public void setAllScheduleId(int allScheduleId) {
        this.allScheduleId = allScheduleId;
    }

    /**
     * @return the scheduleName
     */
    public String getScheduleName() {
        return scheduleName;
    }

    /**
     * @param scheduleName the scheduleName to set
     */
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    /**
     * @return the scheduleDescription
     */
    public String getScheduleDescription() {
        return scheduleDescription;
    }

    /**
     * @param scheduleDescription the scheduleDescription to set
     */
    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
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
