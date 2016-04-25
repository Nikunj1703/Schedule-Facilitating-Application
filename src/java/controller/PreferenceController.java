/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.CourseBean;
import bean.CoursePreferenceBean;
import bean.DayPreferenceBean;
import bean.ProfessorBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import bean.ScheduleBean;
import bean.ToDoBean;
import dao.CourseDAO;
import dao.CourseDAOImpl;
import dao.PreferencesDAO;
import dao.PreferencesDAOImpl;
import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Gunjan
 */
@ManagedBean(name = "preferenceController")
@SessionScoped

public class PreferenceController implements Serializable{
    
    
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;
    
    private ProfessorBean professorModel;
    private DayPreferenceBean dayPreferenceBean;
    private CoursePreferenceBean coursePreferenceBean;
    private CoursePreferenceBean newCoursePreferenceBean;
    
    
    private PreferencesDAO aPreferencesDAO;
    private CourseDAO aCourseDAO;
    private String courseComments;
    private boolean hasDayPreferences;
    private boolean hasCoursePreferences;
    private boolean hasPreferences;
   
    private boolean editable;
    private String preferenceProfName;
    
    private String interestLevelPref1;
    private String interestLevelPref2;
    private String interestLevelPref3;
    private String interestLevelPref4;
    private String interestLevelPref5;
    
   
    private String preferenceDay;
    private String preferenceTime;
    private String preferenceFinalComment;
    private String preferenceOtherInterest;
    private String labNeededForCourses;
    
    
    private static String statusUpdate;
    
    private ArrayList<CoursePreferenceBean> coursePrefList;
    private String[] daysArray;

    public PreferenceController() {
    professorModel = new ProfessorBean();
    dayPreferenceBean = new DayPreferenceBean();
    coursePreferenceBean = new CoursePreferenceBean();
    newCoursePreferenceBean = new CoursePreferenceBean();
    aPreferencesDAO = new PreferencesDAOImpl();
    aCourseDAO = new CourseDAOImpl();
    hasDayPreferences = false;
    hasCoursePreferences = false;
    coursePrefList = new ArrayList<>();
    hasPreferences = false;    
    editable = false;
    statusUpdate = "";
    }

    public PreferenceController(LoginController loginController, ProfessorBean professorModel, DayPreferenceBean dayPreferenceBean, CoursePreferenceBean coursePreferenceBean, UserDAO aUserDAO, boolean hasDayPreferences, boolean hasCoursePreferences, boolean hasPreferences, ArrayList<CoursePreferenceBean> coursePrefList) {
        this.loginController = loginController;
        this.professorModel = professorModel;
        this.dayPreferenceBean = dayPreferenceBean;
        this.coursePreferenceBean = coursePreferenceBean;
        this.hasDayPreferences = hasDayPreferences;
        this.hasCoursePreferences = hasCoursePreferences;
        this.hasPreferences = hasPreferences;
        this.coursePrefList = coursePrefList;
    }

    
    public void checkPreferences(){
    
        if(hasDayPreferences)
            hasPreferences=true;
        else
            hasPreferences=false;
    }
    
       public void getDayPrefExists() {
           hasDayPreferences = aPreferencesDAO.checkDayPrefExists(loginController.getProfModel());
     }

//       public void getCoursePrefExists() {
//           hasCoursePreferences = aPreferencesDAO.checkCoursePrefExists(loginController.getProfModel());
//     }

    
       public DayPreferenceBean getDayPreferences(){
           daysArray = null;
          if(aPreferencesDAO.checkDayPrefExists(loginController.getProfModel())){
           dayPreferenceBean = aPreferencesDAO.fetchDayPreferences(loginController.getProfModel());
           daysArray = dayPreferenceBean.getDays().replaceAll(" ", "").split(",");//trim this
           hasPreferences = true;
          }
          else{
              hasPreferences=false;
          dayPreferenceBean.setDays("");
          dayPreferenceBean.setPreferredTime("");
          dayPreferenceBean.setFinalComments("");
          dayPreferenceBean.setOtherInterests("");
          dayPreferenceBean.setLastUpdtTS("");
          }
          return null;
       }
    
