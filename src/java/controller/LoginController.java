/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import bean.*;
import dao.CourseDAO;
import dao.CourseDAOImpl;
import dao.ProfessorDAO;
import dao.ProfessorDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Gunjan
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String response;
    private User userModel;
    private ToDoBean toDoModel;
    private ReviewCommentBean reviewCommentModel;
    private ProfessorBean profModel;
    private int count;
    private boolean loginDisabled;

    private static String statusUpdate = "";

    UserDAO aUserDAO = new UserDAOImpl();
    ProfessorDAO aProfDAO = new ProfessorDAOImpl();
    CourseDAO aCourseDAO = new CourseDAOImpl();
    private boolean editable;

    private boolean hasToDoItems;
    private boolean hasReviewComments;
    private boolean hasCoursePrefs;
    private boolean hasDayPrefs;

    public LoginController(User userModel, ToDoBean toDoModel, ReviewCommentBean reviewCommentModel, ProfessorBean profModel, boolean editable, boolean hasToDoItems, boolean hasReviewComments, boolean hasCoursePrefs, boolean hasDayPrefs) {
        this.userModel = userModel;
        this.toDoModel = toDoModel;
        this.reviewCommentModel = reviewCommentModel;
        this.profModel = profModel;
        this.editable = editable;
        this.hasToDoItems = hasToDoItems;
        this.hasReviewComments = hasReviewComments;
        this.hasCoursePrefs = hasCoursePrefs;
        this.hasDayPrefs = hasDayPrefs;
    }

    public LoginController() {
        userModel = new User();
        toDoModel = new ToDoBean();
        profModel = new ProfessorBean();
        reviewCommentModel = new ReviewCommentBean();
        this.editable = false;
        count = 1;
        response = "";
    }

    public String countAttempt(ComponentSystemEvent event) {
        String navi = null;

        if (count > 3) {

            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("access-denied?faces-redirect=true");

        }

        return navi;
    }

    public String authenticate() {
        new LoginController();
        response = "";
        statusUpdate = "";

        String uid = userModel.getUlid();
        String pwd = userModel.getPassword();
        if (uid.length() == 0) {

            response = "Please enter your ULID to login";
            System.out.println("in authenticateUser()1: loginValidaton=" + response);
            return "";
        } else if (pwd.length() == 0) {

            response = "Please enter your password.";
            System.out.println("in authenticateUser()2: loginValidaton=" + response);
            return "";
        } else {
            //user has entered a ulid and pwd, now check db
            response = "";

        if (count < 3) {
            int accessLevel;
            accessLevel = aUserDAO.authenticate(userModel);

            if (accessLevel == 1) {
                //admin
//                    count++;
                return "adminLandingPage.xhtml";
            } else if (accessLevel == 0) {
                // Faculty
//                    count++;

                //get Faculty name from Professor table
                profModel = aProfDAO.getProfessor(userModel.getUlid());

                //check if the prof has a to do item
                hasToDoItems = aUserDAO.checkToDoItemExists(profModel);
                if (hasToDoItems) {
                    //  if true only then get todo item else set Status UPdate to no new items    
                    toDoModel = aUserDAO.fetchToDoItems(profModel);
                    //check if the task is in progress and has comments
                    hasReviewComments = aUserDAO.checkCommentsExists(profModel, toDoModel);
                    if (hasReviewComments) {
                        //fetch review comments
                        setReviewCommentModel(aUserDAO.fetchComments(profModel, toDoModel));
                    } else {
                        reviewCommentModel.setAllScheduleId(toDoModel.getAllScheduleId());
                        reviewCommentModel.setCommentText("");
                        setReviewCommentModel(this.reviewCommentModel);
                    }

                } else {
                    statusUpdate = "You do not have any To Do Items.\n If you would like to set up your preferences, click on the preferences tab.";
                }

                return "facultyLandingPage.xhtml";

            } else if (accessLevel == -1) {
                // DB error
                response = "Something went wrong. Please try again after some time.";
                return "login.xhtml";
            } else if (accessLevel == -2) {
                //Non existing user
                return "signUp.xhtml";
            } else if (accessLevel == -3) {
                //invalid username/pwd combo
                count++;
                response = "Username and/or Password incorrect. Please try again.";
                return "";
            } else {
                return "";
            }
        } else //user exceeded unsuccessful login attempts
        {
            response = "You have exceeded your limit of 3 unsuccessful login attempts. "
                    + "Your account has been temporarily locked. Please contact Admin";
            return disableUser();
        }
        }

    }

    public String disableUser() {
//        loggedIn = false;
        userModel.setUlid("");
        userModel.setPassword("");
        setLoginDisabled(true);
        return "login.xhtml";
    }

    public void updateToDoStatus(int status) {
        if (hasToDoItems) {
            toDoModel.setStatus(status);// IN Progress
            boolean result = aUserDAO.updateToDoStatusDB(toDoModel, profModel);
            if (result == true) {
//            if(toDoModel.getStatus()==2)
//                 statusUpdate = "Status Updated: Not Started" + toDoModel.getStatus();
//            else if(toDoModel.getStatus()==3)
//                  statusUpdate = "Status Updated: In Progress" + toDoModel.getStatus();
//             else if(toDoModel.getStatus()==4)
//                  statusUpdate = "Status Updated: Completed" + toDoModel.getStatus();
            } else {
                statusUpdate = "Error updating Status";
            }
        }
    }

    public String editAction() {
        this.setEditable(true);
        return null;
    }

    public String stopEditAction() {
        this.setEditable(false);
        return null;
    }

    public String submitReviewCommentsAction() {

        Boolean result;
        if (hasReviewComments) {
            result = aUserDAO.updateComments(reviewCommentModel, toDoModel);
        } else {
            result = aUserDAO.addComments(reviewCommentModel, toDoModel);
        }

        if (result) {
            statusUpdate = "Comments added successfully";
        } else {
            statusUpdate = "Errors encountered while adding comments.";
        }
        return null;
    }

    public String logoutAction() {
        userModel = new User();
        toDoModel = new ToDoBean();
        profModel = new ProfessorBean();
        reviewCommentModel = new ReviewCommentBean();
        this.editable = false;
        return "login.xhtml";
    }

    public User getUserModel() {
        return userModel;
    }

    public void setUserModel(User userBean) {
        this.userModel = userBean;
    }

    public ToDoBean getToDoModel() {
        return toDoModel;
    }

    public void setToDoModel(ToDoBean toDoModel) {
        this.toDoModel = toDoModel;
    }

    public ProfessorBean getProfModel() {
        return profModel;
    }

    public void setProfModel(ProfessorBean profModel) {
        this.profModel = profModel;
    }

    /**
     * @return the reviewCommentModel
     */
    public ReviewCommentBean getReviewCommentModel() {
        return reviewCommentModel;
    }

    /**
     * @param reviewCommentModel the reviewCommentModel to set
     */
    public void setReviewCommentModel(ReviewCommentBean reviewCommentModel) {
        this.reviewCommentModel = reviewCommentModel;
    }

    /**
     * @return the hasToDoItems
     */
    public boolean isHasToDoItems() {
        return hasToDoItems;
    }

    /**
     * @param hasToDoItems the hasToDoItems to set
     */
    public void setHasToDoItems(boolean hasToDoItems) {
        this.hasToDoItems = hasToDoItems;
    }

    /**
     * @return the hasReviewComments
     */
    public boolean isHasReviewComments() {
        return hasReviewComments;
    }

    /**
     * @param hasReviewComments the hasReviewComments to set
     */
    public void setHasReviewComments(boolean hasReviewComments) {
        this.hasReviewComments = hasReviewComments;
    }

    /**
     * @return the hasCoursePrefs
     */
    public boolean isHasCoursePrefs() {
        return hasCoursePrefs;
    }

    /**
     * @param hasCoursePrefs the hasCoursePrefs to set
     */
    public void setHasCoursePrefs(boolean hasCoursePrefs) {
        this.hasCoursePrefs = hasCoursePrefs;
    }

    /**
     * @return the hasDayPrefs
     */
    public boolean isHasDayPrefs() {
        return hasDayPrefs;
    }

    /**
     * @param hasDayPrefs the hasDayPrefs to set
     */
    public void setHasDayPrefs(boolean hasDayPrefs) {
        this.hasDayPrefs = hasDayPrefs;
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

    public String getResponse() {
        return response;
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

    public boolean isLoginDisabled() {
        return loginDisabled;
    }

    public void setLoginDisabled(boolean loginDisabled) {
        this.loginDisabled = loginDisabled;
    }
}
