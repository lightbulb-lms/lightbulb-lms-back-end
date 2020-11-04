package edu.uncc.itcs.lightbulblms.service.model;

import edu.uncc.itcs.lightbulblms.service.model.response.OktaUsersResponse;
import io.swagger.annotations.ApiModelProperty;

public class User {
    @ApiModelProperty(name = "User's ID in Okta")
    private String userID;
    @ApiModelProperty(name = "User's first name")
    private String firstName;
    @ApiModelProperty(name = "User's last name")
    private String lastName;
    @ApiModelProperty(name = "User's email address")
    private String emailAddress;

    public User() {
    }

    public User(OktaUsersResponse oktaUsersResponse) {
        this.userID = oktaUsersResponse.getId();
        this.firstName = oktaUsersResponse.getProfile().getFirstName();
        this.lastName = oktaUsersResponse.getProfile().getLastName();
        this.emailAddress = oktaUsersResponse.getProfile().getEmail();
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
}
