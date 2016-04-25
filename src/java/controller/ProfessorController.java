/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import dao.RoomDAO;
import dao.RoomDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import bean.ProfessorBean;
import dao.ProfessorDAO;
import dao.ProfessorDAOImpl;
import java.util.ArrayList;


/**
 *
 * @author Nikunj
 */
@ManagedBean
@SessionScoped
public class ProfessorController {

    private ProfessorBean theProfessorModel;
    
    private List<ProfessorBean> professorList;
    private ProfessorBean selectedProfessor;
    private boolean editable;
    private String updateStatus;
    private boolean isProfessorEnabled;

    public List<ProfessorBean> getProfessorList() {
        ProfessorDAO aProfessorList = new ProfessorDAOImpl();
        professorList = aProfessorList.findAllProfessors();
        return professorList;
    }

    public void setProfessorList(List<ProfessorBean> professorList) {
        this.professorList = professorList;
    }

    public ProfessorController() {
        theProfessorModel = new ProfessorBean();

        this.editable = false;

    }

    /**
     * @return the theProfessorModel
     */
    public ProfessorBean getTheProfessorModel() {
        return theProfessorModel;
    }

    /**
     * @param theProfessorModel the theProfessorModel to set
     */
    public void setTheProfessorModel(ProfessorBean theProfessorModel) {
        this.theProfessorModel = theProfessorModel;
    }

    /**
     * @return the selectedProfessor
     */
    public ProfessorBean getSelectedProfessor() {
        return selectedProfessor;
    }

    /**
     * @param selectedProfessor the selectedProfessor to set
     */
    public void setSelectedProfessor(ProfessorBean selectedProfessor) {
        this.selectedProfessor = selectedProfessor;
    }

   
    

    public String saveAction() {
        ProfessorBean updatedProfessor;
        ProfessorDAO updateProfessorDAO = new ProfessorDAOImpl();
        int status = 0;
        //get all existing value but set "editable" to false 
        for (ProfessorBean aProfessor : professorList) {
            System.out.println(aProfessor.getUlid());
            System.out.println(aProfessor.getEmploymentStatus());
            System.out.println(aProfessor.getTeachingStatus());
            System.out.println();
            updatedProfessor = new ProfessorBean(aProfessor.getProfessorName(), aProfessor.getUlid(), aProfessor.getEmploymentStatus(), aProfessor.getTeachingStatus());
            status = updateProfessorDAO.updateProfessorInfo(updatedProfessor);
            if (status == 0) {
                setUpdateStatus("Could not update your submission. Please check your values.");
                break;
            }
        }
        if (status == 1) {
            setUpdateStatus("All Changes are Saved Successfully!");
        }
        this.setEditable(false);
        professorList = null;
        //return to current page
        return null;

    }

    public String editAction() {
        this.setEditable(true);
        return null;
    }

    public String stopEditAction() {
        this.setEditable(false);
        return null;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
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

    public String addNewProfessor() {
        ProfessorDAO addProfessorDAO = new ProfessorDAOImpl();
        int rowCount = addProfessorDAO.addNewProfessor(theProfessorModel);
        if (rowCount == 1) {
            updateStatus = "New Professor Added Successfully";
        } else {
            updateStatus = "Errors encountered while adding new room. Please try again later.";
        }
        return null;
    }

    public String enableProfessors() {
        this.setIsProfessorEnabled(true);

        return null;
    }

    /**
     * @return the isProfessorEnabled
     */
    public boolean isProfessorEnabled() {
        return isProfessorEnabled;
    }

    /**
     * @param isProfessorEnabled the isProfessorEnabled to set
     */
    public void setIsProfessorEnabled(boolean isProfessorEnabled) {
        this.isProfessorEnabled = isProfessorEnabled;
    }
    

}