/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.HashMap;

/**
 *
 * @author MinuRachel
 */
public class ClassRoomReportBean {
    
    private String timeString;
    private HashMap<String, String> infoMap;

    public ClassRoomReportBean() {
        this.timeString = "";
        this.infoMap = new HashMap<String, String>();
    }

    public ClassRoomReportBean(String timeString, HashMap<String, String> infoMap) {
        this.timeString = timeString;
        this.infoMap = infoMap;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public HashMap<String, String> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(HashMap<String, String> infoMap) {
        this.infoMap = infoMap;
    }

}
