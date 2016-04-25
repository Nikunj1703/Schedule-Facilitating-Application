/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Shweta Vyas
 */
public class CourseSectionBean {
    private int course_ID;
    private int all_sch_id;
    private String section_number;


    public CourseSectionBean() {
        this.course_ID = 0;
        this.all_sch_id = 0;
        this.section_number = "";

    }

    
    public CourseSectionBean(int course_ID, int all_sch_id, String section_number) {
        this.course_ID = course_ID;
        this.all_sch_id = all_sch_id;
        this.section_number = section_number;
    }

    public int getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(int course_ID) {
        this.course_ID = course_ID;
    }

    public int getAll_sch_id() {
        return all_sch_id;
    }

    public void setAll_sch_id(int all_sch_id) {
        this.all_sch_id = all_sch_id;
    }

    public String getSection_number() {
        return section_number;
    }

    public void setSection_number(String section_number) {
        this.section_number = section_number;
    }

   
    
}
