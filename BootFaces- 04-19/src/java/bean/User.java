/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gunjan
 */
@ManagedBean
@SessionScoped
public class User {

    private String ulid;
    private String password;
    private int isAdmin;
    private String createdTime;
    private String lastUpdtTime;

    public User(String ulid, String password, int isAdmin, String createdTime, String lastUpdtTime) {

        this.ulid = ulid;
        this.password = password;
        this.isAdmin = isAdmin;
        this.createdTime = createdTime;
        this.lastUpdtTime = lastUpdtTime;
    }

    public User() {
//        User user = (User) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userBean"));
//        this.ulid = user.getUlid();
//        this.password = user.getPassword();
//        this.isAdmin = user.getIsAdmin();
//        this.createdTime = user.getCreatedTime();
//        this.lastUpdtTime = user.getLastUpdtTime();
    }

    /**
     * @return the ulid
     */
    public String getUlid() {
        return ulid;
    }

    /**
     * @param ulid the ulid to set
     */
    public void setUlid(String ulid) {
        this.ulid = ulid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isAdmin
     */
    public int getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the createdTime
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return the lastUpdtTime
     */
    public String getLastUpdtTime() {
        return lastUpdtTime;
    }

    /**
     * @param lastUpdtTime the lastUpdtTime to set
     */
    public void setLastUpdtTime(String lastUpdtTime) {
        this.lastUpdtTime = lastUpdtTime;
    }

}
