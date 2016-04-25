/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.AllScheduleBean;
import bean.ClassRoomReportBean;
import bean.ExportBean;
import bean.ReportBean;
import dao.ReportDAO;
import dao.ReportDAOImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MinuRachel
 */
@ManagedBean
@SessionScoped
public class ReportController {

    private ReportBean theReportModel;
    private int allScheduleId;
    private List<ReportBean> reportList1;
    private List<ReportBean> reportList2;
    private List<ReportBean> reportList3;
    private List<ClassRoomReportBean> mwReportList;
    private List<ClassRoomReportBean> mwLabReportList;
    private List<ClassRoomReportBean> trReportList;
    private List<ClassRoomReportBean> trLabReportList;
    private List<String> lectureList;
    private List<String> labList;
    private List<ExportBean> exportList;

    public ReportController() {
        theReportModel = new ReportBean();
        theReportModel.setDays("M-W-F");

    }

    public ReportBean getTheReportModel() {
        return theReportModel;
    }

    public void setTheReportModel(ReportBean theReportModel) {
        this.theReportModel = theReportModel;
    }

    public int getAllScheduleId() {
        return allScheduleId;
    }

    public void setAllScheduleId(int allScheduleId) {
        this.allScheduleId = allScheduleId;
    }

    public void findAllScheduleId(AllScheduleBean theAllScheduleModel) {
        this.allScheduleId = theAllScheduleModel.getAll_Sch_Id();
    }

    public List<ReportBean> getReportList1() {
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session
        ReportDAO aReportList1 = new ReportDAOImpl();
        reportList1 = aReportList1.populateCourseSectionReport(allSchId);
        return reportList1;
    }

    public void setReportList1(List<ReportBean> reportList1) {
        this.reportList1 = reportList1;
    }

    public List<ReportBean> getReportList2() {
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session
        ReportDAO aReportList2 = new ReportDAOImpl();
        reportList2 = aReportList2.populateInstructorReport(allSchId);
        return reportList2;
    }

    public void setReportList2(List<ReportBean> reportList2) {
        this.reportList2 = reportList2;
    }

    public List<ReportBean> getReportList3() {
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session
        ReportDAO aReportList3 = new ReportDAOImpl();
        reportList3 = aReportList3.populateTimeTableReport(allSchId);
        return reportList3;
    }

    public void setReportList3(List<ReportBean> reportList3) {
        this.reportList3 = reportList3;
    }

    public List<ClassRoomReportBean> getMwReportList() {
        List<ClassRoomReportBean> tempMWReportList = new ArrayList<>();
        int allSchId = this.allScheduleId;//this should be replaced with the allSchId that is passed in the session

        ReportDAO aMWReportList = new ReportDAOImpl();

        ArrayList<ReportBean> mwClassReportList = aMWReportList.findMondayWednesdayClassesReport(allSchId);
        ArrayList<String> mwTimeSlots = aMWReportList.findMondayWednesdayLectureTimeSlots(allSchId);
        ClassRoomReportBean aClassRoomReport;

        for (String mwTimeSlot : mwTimeSlots) {
            HashMap<String, String> timeSlotMap = new HashMap<>();

            for (ReportBean mwClassReport : mwClassReportList) {

                if (mwTimeSlot.equalsIgnoreCase(mwClassReport.getClassTime())) {
                    for (int i = 0; i < lectureList.size(); i++) {
                        if (mwClassReport.getRoomName().equalsIgnoreCase(lectureList.get(i))) {
                            String data = null;
                            if (timeSlotMap.containsKey(mwClassReport.getRoomName())) {
                                data = timeSlotMap.get(mwClassReport.getRoomName());
                                data += "<br/>" + mwClassReport.getProfessorName() + "<br/>"
                                        + mwClassReport.getCourse_code() + "-" + mwClassReport.getSection_number() + "<br/>"
                                        + mwClassReport.getDays() + "<br/>";
                            } else {
                                data = "<br/>" + mwClassReport.getProfessorName() + "<br/>"
                                        + mwClassReport.getCourse_code() + "-" + mwClassReport.getSection_number() + "<br/>"
                                        + mwClassReport.getDays() + "<br/>";
                            }
                            timeSlotMap.put(mwClassReport.getRoomName(), data);
                        }
                    }
                }
            }

            aClassRoomReport = new ClassRoomReportBean();
            aClassRoomReport.setTimeString(mwTimeSlot);
            aClassRoomReport.setInfoMap(timeSlotMap);
            tempMWReportList.add(aClassRoomReport);

        }
        mwReportList = tempMWReportList;
        return mwReportList;
    }

