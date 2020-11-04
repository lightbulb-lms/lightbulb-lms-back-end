package edu.uncc.itcs.lightbulblms.controller.model.response;

import edu.uncc.itcs.lightbulblms.service.model.User;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AllUsersResponse {
    @ApiModelProperty(value = "List of admin users")
    private List<User> adminUsers;
    @ApiModelProperty(value = "List of student users")
    private List<User> studentUsers;
    @ApiModelProperty(value = "List of teacher users")
    private List<User> teacherUsers;

    public AllUsersResponse() {
    }

    public AllUsersResponse(List<User> adminUsers, List<User> studentUsers, List<User> teacherUsers) {
        this.adminUsers = adminUsers;
        this.studentUsers = studentUsers;
        this.teacherUsers = teacherUsers;
    }

    public List<User> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<User> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public List<User> getStudentUsers() {
        return studentUsers;
    }

    public void setStudentUsers(List<User> studentUsers) {
        this.studentUsers = studentUsers;
    }

    public List<User> getTeacherUsers() {
        return teacherUsers;
    }

    public void setTeacherUsers(List<User> teacherUsers) {
        this.teacherUsers = teacherUsers;
    }
}
