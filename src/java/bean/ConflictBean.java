/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Comparator;

/**
 *
 * @author Nikunj
 */
public class ConflictBean {
    private ScheduleBean firstScheduleBean;
    private ScheduleBean secondScheduleBean;

    public ConflictBean() {
    }
  

    public ScheduleBean getFirstScheduleBean() {
        return firstScheduleBean;
    }

    public void setFirstScheduleBean(ScheduleBean firstScheduleBean) {
        this.firstScheduleBean = firstScheduleBean;
    }

    public ScheduleBean getSecondScheduleBean() {
        return secondScheduleBean;
    }

    public void setSecondScheduleBean(ScheduleBean secondScheduleBean) {
        this.secondScheduleBean = secondScheduleBean;
    }



    public ConflictBean(ScheduleBean firstScheduleBean, ScheduleBean secondScheduleBean) {
        this.firstScheduleBean = firstScheduleBean;
        this.secondScheduleBean = secondScheduleBean;
    }
    
    public static Comparator<ScheduleBean> compareByDays = new Comparator<ScheduleBean>(){
        public int compare(ScheduleBean firstScheduleBean, ScheduleBean secondScheduleBean){
            return firstScheduleBean.getDays().compareTo(secondScheduleBean.getDays());
        }
    };
    
}
