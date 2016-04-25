/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.User;
import dao.UserDAO;
import dao.UserDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MinuRachel
 */
@ManagedBean
@SessionScoped
public class SignUpController {

    private String response;
    private String signUpValidaton;
    private User theModel;
    private String firstName;
    private String lastName;
    private String confirmPassword;

    public SignUpController() {
        this.theModel = new User();
        response = "";
        signUpValidaton = "";
        firstName = "";
        lastName = "";
        confirmPassword = "";
    }

    public SignUpController(String response, String signUpValidaton, User theModel) {
        this.response = response;
        this.signUpValidaton = signUpValidaton;
        this.theModel = theModel;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSignUpValidaton() {
        return signUpValidaton;
    }

    public void setSignUpValidaton(String signUpValidaton) {
        this.signUpValidaton = signUpValidaton;
    }

    public User getTheModel() {
        return theModel;
    }

    public void setTheModel(User theModel) {
        this.theModel = theModel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String validateSignUp() {
        String validationMessage = null;
        String ulid = theModel.getUlid();
        String pwd = theModel.getPassword();

        UserDAO aSignUpDAO = new UserDAOImpl();
        int ulidCount = aSignUpDAO.checkUlidPresent(ulid);
        boolean isFaculty = aSignUpDAO.isFacultyUlid(ulid);
        if (firstName.length() == 0) {
            signUpValidaton = "The first name field cannot be left blank. Please enter your first name";
        } else if (firstName.length() < 2 || firstName.length() > 25) {
            signUpValidaton = "Your first name has to be between 2 and 25 letters long";
        } else if (lastName.length() == 0) {
            signUpValidaton = "The last name field cannot be left blank. Please enter your last name";
        } else if (lastName.length() < 2 || lastName.length() > 25) {
            signUpValidaton = "Your last name has to be between 2 and 25 letters long";
        } else if (ulid.length() == 0) {
            signUpValidaton = "Your ULID is required to create an account. Please enter your ULID";
        } else if (ulid.length() < 2 || ulid.length() > 8) {
            signUpValidaton = "Invalid ULID. Please enter your ULID again.";
        } else if (ulidCount >= 1) {
            signUpValidaton = "The ULID you provided is already registered in this system. Try logging in";
        } else if (pwd.length() == 0) {
            signUpValidaton = "A password is required to create an account. Please enter a password for your account";
        } else if (pwd.length() < 8) {
            signUpValidaton = "The password should have at least 8 characters";
        } else if (confirmPassword.length() == 0) {
            signUpValidaton = "Please confirm your password";
        } else if (!pwd.equals(confirmPassword)) {
            signUpValidaton = "The password and the confirmation password doesn't match";
        }else if (!isFaculty) {
            signUpValidaton = "Only faculty members are authorized to access this system.";
        } else {
            signUpValidaton = "";
            validationMessage = createAccount();
        }

        return validationMessage;
    }

    public String createAccount() {
        UserDAO aSignUpDAO = new UserDAOImpl();    // Creating a new object each time.
        int rowCount = aSignUpDAO.createAccount(theModel); // Doing anything with the object after this?
        if (rowCount == 1) {
            signUpValidaton = "";
            return "login.xhtml"; // navigate to "signUpConfirmation.xhtml"
        } else {
            signUpValidaton = "Couldn't complete the registration. Please try again later";
            return "";
        }
    }
}
