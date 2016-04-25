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
import bean.ConflictBean;
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
import java.util.HashMap;
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
    private ConflictBean aConflictBean = new ConflictBean();
    private ArrayList<ScheduleBean> conflictScheduleBeanList = new ArrayList<ScheduleBean>();
    private ArrayList<ScheduleBean> finalConflictsList = new ArrayList<ScheduleBean>();
    private String conflictResultScheduleIds;
    private String finalConflictResult;
    private String noConflictMsg = "";
    private String dayConflictStr = "";
    
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

    public String getNoConflictMsg() {
        return noConflictMsg;
    }

    public void setNoConflictMsg(String noConflictMsg) {
        this.noConflictMsg = noConflictMsg;
    }

    public ArrayList<RoomBean> getRoomList() {
        RoomController roomController = new RoomController();
        roomList = (ArrayList) roomController.getRoomList();
        return roomList;
    }

    public String getDayConflictStr() {
        return dayConflictStr;
    }

    public void setDayConflictStr(String dayConflictStr) {
        this.dayConflictStr = dayConflictStr;
    }

    public String getConflictResultScheduleIds() {
        return conflictResultScheduleIds;
    }

    public void setConflictResultScheduleIds(String conflictResultScheduleIds) {
        this.conflictResultScheduleIds = conflictResultScheduleIds;
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

    public ArrayList<ScheduleBean> getConflictScheduleBeanList() {
        return conflictScheduleBeanList;
    }

    public void setConflictScheduleBeanList(ArrayList<ScheduleBean> conflictScheduleBeanList) {
        this.conflictScheduleBeanList = conflictScheduleBeanList;
    }

    public void triggerConflicts(){
        finalConflictsList = getFinalConflictsList();
    }
    
    
    public void findFinalConflicts(int allScheduleId){
    
    finalConflictsList = scheduleDAO.getConflictingSchedule(allScheduleId, finalConflictResult);
    String tempArr[] = null;
    if(finalConflictResult!=null && finalConflictResult.length() >0){
        //181, 182
        tempArr = finalConflictResult.split(", ");
    }
    if(tempArr!=null && tempArr.length > 1){
        noConflictMsg = "";
        finalConflictsList = scheduleDAO.getConflictingSchedule(allScheduleId, finalConflictResult);
    }else{
        noConflictMsg = "No Conflict Found!";
    }
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

    public ConflictBean getaConflictBean() {
        return aConflictBean;
    }

    public void setaConflictBean(ConflictBean aConflictBean) {
        this.aConflictBean = aConflictBean;
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
        int schId = 0;
        //get all existing value but set "editable" to false 
        for (ScheduleBean aSchedule : scheduleRows) {
            status = addScheduleDAO.addScheduleRow(aSchedule);
            schId = addScheduleDAO.findMaxScheduleId();
            aSchedule.setScheduleId(schId);
            if (status == 0) {
                setUpdateStatus("Could not update your submission. Please check your values.");
                break;
            }
        }
        if (status == 1) {
            setUpdateStatus("All Changes are Saved Successfully!");
        }
        
        conflictScheduleBeanList = scheduleRows;
        conflictChecker();
       findFinalConflicts(scheduleRows.get(0).getAllScheduleId());
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
        conflictScheduleBeanList = scheduleRows;
         conflictChecker();//Delete this line
        findFinalConflicts(scheduleRows.get(0).getAllScheduleId());
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
            conflictScheduleBeanList = scheduleRows;
                conflictChecker();//Delete this line
        findFinalConflicts(scheduleRows.get(0).getAllScheduleId());
            scheduleRows = null;

            //return "";
            dayIterator = 0;
            dayIterator2 = 0;
         

        } else {
            updateStatus = "Errors encountered while adding new course. Please try again later.";
        }
        

    }
    
    public String formatDays(String days){
        //"[M,T]"
        days = days.substring(1, days.length()-1);
        days = days.replaceAll(", ", "");
        days = days.trim();
        return days;
    }
    
    public void conflictChecker(){
        String tempConflictString = "";
        String finalConflictSchIds = "";
        for(int i=0;i<conflictScheduleBeanList.size()-1;i++){
            for(int j=i+1;j<conflictScheduleBeanList.size();j++){
                aConflictBean.setFirstScheduleBean(conflictScheduleBeanList.get(i));
                aConflictBean.setSecondScheduleBean(conflictScheduleBeanList.get(j));
                conflictResultScheduleIds = checkConflictByDays(aConflictBean);
                if(conflictResultScheduleIds.length()!=0)
                    tempConflictString+=conflictResultScheduleIds+", ";
                System.out.println("conflictResultScheduleIds = "+conflictResultScheduleIds);
            }
        }
        String daysAndRoomsConflictResult = "";
        if(tempConflictString.length() != 0){
                    tempConflictString = tempConflictString.substring(0, tempConflictString.length()-2);
                    daysAndRoomsConflictResult = checkConflictByRoom(tempConflictString, aConflictBean);
        }
        
        
        //Resolve conflict by time
        if(daysAndRoomsConflictResult.length()!=0){
            finalConflictSchIds = checkConflictByTime(daysAndRoomsConflictResult, aConflictBean);
        }
        System.out.println("finalConflictSchIds: "+finalConflictSchIds);
        finalConflictResult = finalConflictSchIds;
    }
    
    public String checkConflictByTime(String daysAndRoomsConflictResult, ConflictBean aConflictBean){
        String tempClassTimeConflict = "";
        for(int i=0;i<conflictScheduleBeanList.size()-1;i++){
            for(int j=i+1;j<conflictScheduleBeanList.size();j++){
                aConflictBean.setFirstScheduleBean(conflictScheduleBeanList.get(i));
                aConflictBean.setSecondScheduleBean(conflictScheduleBeanList.get(j));
                //1:00 - 1:00 PM
                String firstClassTime = aConflictBean.getFirstScheduleBean().getFromHour()+":"+aConflictBean.getFirstScheduleBean().getFromMinute()+" - "+
                        aConflictBean.getFirstScheduleBean().getToHour()+":"+aConflictBean.getFirstScheduleBean().getToMinute()+" "+aConflictBean.getFirstScheduleBean().getToAMPM();
                
                String secondClassTime = aConflictBean.getSecondScheduleBean().getFromHour()+":"+aConflictBean.getSecondScheduleBean().getFromMinute()+" - "+
                        aConflictBean.getSecondScheduleBean().getToHour()+":"+aConflictBean.getSecondScheduleBean().getToMinute()+" "+aConflictBean.getSecondScheduleBean().getToAMPM();
                    
                if(firstClassTime.equals(secondClassTime)){
                        tempClassTimeConflict+=aConflictBean.getFirstScheduleBean().getScheduleId()+", "+aConflictBean.getSecondScheduleBean().getScheduleId()+", ";
                    }
            }
        }
        
        if(tempClassTimeConflict.length() != 0){
                    tempClassTimeConflict = tempClassTimeConflict.substring(0, tempClassTimeConflict.length()-2);
                    return commonFromTwoStringArr(daysAndRoomsConflictResult.trim().split(", "),tempClassTimeConflict.trim().split(", "));
        }
        return "";
    }
    
    public String checkConflictByRoom(String duplicateConflictString, ConflictBean aConflictBean){
        String uniqueConflictString = makeUniqueValues(duplicateConflictString);
        System.out.println("uniqueConflictString = "+uniqueConflictString);
        String[] conflictSchIdStr = uniqueConflictString.trim().split(", ");
        String conflictSchIdsForRooms = "";
        for(int i=0;i<conflictScheduleBeanList.size()-1;i++){
            for(int j=i+1;j<conflictScheduleBeanList.size();j++){
                aConflictBean.setFirstScheduleBean(conflictScheduleBeanList.get(i));
                aConflictBean.setSecondScheduleBean(conflictScheduleBeanList.get(j));
                if(aConflictBean.getFirstScheduleBean().getRoomId() == aConflictBean.getSecondScheduleBean().getRoomId()){
                    conflictSchIdsForRooms+=aConflictBean.getFirstScheduleBean().getScheduleId()+", "+aConflictBean.getSecondScheduleBean().getScheduleId()+", ";
                }
            }
        }
        //129, 130, 130, 131, 131, 132
        if(conflictSchIdsForRooms.length()!=0){
            String uniqueConflictSchIdsForRooms = makeUniqueValues(conflictSchIdsForRooms);
            String[] uniqueConflictSchIdsForRoomsArr = uniqueConflictSchIdsForRooms.trim().split(", ");
            return commonFromTwoStringArr(conflictSchIdStr, uniqueConflictSchIdsForRoomsArr);
        }
        //Move this to top method
        return "";
    }
    
    public String commonFromTwoStringArr(String[] sArr1, String[] sArr2){
        String commonResult = "";
        if(sArr1.length < sArr2.length){
            String[] temp = sArr2;
            sArr2 = sArr1;
            sArr1 = temp;
        }
        
        for(int i=0;i<sArr1.length;i++){
            for(int j=0;j<sArr2.length;j++){
                if(sArr1[i].equals(sArr2[j])){
                    commonResult += sArr1[i] +", ";
                }
            }
        }
        if(commonResult.length()!=0)
            commonResult = commonResult.substring(0, commonResult.length()-2);
        
        System.out.println("commonResult: "+commonResult);
        return commonResult;
    }
    
    public String makeUniqueValues(String tempConflictString){
        //129, 130, 129,
//       if(tempConflictString.length()!=0)
//            tempConflictString = tempConflictString.substring(0, tempConflictString.length()-2);
       //129, 130, 129
       
       String[] tempConflictArr = tempConflictString.split(", ");
       HashMap<Integer,Integer> uniqueHash = new HashMap<Integer,Integer>();
       String returnStr = "";
       for(int i=0;i<tempConflictArr.length;i++){
           if(!uniqueHash.containsKey(Integer.parseInt(tempConflictArr[i]))){
               returnStr += tempConflictArr[i]+", ";
               uniqueHash.put(Integer.parseInt(tempConflictArr[i]), 1);
           }
       }
       
       if(returnStr.length()!=0)
            returnStr = returnStr.substring(0, returnStr.length()-2);
       return returnStr;
    }
    
    public String checkConflictByDays(ConflictBean aConflictBean){
        if(compareDaysTemp(formatDays(aConflictBean.getFirstScheduleBean().getDays()).split(""), formatDays(aConflictBean.getSecondScheduleBean().getDays()).split(""))){
            //conflict
            return aConflictBean.getFirstScheduleBean().getScheduleId()+", "+aConflictBean.getSecondScheduleBean().getScheduleId();
        }
        return "";
    }
    
    public boolean compareDaysTemp(String[] daySeq1, String[] daySeq2){
        if(daySeq1.length < daySeq2.length){
            String[] temp = daySeq2;
            daySeq2 = daySeq1;
            daySeq1 = temp;
        }
        for(int i=0;i<daySeq1.length;i++){
            for(int j=0;j<daySeq2.length;j++){
                if(daySeq1[i].equals(daySeq2[j])){
                    return true;
                }
            }
        }
        return false;
    }

    public void addNewCourseForUpdateSchedule() {

    }

    public String[] getDays() {
        if (dayIterator2 >= scheduleRows.size()) {
            dayIterator2 = 0;
        }
        String[] days = scheduleRows.get(dayIterator2).getDays().split("");
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

    /**
     * @return the finalConflictsList
     */
    public ArrayList<ScheduleBean> getFinalConflictsList() {
        return finalConflictsList;
    }

    /**
     * @param finalConflictsList the finalConflictsList to set
     */
    public void setFinalConflictsList(ArrayList<ScheduleBean> finalConflictsList) {
        this.finalConflictsList = finalConflictsList;
    }

    /**
     * @return the finalConflictResult
     */
    public String getFinalConflictResult() {
        return finalConflictResult;
    }

    /**
     * @param finalConflictResult the finalConflictResult to set
     */
    public void setFinalConflictResult(String finalConflictResult) {
        this.finalConflictResult = finalConflictResult;
    }
    
    
    


}
