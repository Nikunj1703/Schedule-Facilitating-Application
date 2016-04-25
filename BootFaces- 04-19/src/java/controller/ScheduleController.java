/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AllScheduleDAO;
import dao.AllScheduleDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import bean.AllScheduleBean;
import bean.ClassRoomReportBean;
import bean.ProfessorBean;
import bean.ReportBean;
import bean.RoomBean;
import bean.ScheduleBean;
import bean.ToDoBean;
import dao.CourseDAO;
import dao.CourseDAOImpl;
import dao.ReportDAO;
import dao.ReportDAOImpl;
import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Gunjan
 */
@ManagedBean
@SessionScoped

public class ScheduleController implements Serializable {

    ScheduleBean scheduleModel;
    ToDoBean toDoModel;
    //AllScheduleBean theAllScheduleBean;
    private ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    private ArrayList<ScheduleBean> scheduleRows;
    private ArrayList<ScheduleBean> scheduleRowsToModify;
    ArrayList<Integer> hourList;
    ArrayList<String> minuteList;
    private String updateStatus;
    private String updateStatusSaveAs;
    private ArrayList<ProfessorBean> professorList;
    private String[] days;
    private ArrayList<RoomBean> roomList;
    private AllScheduleBean theAllScheduleModel = new AllScheduleBean();
    private List<ReportBean> reportList2;
    private List<ReportBean> reportList3;
    private List<ClassRoomReportBean> mwReportList;
    private List<ClassRoomReportBean> mwLabReportList;
    private List<ClassRoomReportBean> trReportList;
    private List<ClassRoomReportBean> trLabReportList;
    private List<String> lectureList;
    private List<String> labList;

    int professorId;
    int roomId;
    int dayIterator = 0;
    int dayIterator2 = 0;
    private String termUpdate;
    private String yearUpdate;
    int classTimeIterator = 0;
    StringBuilder classTimeBuilder = new StringBuilder();
    private int numOfSections;
    private CourseDAO aCourseDAO = new CourseDAOImpl();
    private String newCourseName;

    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;

    @ManagedProperty(value = "#{reportController}")
    private ReportController reportController;

    public ArrayList<ProfessorBean> getProfessorList() {
        ProfessorController professorController = new ProfessorController();
        professorList = (ArrayList) professorController.getProfessorList();
        return professorList;
    }

    public ArrayList<RoomBean> getRoomList() {
        RoomController roomController = new RoomController();
        roomList = (ArrayList) roomController.getRoomList();
        return roomList;
    }

    public int getNumOfSections() {
        return numOfSections;
    }

    public void setNumOfSections(int numOfSections) {
        this.numOfSections = numOfSections;
    }

    public String getNewCourseName() {
        return newCourseName;
    }

    public void setNewCourseName(String newCourseName) {
        this.newCourseName = newCourseName;
    }

    /**
     * @return the toDoModel
     */
    public ToDoBean getToDoModel() {
        return toDoModel;
    }

    /**
     * @param toDoModel the toDoModel to set
     */
    public void setToDoModel(ToDoBean toDoModel) {
        this.toDoModel = toDoModel;
    }

    public String getTermUpdate() {
        return termUpdate;
    }

    public void setTermUpdate(String termUpdate) {
        this.termUpdate = termUpdate;
    }

    public String getYearUpdate() {
        return yearUpdate;
    }

    public void setYearUpdate(String yearUpdate) {
        this.yearUpdate = yearUpdate;
    }

    public void setDays(String[] days) {
        scheduleRows.get(dayIterator).setDays(Arrays.toString(days));
        dayIterator++;
        this.days = days;
    }

    /**
     * @return the scheduleRows
     */
    public ArrayList<ScheduleBean> getScheduleRows() {
        return scheduleRows;
    }