    public void setMwReportList(List<ClassRoomReportBean> mwReportList) {
        this.mwReportList = mwReportList;
    }

    public List<ClassRoomReportBean> getMwLabReportList() {
        List<ClassRoomReportBean> tempMWReportList = new ArrayList<>();
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session

        ReportDAO aMWReportList = new ReportDAOImpl();

        ArrayList<ReportBean> mwClassReportList = aMWReportList.findMondayWednesdayClassesReport(allSchId);
        ArrayList<String> mwTimeSlots = aMWReportList.findMondayWednesdayLabTimeSlots(allSchId);
        ClassRoomReportBean aClassRoomReport;

        for (String mwTimeSlot : mwTimeSlots) {
            HashMap<String, String> timeSlotMap = new HashMap<>();
            for (ReportBean mwClassReport : mwClassReportList) {

                if (mwTimeSlot.equalsIgnoreCase(mwClassReport.getClassTime())) {
                    for (int i = 0; i < labList.size(); i++) {
                        if (mwClassReport.getRoomName().equalsIgnoreCase(labList.get(i))) {
                            String data = null;
                            if (timeSlotMap.containsKey(mwClassReport.getRoomName())) {
                                data = timeSlotMap.get(mwClassReport.getRoomName());
                                data += "<br/>" + mwClassReport.getProfessorName() + "<br/>"
                                        + mwClassReport.getCourse_code() + "-" + mwClassReport.getSection_number() + "<br/>"
                                        + mwClassReport.getDays() + "<br/>";
                            } else {
                                data = "<br/>" + mwClassReport.getProfessorName() + "<br/>"
                                        + mwClassReport.getCourse_code() + "-" + mwClassReport.getSection_number() + "<br/>"
                                        + mwClassReport.getDays() + "<br/>";
                            }
                            timeSlotMap.put(mwClassReport.getRoomName(), data);
                        }

                    }
                }
            }

            aClassRoomReport = new ClassRoomReportBean();
            aClassRoomReport.setTimeString(mwTimeSlot);
            aClassRoomReport.setInfoMap(timeSlotMap);
            tempMWReportList.add(aClassRoomReport);

        }
        mwLabReportList = tempMWReportList;
        return mwLabReportList;
    }

    public void setMwLabReportList(List<ClassRoomReportBean> mwLabReportList) {
        this.mwLabReportList = mwLabReportList;
    }

    public List<ClassRoomReportBean> getTrReportList() {
        List<ClassRoomReportBean> tempTRReportList = new ArrayList<>();
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session

        ReportDAO aTRReportList = new ReportDAOImpl();

        ArrayList<ReportBean> trClassReportList = aTRReportList.findTuesdayThursdayClassesReport(allSchId);
        ArrayList<String> trTimeSlots = aTRReportList.findTuesdayThursdayLectureTimeSlots(allSchId);
        ClassRoomReportBean aClassRoomReport;

        for (String trTimeSlot : trTimeSlots) {
            HashMap<String, String> timeSlotMap = new HashMap<>();

            for (ReportBean trClassReport : trClassReportList) {

                if (trTimeSlot.equalsIgnoreCase(trClassReport.getClassTime())) {
                    for (int i = 0; i < lectureList.size(); i++) {
                        if (trClassReport.getRoomName().equalsIgnoreCase(lectureList.get(i))) {
                            String data = null;
                            if (timeSlotMap.containsKey(trClassReport.getRoomName())) {
                                data = timeSlotMap.get(trClassReport.getRoomName());
                                data += "<br/>" + trClassReport.getProfessorName() + "<br/>"
                                        + trClassReport.getCourse_code() + "-" + trClassReport.getSection_number() + "<br/>"
                                        + trClassReport.getDays() + "<br/>";
                            } else {
                                data = "<br/>" + trClassReport.getProfessorName() + "<br/>"
                                        + trClassReport.getCourse_code() + "-" + trClassReport.getSection_number() + "<br/>"
                                        + trClassReport.getDays() + "<br/>";
                            }
                            timeSlotMap.put(trClassReport.getRoomName(), data);
                        }
                    }
                }
            }

            aClassRoomReport = new ClassRoomReportBean();
            aClassRoomReport.setTimeString(trTimeSlot);
            aClassRoomReport.setInfoMap(timeSlotMap);
            tempTRReportList.add(aClassRoomReport);

        }
        trReportList = tempTRReportList;
        return trReportList;
    }

