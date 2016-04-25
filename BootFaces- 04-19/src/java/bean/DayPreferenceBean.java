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
public class DayPreferenceBean {

    private int dayPreferenceId;
    private int profId;
    private String days;
    private String preferredTime;
    private String finalComments;
    private String otherInterests;
    private String createdTS;
    private String lastUpdtTS;
    
    private String fromAMPM = "";
    private String toAMPM = "";
    private int fromHour;
    private String fromMinute;
    private int toHour;
    private String toMinute;

    public DayPreferenceBean(int dayPreferenceId, int profId, String days, String preferredTime, String finalComments, String otherInterests, String createdTS, String lastUpdtTS, int fromHour, String fromMinute, int toHour, String toMinute) {
        this.dayPreferenceId = dayPreferenceId;
        this.profId = profId;
        this.days = days;
        this.preferredTime = preferredTime;
        this.finalComments = finalComments;
        this.otherInterests = otherInterests;
        this.createdTS = createdTS;
        this.lastUpdtTS = lastUpdtTS;
        this.fromHour = fromHour;
        this.fromMinute = fromMinute;
        this.toHour = toHour;
        this.toMinute = toMinute;
    }

    

    

    
    
    /**
     * Creates a new instance of DayPreferenceBean
     */
    public DayPreferenceBean() {
//        DayPreferenceBean dayPreferenceBean = (DayPreferenceBean) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dayPreferenceBean"));
//        this.dayPreferenceId = dayPreferenceBean.getDayPreferenceId();
//        this.profId = dayPreferenceBean.getProfId();
//        this.days = dayPreferenceBean.getDays();
//        this.preferredTime = dayPreferenceBean.getIsEvening();
//        this.createdTS = dayPreferenceBean.getCreatedTS();
//        this.lastUpdtTS = dayPreferenceBean.getLastUpdtTS();
    }

    /**
     * @return the dayPreferenceId
     */
    public int getDayPreferenceId() {
        return dayPreferenceId;
    }

    /**
     * @param dayPreferenceId the dayPreferenceId to set
     */
    public void setDayPreferenceId(int dayPreferenceId) {
        this.dayPreferenceId = dayPreferenceId;
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
     * @return the preferredTime
     */
    public String getPreferredTime() {
        return preferredTime;
    }

    /**
     * @param preferredTime the preferredTime to set
     */
    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    /**
     * @return the finalComments
     */
    public String getFinalComments() {
        return finalComments;
    }

    /**
     * @param finalComments the finalComments to set
     */
    public void setFinalComments(String finalComments) {
        this.finalComments = finalComments;
    }

    /**
     * @return the otherInterests
     */
    public String getOtherInterests() {
        return otherInterests;
    }

    /**
     * @param otherInterests the otherInterests to set
     */
    public void setOtherInterests(String otherInterests) {
        this.otherInterests = otherInterests;
    }

    /**
     * @return the fromAMPM
     */
    public String getFromAMPM() {
        return fromAMPM;
    }

    /**
     * @param fromAMPM the fromAMPM to set
     */
    public void setFromAMPM(String fromAMPM) {
        this.fromAMPM = fromAMPM;
    }

    /**
     * @return the toAMPM
     */
    public String getToAMPM() {
        return toAMPM;
    }

    /**
     * @param toAMPM the toAMPM to set
     */
    public void setToAMPM(String toAMPM) {
        this.toAMPM = toAMPM;
    }

    /**
     * @return the fromHour
     */
    public int getFromHour() {
        return fromHour;
    }

    /**
     * @param fromHour the fromHour to set
     */
    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }

    /**
     * @return the fromMinute
     */
    public String getFromMinute() {
        return fromMinute;
    }

    /**
     * @param fromMinute the fromMinute to set
     */
    public void setFromMinute(String fromMinute) {
        this.fromMinute = fromMinute;
    }

    /**
     * @return the toHour
     */
    public int getToHour() {
        return toHour;
    }

    /**
     * @param toHour the toHour to set
     */
    public void setToHour(int toHour) {
        this.toHour = toHour;
    }

    /**
     * @return the toMinute
     */
    public String getToMinute() {
        return toMinute;
    }

    /**
     * @param toMinute the toMinute to set
     */
    public void setToMinute(String toMinute) {
        this.toMinute = toMinute;
    }

}
