/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.AllScheduleBean;
import dao.ReportDAO;
import dao.ReportDAOImpl;
import java.util.ArrayList;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MinuRachel
 */
@ManagedBean
@SessionScoped
public class SendForReviewController {

    private String ulids;
    private String sendForReviewStatus;
    private int allScheduleId;

    public SendForReviewController() {
        ulids = "";
        sendForReviewStatus = "";
    }

    public String getUlids() {

        return ulids;
    }

    public void setUlids(String ulids) {

        this.ulids = ulids;
    }

    public String getSendForReviewStatus() {
        return sendForReviewStatus;
    }

    public void setSendForReviewStatus(String sendForReviewStatus) {
        this.sendForReviewStatus = sendForReviewStatus;
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

    public String submitReviewRequest() {
        int allSchId = this.allScheduleId; //this should be replaced with the allSchId that is passed in the session 
        String[] ulidArray = ulids.split(",");
        ArrayList<String> ulidList = new ArrayList();
        for (int i = 0; i < ulidArray.length; i++) {
            ulidList.add(ulidArray[i]);
        }
        ReportDAO aReviewAdd = new ReportDAOImpl();
        int rowCount = aReviewAdd.addNewReviewer(ulidList, allSchId);
        if (rowCount == 0) {
            sendForReviewStatus = "Something seriously went wrong. Please try again later.";

        } else {
            sendForReviewStatus = "Reivewers have been added to the schedule. They will recieve an email notification to take action.";
            for (int i = 0; i < ulidList.size(); i++) {
                sendEmailForApproval(ulidList.get(i) + "@ilstu.edu");
            }
        }

        return null;
    }

    public void sendEmailForApproval(String email) {
        // Recipient's email ID needs to be mentioned.
        String to = email; //have to query db to get the email of admin
        // Sender's email ID needs to be mentioned
        String from = "msabu@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "yourID"); // if needed
        properties.setProperty("mail.password", "yourPassword"); // if needed
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Schedule Review Request: Action Needed");
            String messageBody = "Hi,<br><br>You have been invited to review the draft schedule being prepared right now.<br/> Please log into the Schedule facilitor System"
                    + " to review the same. Feel free to add your comments to the schedule, if you have any. Let me know If you hvae any questions or concerns.";
//                    + "<br><br>Best,<br>Dr. Mary Elaine Califf<br>";
            // Send the actual HTML message, as big as you like
            message.setContent(messageBody,
                    "text/html");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            throw new RuntimeException(mex);
        }
    }
}
