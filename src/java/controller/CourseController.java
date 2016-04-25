/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.AllScheduleBean;
import dao.CourseDAO;
import dao.CourseDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import bean.CourseBean;
import bean.CourseSectionBean;
import java.util.ArrayList;
/**
 *
 * @author Shweta Vyas
 */
@ManagedBean
@SessionScoped
public class CourseController {

    private CourseBean theCourseModel;
    private CourseSectionBean theCourseSectionModel;
    private List<CourseBean> courseList;
    private List<CourseBean> allCourseList;
    private List<CourseSectionBean> courseSectionList;
    private CourseBean selectedCourse;
    private boolean editable;
    private String updateStatus;
    private String updatedCourse;
    //private boolean isRoomEnabled;
   

    CourseDAO courseDAO = new CourseDAOImpl();
   
    public List<CourseBean> getCourseList(AllScheduleBean theAllScheduleModel) {
      // CourseDAO aCourseList = new CourseDAOImpl();
        courseList = courseDAO.findAllCourses(theAllScheduleModel.getAll_Sch_Id());
        return courseList;
    }

    public void setCourseList(List<CourseBean> courseList) {
        this.courseList = courseList;
    }

    /**
     * @return the allCourseList
     */
    public List<CourseBean> getAllCourseList() {
       allCourseList = courseDAO.getAllCourses();
        return allCourseList;
    }

    /**
     * @param allCourseList the allCourseList to set
     */
    public void setAllCourseList(List<CourseBean> allCourseList) {
        this.allCourseList = allCourseList;
    }

    /**
     * @return the allCoursesList
     */
   
    public CourseController() {
        theCourseModel = new CourseBean();
//        selectedRoom = new RoomBean();
        this.editable = false;

    }

    public CourseBean getTheCourseModel() {
        return theCourseModel;
    }

    public void setTheCourseModel(CourseBean theCourseModel) {
        this.theCourseModel = theCourseModel;
    }

    public CourseBean getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(CourseBean selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    
   public String saveAction(AllScheduleBean allScheduleModel) {
        CourseSectionBean updatedCourseSection;
        CourseDAO updateCourseDAO = new CourseDAOImpl();
        int status = 0;
        //int sectionNumber = 1;
        //get all existing value but set "editable" to false 
        for (CourseBean aCourse : courseList) {
            int course_id = updateCourseDAO.findCourseId(aCourse.getCourse_code());
            int all_sch_id = allScheduleModel.getAll_Sch_Id();
            int numberOfSections = aCourse.getNumberOfSections();
            if(numberOfSections != 0){
            for(int sectionNumber = 1; sectionNumber<=numberOfSections;sectionNumber++){
                String section_number = Integer.toString(sectionNumber);
                updatedCourseSection = new CourseSectionBean(course_id, all_sch_id, section_number);
                aCourse.setNumberOfSections(numberOfSections);
                boolean scheduleCourseExist = updateCourseDAO.checkScheduleAndCourseInDb(course_id, all_sch_id);
                if(!scheduleCourseExist)
                    status = updateCourseDAO.addNewCourseSection(updatedCourseSection);
                else
                    //status = updateCourseDAO.updateExistingCourseSectionInfo(updatedCourseSection);
                if (status == 0) {
                    setUpdateStatus("Could not update your submission. Please check your values.");
                    break;
                }
            }
        }
        }
        if (status == 1) {
            setUpdateStatus("All Changes are Saved Successfully!");
        }
        this.setEditable(false);
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

    public String addNewCourse() {
        CourseDAO addCourseDAO = new CourseDAOImpl();
        int rowCount = addCourseDAO.addNewCourse(theCourseModel);
        if (rowCount == 1) {
            updateStatus = "New Course Added Successfully";
        } else {
            updateStatus = "Errors encountered while adding new course. Please try again later.";
        }
        return null;
    }

}
