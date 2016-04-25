/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AllScheduleDAO;
import dao.AllScheduleDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import bean.AllScheduleBean;
import java.util.ArrayList;
import org.jboss.weld.context.RequestContext;
import org.w3c.dom.html.HTMLButtonElement;
import org.w3c.dom.html.HTMLDivElement;

/**
 *
 * @author Shweta Vyas
 */
@ManagedBean
@SessionScoped
public class AllScheduleController {
    
    private AllScheduleBean theAllScheduleModel;
    private String term;
    private String year;
    private String scheduleVersion;
    private String updateStatus;
    private ArrayList<String> allScheduleVersionsList;
    private String style = "display : none";
    private String style2 = "display : none";
   // private List<AllScheduleBean> allScheduleList;
    private String termModify;
    private String yearModify;

    public AllScheduleController(){
        theAllScheduleModel = new AllScheduleBean();
    }
    public AllScheduleBean getTheAllScheduleModel() {
        return theAllScheduleModel;
    }

    public void setTheAllScheduleModel(AllScheduleBean theAllScheduleModel) {
        this.theAllScheduleModel = theAllScheduleModel;
    }

    public ArrayList<String> getAllScheduleVersionsList() {
        return allScheduleVersionsList;
    }

    public String getScheduleVersion() {
        return scheduleVersion;
    }

    public void setScheduleVersion(String scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

     public String getStyle2() {
        return style2;
    }

    public void setStyle2(String style2) {
        this.style2 = style2;
    }

    public String getTermModify() {
        return termModify;
    }

    public void setTermModify(String termModify) {
        this.termModify = termModify;
    }

    public String getYearModify() {
        return yearModify;
    }

    public void setYearModify(String yearModify) {
        this.yearModify = yearModify;
    }

    

    /**
     * @return the updateStatus
     */
    public String getUpdateStatus() {
        return updateStatus;
    }

    /**
     * @param updateStatus the updateStatus to set
     */
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
    public String addNewAllScheduleId() {
        this.style = "display : inline-table";
        this.style2 = "display : none";
        AllScheduleDAO addAllScheduleIdDAO = new AllScheduleDAOImpl();
        theAllScheduleModel = addAllScheduleIdDAO.addNewAllScheduleID(theAllScheduleModel,term,year);
        if (theAllScheduleModel !=null ) {
            
            updateStatus = "New Schedule Created Successfully";
        } else {
            updateStatus = "Errors encountered while adding new course. Please try again later.";
        }
        return null;
    }
    
    public void findAllScheduleVersions(String term, String year){
//        term = "Fall";
//        year = "2016";
        AllScheduleDAO findAllScheduleVersionsFromDAO = new AllScheduleDAOImpl();
        allScheduleVersionsList = findAllScheduleVersionsFromDAO.findScheduleVersionsFromDB(term+"_"+year);
        
        //return allScheduleVersionsList;
    }
    
    public String modifyExistingSchedule(){
        this.style = "display : none";
        this.style2 = "display : inline-table";
        System.out.println(scheduleVersion);
        int allScheduleId = findAllScheduleIdToModify(scheduleVersion);
        theAllScheduleModel.setAllScheduleId(allScheduleId);
        theAllScheduleModel.setAll_Sch_Id(allScheduleId);
        return "";
    }
    
    public int findAllScheduleIdToModify(String scheduleVersion){
        //Find AllSchduleID from Db based on the parameter
        AllScheduleDAO findAllScheduleIDBySchVersionNameFromDAO = new AllScheduleDAOImpl();
        return findAllScheduleIDBySchVersionNameFromDAO.findAllScheduleIDBySchVersionNameFromDB(scheduleVersion);
    }

}
