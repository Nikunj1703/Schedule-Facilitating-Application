/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import bean.*;
import java.util.ArrayList;
/**
 *
 * @author Gunjan
 */
public interface PreferencesDAO {
    
      public boolean checkCoursePrefExists(ProfessorBean professorBean, int courseId);
      public ArrayList<CoursePreferenceBean> fetchCoursePreference(ProfessorBean professorBean);
       public int addNewCoursePreference(CoursePreferenceBean coursePreferenceBean);
      public int updateCoursePreferences(CoursePreferenceBean coursePreferenceBean);
      
      public boolean checkDayPrefExists (ProfessorBean professorBean);
      public DayPreferenceBean fetchDayPreferences (ProfessorBean professorBean);
      public int updateDayPreferences(DayPreferenceBean dayPreferenceBean);
      public int addDayPreferences(DayPreferenceBean dayPreferenceBean);
      public String findCoursePreferences(int preferenceProfId);
      public String findProfName(int preferenceProfId);
      public String courseIDByInterestLevel(int preferenceProfId, int interestLevel);
      public String findDayPreferenceByProfId(int preferenceProfId);
      public String findOtherInterestPreferenceByProfId(int preferenceProfId);
      public String findFinalCommentPreferenceByProfId(int preferenceProfId);
      public String findTimePreferenceByProfId(int preferenceProfId);
      public String labNeededForProfessor(int preferenceProfId);
      public String courseCommentsForProfessor(int preferenceProfId);
}