       public ArrayList<CoursePreferenceBean> getCoursePreferences (){
             ArrayList<CourseBean> cList = aCourseDAO.getAllCourses();
        
          
           for (CourseBean aCourse : cList) {
         
               if(!aPreferencesDAO.checkCoursePrefExists(loginController.getProfModel(),aCourse.getCourse_id())){
          
               coursePreferenceBean.setProfId(loginController.getProfModel().getProfessorId());
               coursePreferenceBean.setCourseId(aCourse.getCourse_id());
               coursePreferenceBean.setLabNeeded(0);
               coursePreferenceBean.setInterestLevel(0);
               coursePreferenceBean.setComments("");
               aPreferencesDAO.addNewCoursePreference(coursePreferenceBean);
        //   coursePreferenceBean = new CoursePreferenceBean(aCourse.getCoursePreferenceId(),loginController.getProfModel().getProfessorId(), aCourse.getCourseId(), aCourse.getLabNeeded(),aCourse.getComments(), aCourse.getInterestLevel());
           }
        }
           coursePrefList = aPreferencesDAO.fetchCoursePreference(loginController.getProfModel());
         
           return null;
       }
    
       public String editAction() {
           statusUpdate="";
        this.setEditable(true);
        return null;
    }

    public String getLabNeededForCourses() {
        return labNeededForCourses;
    }

    public void setLabNeededForCourses(String labNeededForCourses) {
        this.labNeededForCourses = labNeededForCourses;
    }

    public String getCourseComments() {
        return courseComments;
    }

    public void setCourseComments(String courseComments) {
        this.courseComments = courseComments;
    }

    


    public String getInterestLevelPref1() {
        return interestLevelPref1;
    }

    public void setInterestLevelPref1(String interestLevelPref1) {
        this.interestLevelPref1 = interestLevelPref1;
    }

    public String getInterestLevelPref2() {
        return interestLevelPref2;
    }

    public void setInterestLevelPref2(String interestLevelPref2) {
        this.interestLevelPref2 = interestLevelPref2;
    }

    public String getInterestLevelPref3() {
        return interestLevelPref3;
    }

    public void setInterestLevelPref3(String interestLevelPref3) {
        this.interestLevelPref3 = interestLevelPref3;
    }

    public String getInterestLevelPref4() {
        return interestLevelPref4;
    }

    public void setInterestLevelPref4(String interestLevelPref4) {
        this.interestLevelPref4 = interestLevelPref4;
    }

    public String getInterestLevelPref5() {
        return interestLevelPref5;
    }

    public void setInterestLevelPref5(String interestLevelPref5) {
        this.interestLevelPref5 = interestLevelPref5;
    }

    public String getPreferenceDay() {
        return preferenceDay;
    }

    public void setPreferenceDay(String preferenceDay) {
        this.preferenceDay = preferenceDay;
    }

    public String getPreferenceTime() {
        return preferenceTime;
    }

    public void setPreferenceTime(String preferenceTime) {
        this.preferenceTime = preferenceTime;
    }

    public String getPreferenceFinalComment() {
        return preferenceFinalComment;
    }

    public void setPreferenceFinalComment(String preferenceFinalComment) {
        this.preferenceFinalComment = preferenceFinalComment;
    }

    public String getPreferenceOtherInterest() {
        return preferenceOtherInterest;
    }

    public void setPreferenceOtherInterest(String preferenceOtherInterest) {
        this.preferenceOtherInterest = preferenceOtherInterest;
    }

    

    public String stopEditAction() {
        statusUpdate="";
        this.setEditable(false);
        return null;
    }

    public String getPreferenceProfName() {
        return preferenceProfName;
    }

    public void setPreferenceProfName(String preferenceProfName) {
        this.preferenceProfName = preferenceProfName;
    }

    
    
    public String addNewPreference() {

        newCoursePreferenceBean.setProfId(loginController.getProfModel().getProfessorId());
        
        int rowCount = aPreferencesDAO.addNewCoursePreference(newCoursePreferenceBean);
        if (rowCount == 1) {
            statusUpdate = "Course preference added successfully";
              coursePrefList = aPreferencesDAO.fetchCoursePreference(loginController.getProfModel());
        } else {
            statusUpdate ="Errors encountered while adding preference.";
        }
        return null;
    }
    
