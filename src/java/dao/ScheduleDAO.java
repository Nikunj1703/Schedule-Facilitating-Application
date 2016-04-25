/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.ScheduleBean;
import bean.ToDoBean;
import java.util.ArrayList;

/**
 *
 * @author Gunjan
 */
public interface ScheduleDAO {
    public ArrayList<ScheduleBean> getScheduleForReview(ToDoBean toDoBean);
     public ArrayList getCourseSections(int allScheduleID);
     public String findSectionNumber(int sectionID);
     public int addScheduleRow(ScheduleBean aSchedule);
     public ArrayList<ScheduleBean> findScheduleBeanForModifyFromDB(int allScheduleId);
     public int updateScheduleRow(ScheduleBean aSchedule);
     public int insertCourseSectionForSaveAsSchedule(ScheduleBean aSchedule, String sectionNumber, int courseId);
     public ArrayList<ScheduleBean> getConflictingSchedule(int allScheduleId,String scheduleIds);
     public int findMaxScheduleId();
   }