    public void setTrReportList(List<ClassRoomReportBean> trReportList) {
        this.trReportList = trReportList;
    }

    public List<ClassRoomReportBean> getTrLabReportList() {
        List<ClassRoomReportBean> tempTRLabReportList = new ArrayList<>();
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session

        ReportDAO aTRReportList = new ReportDAOImpl();

        ArrayList<ReportBean> trLabClassReportList = aTRReportList.findTuesdayThursdayClassesReport(allSchId);
        ArrayList<String> trLabTimeSlots = aTRReportList.findTuesdayThursdayLabTimeSlots(allSchId);
        ClassRoomReportBean aLabClassRoomReport;

        for (String trLabTimeSlot : trLabTimeSlots) {
            HashMap<String, String> timeSlotMap = new HashMap<>();

            for (ReportBean trLabClassReport : trLabClassReportList) {

                if (trLabTimeSlot.equalsIgnoreCase(trLabClassReport.getClassTime())) {
                    for (int i = 0; i < labList.size(); i++) {
                        if (trLabClassReport.getRoomName().equalsIgnoreCase(labList.get(i))) {
                            String data = null;
                            if (timeSlotMap.containsKey(trLabClassReport.getRoomName())) {
                                data = timeSlotMap.get(trLabClassReport.getRoomName());
                                data += "<br/>" + trLabClassReport.getProfessorName() + "<br/>"
                                        + trLabClassReport.getCourse_code() + "-" + trLabClassReport.getSection_number() + "<br/>"
                                        + trLabClassReport.getDays() + "<br/>";
                            } else {
                                data = "<br/>" + trLabClassReport.getProfessorName() + "<br/>"
                                        + trLabClassReport.getCourse_code() + "-" + trLabClassReport.getSection_number() + "<br/>"
                                        + trLabClassReport.getDays() + "<br/>";
                            }
                            timeSlotMap.put(trLabClassReport.getRoomName(), data);
                        }
                    }
                }
            }

            aLabClassRoomReport = new ClassRoomReportBean();
            aLabClassRoomReport.setTimeString(trLabTimeSlot);
            aLabClassRoomReport.setInfoMap(timeSlotMap);
            tempTRLabReportList.add(aLabClassRoomReport);

        }
        trLabReportList = tempTRLabReportList;
        return trLabReportList;
    }

    public void setTrLabReportList(List<ClassRoomReportBean> trLabReportList) {
        this.trLabReportList = trLabReportList;
    }

    public List<String> getLectureList() {
        ReportDAO aReportList = new ReportDAOImpl();
        lectureList = aReportList.findLectureRooms();
        return lectureList;
    }

    public void setLectureList(List<String> lectureList) {
        this.lectureList = lectureList;
    }

    public List<String> getLabList() {
        ReportDAO aReportList = new ReportDAOImpl();
        labList = aReportList.findLabRooms();
        return labList;
    }

    public void setLabList(List<String> labList) {
        this.labList = labList;
    }

    public List<ExportBean> getExportList() {
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session
        ReportDAO anExportList = new ReportDAOImpl();
        exportList = anExportList.populateExportTable(allSchId);
        return exportList;
    }

    public void setExportList(List<ExportBean> exportList) {
        this.exportList = exportList;
    }

}
