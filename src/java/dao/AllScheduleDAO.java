/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AllScheduleBean;
import bean.ScheduleBean;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Shweta Vyas
 */
public interface AllScheduleDAO {
    public int findAllScheduleIDs(String term,String year);
    public AllScheduleBean addNewAllScheduleID(AllScheduleBean aScheduleBean, String term, String year);
    public int findAllScheduleId();
    public ArrayList<String> findScheduleVersionsFromDB(String term_Year);
    public int findAllScheduleIDBySchVersionNameFromDB(String scheduleName);
    public String findReviewCommentsFromReviewCommentTable(int allSchId);
}
