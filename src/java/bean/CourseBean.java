package bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shweta Vyas
 */
public class CourseBean {
    private String course_code;
    private String course_name;
    private int course_id;
    private String created_ts;
    private String last_updt_ts;
    private int numberOfSections;
     private boolean editable;
    
    public CourseBean(){
        course_code = "";
        course_name = "";
        created_ts = "";
        last_updt_ts = "";
        numberOfSections = 1;
    }

    public CourseBean(String course_code, String course_name, String created_ts, String last_updt_ts) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.created_ts = created_ts;
        this.last_updt_ts = last_updt_ts;
    }

    public int getNumberOfSections() {
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }
    
    
    public String getCourse_code() {
        return course_code;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
    
    
  

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCreated_ts() {
        return created_ts;
    }

    public void setCreated_ts(String created_ts) {
        this.created_ts = created_ts;
    }

    public String getLast_updt_ts() {
        return last_updt_ts;
    }

    public void setLast_updt_ts(String last_updt_ts) {
        this.last_updt_ts = last_updt_ts;
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
    
   
}
