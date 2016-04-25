/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.CourseBean;
import bean.CourseSectionBean;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Shweta Vyas
 */
public interface CourseDAO {
    public ArrayList findAllCourses(int allScheduleId);
    public ArrayList<CourseBean> getAllCourses();
   // public int updateCourseInfo(CourseBean updatedCourse);
    
    public int addNewCourse(CourseBean newCourse);
    public int findCourseId(String course_code);
    public int addNewCourseSection(CourseSectionBean newCourseSection);
    public int numOfSections(int courseId, int allScheduleId);
    public boolean checkScheduleAndCourseInDb(int course_id, int all_sch_id);
   
}