    public ScheduleController(ScheduleBean scheduleModel, ToDoBean toDoModel, ArrayList<ScheduleBean> scheduleRows) {
        this.scheduleModel = scheduleModel;
        this.toDoModel = toDoModel;
        this.scheduleRows = scheduleRows;
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

    public ReportController getReportController() {
        return reportController;
    }

    public void setReportController(ReportController reportController) {
        this.reportController = reportController;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * @param scheduleRows the scheduleRows to set
     */
    public void setScheduleRows(ArrayList<ScheduleBean> scheduleRows) {
        this.scheduleRows = scheduleRows;
    }

    public ScheduleController() {
        scheduleModel = new ScheduleBean();
        toDoModel = new ToDoBean();
    }

    public ScheduleBean getTheScheduleModel() {//earlier it was getTheAllScheduleModel
        return scheduleModel;
    }

    public void setTheScheduleModel(ScheduleBean scheduleBean) {
        this.scheduleModel = scheduleModel;
    }

    public ArrayList<ScheduleBean> getSchedule() {
        ReportDAO aReport = new ReportDAOImpl();
        System.out.println("In schdl cntrl");
        scheduleRows = scheduleDAO.getScheduleForReview(loginController.getToDoModel());
        reportController.setAllScheduleId(loginController.getToDoModel().getAllScheduleId());
        reportList2 = reportController.getReportList2();
        reportList3 = reportController.getReportList3();
        lectureList = reportController.getLectureList();
        labList = reportController.getLabList();
        mwReportList = reportController.getMwReportList();
        mwLabReportList = reportController.getMwLabReportList();
        trReportList = reportController.getTrReportList();
        trLabReportList = reportController.getTrLabReportList();

        //return scheduleRows;
        return null;
    }

    public ArrayList<ScheduleBean> getIntialScheduleData(AllScheduleBean theAllScheduleModel) {
        int allScheduleId = theAllScheduleModel.getAll_Sch_Id();

        //int allScheduleId = 1;
        scheduleRows = scheduleDAO.getCourseSections(allScheduleId);
        return scheduleRows;
    }

    public ArrayList<ScheduleBean> getScheduleBeanListForModify(AllScheduleBean theAllScheduleModel) {

        int allScheduleId = theAllScheduleModel.getAll_Sch_Id();

        //int allScheduleId = 1;
        this.scheduleRowsToModify = scheduleDAO.findScheduleBeanForModifyFromDB(allScheduleId);
        return scheduleRowsToModify;
    }

    public String findSectionCode(int sectionID) {
        String sectionCode = scheduleDAO.findSectionNumber(sectionID);
        return sectionCode;
    }

    public ArrayList<Integer> findHours() {
        hourList = new ArrayList<Integer>();
        for (int i = 1; i <= 12; i++) {
            hourList.add(i);
        }
        return hourList;
    }

    public ArrayList<String> findMinutes() {
        minuteList = new ArrayList<String>();
        String first = "00";
        String second = "05";
        int i = 10;
        minuteList.add(first);
        minuteList.add(second);
        while (i < 60) {
            minuteList.add(i + "");
            i = i + 5;
        }
        return minuteList;
    }

    public String getUpdateStatusSaveAs() {
        return updateStatusSaveAs;
    }

    public void setUpdateStatusSaveAs(String updateStatusSaveAs) {
        this.updateStatusSaveAs = updateStatusSaveAs;
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

    public void saveAction() {
        //int allScheduleId = theAllScheduleModel.getAllScheduleId();

        System.out.println("New Schedule Row: " + scheduleRows.toString());

        ScheduleDAO addScheduleDAO = new ScheduleDAOImpl();
        int status = 0;
        //get all existing value but set "editable" to false 
        for (ScheduleBean aSchedule : scheduleRows) {
            status = addScheduleDAO.addScheduleRow(aSchedule);
            if (status == 0) {
                setUpdateStatus("Could not update your submission. Please check your values.");
                break;
            }
        }
        if (status == 1) {
            setUpdateStatus("All Changes are Saved Successfully!");
        }

        scheduleRows = null;

        //return "";
        dayIterator = 0;
        dayIterator2 = 0;
    }

    public void updateAction() {
        ScheduleDAO addScheduleDAO = new ScheduleDAOImpl();
        int status = 0;
        //get all existing value but set "editable" to false 
        for (ScheduleBean aSchedule : scheduleRows) {
            status = addScheduleDAO.updateScheduleRow(aSchedule);
            if (status == 0) {
                setUpdateStatus("Could not update your submission. Please check your values.");
                break;
            }
        }
        if (status == 1) {
            setUpdateStatus("All Changes are Updated Successfully!");
        }

        scheduleRows = null;

        //return "";
        dayIterator = 0;
        dayIterator2 = 0;
    }

    public void saveAsNewAction() {
        AllScheduleDAO addAllScheduleIdDAO = new AllScheduleDAOImpl();
        theAllScheduleModel = addAllScheduleIdDAO.addNewAllScheduleID(theAllScheduleModel, termUpdate, yearUpdate);
        if (theAllScheduleModel != null) {
            ScheduleDAO addScheduleDAO = new ScheduleDAOImpl();
            CourseDAO findCourseIdcourseDAO = new CourseDAOImpl();
            int status = 0;
            //get all existing value but set "editable" to false 
            for (ScheduleBean aSchedule : scheduleRows) {
                aSchedule.setAllScheduleId(theAllScheduleModel.getAll_Sch_Id());
                String sectionNumber = addScheduleDAO.findSectionNumber(aSchedule.getSectionId());
                int courseId = findCourseIdcourseDAO.findCourseId(aSchedule.getCourseCode());

                int statusOfAddCourseSection = addScheduleDAO.insertCourseSectionForSaveAsSchedule(aSchedule, sectionNumber, courseId);
                //We need to add a statement to insert into COURSE_SECTION table.
                if (statusOfAddCourseSection == 1) {
                    status = addScheduleDAO.addScheduleRow(aSchedule);
                }
                if (status == 0) {
                    setUpdateStatusSaveAs("Could not create a Schedule. Please check your values.");
                    break;
                }
            }
            if (status == 1) {
                setUpdateStatusSaveAs("Schedule Saved As Successfully!");
            }

            scheduleRows = null;

            //return "";
            dayIterator = 0;
            dayIterator2 = 0;

        } else {
            updateStatus = "Errors encountered while adding new course. Please try again later.";
        }

    }

    public void addNewCourseForUpdateSchedule() {

    }

    public String[] getDays() {
        if (dayIterator2 >= scheduleRows.size()) {
            dayIterator2 = 0;
        }
        String[] days = scheduleRows.get(dayIterator2).getDays().split(", ");
        dayIterator2++;
        return days;
    }

    public ScheduleBean getScheduleModel() {
        return scheduleModel;
    }

    public List<ReportBean> getReportList2() {
        return reportList2;
    }

    public void setReportList2(List<ReportBean> reportList2) {
        this.reportList2 = reportList2;
    }

    public List<ReportBean> getReportList3() {
        return reportList3;
    }

    public void setReportList3(List<ReportBean> reportList3) {
        this.reportList3 = reportList3;
    }

    public List<ClassRoomReportBean> getMwReportList() {
        return mwReportList;
    }

    public void setMwReportList(List<ClassRoomReportBean> mwReportList) {
        this.mwReportList = mwReportList;
    }

    public List<ClassRoomReportBean> getMwLabReportList() {
        return mwLabReportList;
    }

    public void setMwLabReportList(List<ClassRoomReportBean> mwLabReportList) {
        this.mwLabReportList = mwLabReportList;
    }

    public List<ClassRoomReportBean> getTrReportList() {
        return trReportList;
    }

    public void setTrReportList(List<ClassRoomReportBean> trReportList) {
        this.trReportList = trReportList;
    }

    public List<ClassRoomReportBean> getTrLabReportList() {
        return trLabReportList;
    }

    public void setTrLabReportList(List<ClassRoomReportBean> trLabReportList) {
        this.trLabReportList = trLabReportList;
    }

    public List<String> getLectureList() {
        return lectureList;
    }

    public void setLectureList(List<String> lectureList) {
        this.lectureList = lectureList;
    }

    public List<String> getLabList() {
        return labList;
    }

    public void setLabList(List<String> labList) {
        this.labList = labList;
    }

}
