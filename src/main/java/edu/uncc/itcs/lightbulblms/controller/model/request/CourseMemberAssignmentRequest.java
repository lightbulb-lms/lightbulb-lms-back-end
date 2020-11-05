package edu.uncc.itcs.lightbulblms.controller.model.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CourseMemberAssignmentRequest {
    @NotBlank(message = "This field cannot be blank")
    @ApiModelProperty(value = "THe user ID to be added", required = true)
    private String userId;

    public CourseMemberAssignmentRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
