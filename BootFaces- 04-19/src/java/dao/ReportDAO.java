/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author MinuRachel
 */
public interface ReportDAO {

    public ArrayList populateCourseSectionReport(int allSchId);

    public ArrayList populateInstructorReport(int allSchId);

    public ArrayList populateTimeTableReport(int allSchId);
    
    public ArrayList findLabRooms();
    
    public ArrayList findLectureRooms();

    public ArrayList findMondayWednesdayClassesReport(int allSchId);

    public ArrayList findMondayWednesdayLectureTimeSlots(int allSchId);
    
    public ArrayList findMondayWednesdayLabTimeSlots(int allSchId);

    public ArrayList findTuesdayThursdayClassesReport(int allSchId);

    public ArrayList findTuesdayThursdayLectureTimeSlots(int allSchId);
    
    public ArrayList findTuesdayThursdayLabTimeSlots(int allSchId);
    
    //this method is used in the send for Review use case
    public int addNewReviewer(ArrayList ulidList, int allSchId); 
    
    //this methods are used for the export schedule use case
    public ArrayList populateExportTable(int allSchId);
    

}
