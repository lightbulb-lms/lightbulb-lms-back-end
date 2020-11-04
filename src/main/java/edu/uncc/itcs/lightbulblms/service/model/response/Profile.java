package edu.uncc.itcs.lightbulblms.service.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("mobilePhone")
    public Object mobilePhone;
    @JsonProperty("secondEmail")
    public Object secondEmail;
    @JsonProperty("login")
    public String login;
    @JsonProperty("email")
    public String email;

    public Profile() {
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

    public Object getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Object mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Object getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(Object secondEmail) {
        this.secondEmail = secondEmail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