     public String saveAction() {
         

       CoursePreferenceBean updatedPreferences;
        int courseStatus = 0;
        int dayStatus = 0;
        //get all existing value but set "editable" to false 
        for (CoursePreferenceBean aCourse : coursePrefList) {
          
            updatedPreferences = new CoursePreferenceBean(aCourse.getCoursePreferenceId(),loginController.getProfModel().getProfessorId(), aCourse.getCourseId(), aCourse.getLabNeeded(),aCourse.getComments(), aCourse.getInterestLevel());
            courseStatus = aPreferencesDAO.updateCoursePreferences(updatedPreferences);
            if (courseStatus == 0) {
                statusUpdate = "Could not update your submission. Please check your values.";
                break;
            }
        }
        //11:00 6:30 PM
         String preferredTime = dayPreferenceBean.getFromHour()+":"+dayPreferenceBean.getFromMinute()+" - "+dayPreferenceBean.getToHour()+":"+dayPreferenceBean.getToMinute()+" "+dayPreferenceBean.getToAMPM();
       dayPreferenceBean.setPreferredTime(preferredTime);
        dayPreferenceBean.setProfId(loginController.getProfModel().getProfessorId());
        dayPreferenceBean.setDays(Arrays.toString(daysArray).substring(1, Arrays.toString(daysArray).length()-1));
       
        if (aPreferencesDAO.checkDayPrefExists(loginController.getProfModel())){
            dayStatus = aPreferencesDAO.updateDayPreferences(dayPreferenceBean);
         }else{
           dayStatus = aPreferencesDAO.addDayPreferences(dayPreferenceBean);
        }
        if (courseStatus == 1) {
         if (dayStatus == 1) {
            statusUpdate = "All Changes Saved Successfully!";
         }
        }
        this.setEditable(false);
        //return to current page
        return null;

    }
     
     
       public String logoutAction() {
      professorModel = new ProfessorBean();
    dayPreferenceBean = new DayPreferenceBean();
    coursePreferenceBean = new CoursePreferenceBean();
    newCoursePreferenceBean = new CoursePreferenceBean();
    aPreferencesDAO = new PreferencesDAOImpl();
    aCourseDAO = new CourseDAOImpl();
    hasDayPreferences = false;
    hasCoursePreferences = false;
    coursePrefList = new ArrayList<>();
    hasPreferences = false;    
    editable = false;
    statusUpdate = "";
        return "";
    }
     
     
    /**
     * @return the loginController
     */
    public LoginController getLoginController() {
        return loginController;
    }

    /**
     * @param loginController the loginController to set
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * @return the professorModel
     */
    public ProfessorBean getProfessorModel() {
        return professorModel;
    }

    /**
     * @param professorModel the professorModel to set
     */
    public void setProfessorModel(ProfessorBean professorModel) {
        this.professorModel = professorModel;
    }

    /**
     * @return the dayPreferenceBean
     */
    public DayPreferenceBean getDayPreferenceBean() {
        return dayPreferenceBean;
    }

    /**
     * @param dayPreferenceBean the dayPreferenceBean to set
     */
    public void setDayPreferenceBean(DayPreferenceBean dayPreferenceBean) {
        this.dayPreferenceBean = dayPreferenceBean;
    }

    /**
     * @return the coursePreferenceBean
     */
    public CoursePreferenceBean getCoursePreferenceBean() {
        return coursePreferenceBean;
    }

    /**
     * @param coursePreferenceBean the coursePreferenceBean to set
     */
    public void setCoursePreferenceBean(CoursePreferenceBean coursePreferenceBean) {
        this.coursePreferenceBean = coursePreferenceBean;
    }

    /**
     * @return the hasDayPreferences
     */
    public boolean isHasDayPreferences() {
        return hasDayPreferences;
    }

    /**
     * @param hasDayPreferences the hasDayPreferences to set
     */
    public void setHasDayPreferences(boolean hasDayPreferences) {
        this.hasDayPreferences = hasDayPreferences;
    }

    /**
     * @return the hasCoursePreferences
     */
    public boolean isHasCoursePreferences() {
        return hasCoursePreferences;
    }

