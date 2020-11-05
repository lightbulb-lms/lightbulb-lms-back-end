package edu.uncc.itcs.lightbulblms.controller.model.response;

import edu.uncc.itcs.lightbulblms.service.model.User;
import io.swagger.annotations.ApiModelProperty;

public class CourseMember {
    @ApiModelProperty(name = "User's ID in Okta")
    private String userID;
    @ApiModelProperty(name = "User's first name")
    private String firstName;
    @ApiModelProperty(name = "User's last name")
    private String lastName;
    @ApiModelProperty(name = "User's email address")
    private String emailAddress;
    @ApiModelProperty(name = "User's role")
    private RoleEnum role;

    public CourseMember(User user, RoleEnum role) {
        this.userID = user.getUserID();
        this.emailAddress = user.getEmailAddress();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