    /**
     * @param hasCoursePreferences the hasCoursePreferences to set
     */
    public void setHasCoursePreferences(boolean hasCoursePreferences) {
        this.hasCoursePreferences = hasCoursePreferences;
    }

    /**
     * @return the coursePrefList
     */
    public ArrayList<CoursePreferenceBean> getCoursePrefList() {
        return coursePrefList;
    }

    /**
     * @param coursePrefList the coursePrefList to set
     */
    public void setCoursePrefList(ArrayList<CoursePreferenceBean> coursePrefList) {
        this.coursePrefList = coursePrefList;
    }

    /**
     * @return the hasPreferences
     */
    public boolean isHasPreferences() {
        return hasPreferences;
    }

    /**
     * @param hasPreferences the hasPreferences to set
     */
    public void setHasPreferences(boolean hasPreferences) {
        this.hasPreferences = hasPreferences;
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
     * @return the newCoursePreferenceBean
     */
    public CoursePreferenceBean getNewCoursePreferenceBean() {
        return newCoursePreferenceBean;
    }

    /**
     * @param newCoursePreferenceBean the newCoursePreferenceBean to set
     */
    public void setNewCoursePreferenceBean(CoursePreferenceBean newCoursePreferenceBean) {
        this.newCoursePreferenceBean = newCoursePreferenceBean;
    }

    /**
     * @return the daysArray
     */
    public String[] getDaysArray() {
        return daysArray;
    }

    /**
     * @param daysArray the daysArray to set
     */
    public void setDaysArray(String[] daysArray) {
        this.daysArray = daysArray;
    }

    /**
     * @return the statusUpdate
     */
    public String getStatusUpdate() {
        return statusUpdate;
    }

    /**
     * @param statusUpdate the statusUpdate to set
     */
    public void setStatusUpdate(String statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

  public void findPreference(){
        System.out.println("Professor ID through course preference: "+coursePreferenceBean.getPreferenceProfId());
        System.out.println("Professor ID through day preference: "+dayPreferenceBean.getPreferenceProfId());
        PreferencesDAO aPreferenceDAO = new PreferencesDAOImpl();
        
        preferenceProfName = aPreferenceDAO.findProfName(coursePreferenceBean.getPreferenceProfId());
       
        
        interestLevelPref1 = "Interest Level-1: "+aPreferenceDAO.courseIDByInterestLevel(coursePreferenceBean.getPreferenceProfId(), 1);
        interestLevelPref2 = "Interest Level-2: "+aPreferenceDAO.courseIDByInterestLevel(coursePreferenceBean.getPreferenceProfId(), 2);
        interestLevelPref3 = "Interest Level-3: "+aPreferenceDAO.courseIDByInterestLevel(coursePreferenceBean.getPreferenceProfId(), 3);
        interestLevelPref4 = "Interest Level-4: "+aPreferenceDAO.courseIDByInterestLevel(coursePreferenceBean.getPreferenceProfId(), 4);
        interestLevelPref5 = "Interest Level-5: "+aPreferenceDAO.courseIDByInterestLevel(coursePreferenceBean.getPreferenceProfId(), 5);
        
        preferenceDay = "Days Prefered: "+aPreferenceDAO.findDayPreferenceByProfId(dayPreferenceBean.getPreferenceProfId());
        preferenceTime = "Time Prefered: "+aPreferenceDAO.findTimePreferenceByProfId(dayPreferenceBean.getPreferenceProfId());
        preferenceOtherInterest = "Other Interests: "+aPreferenceDAO.findOtherInterestPreferenceByProfId(dayPreferenceBean.getPreferenceProfId());
        preferenceFinalComment = "Final Comments: "+aPreferenceDAO.findFinalCommentPreferenceByProfId(dayPreferenceBean.getPreferenceProfId());
        courseComments = aPreferenceDAO.courseCommentsForProfessor(dayPreferenceBean.getPreferenceProfId());
        labNeededForCourses = "Lab Needed for Courses: "+aPreferenceDAO.labNeededForProfessor(coursePreferenceBean.getPreferenceProfId());
        
        System.out.println("preferenceProfName: "+preferenceProfName);
    }

}
