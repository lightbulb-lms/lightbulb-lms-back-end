package edu.uncc.itcs.lightbulblms.controller.model.response;

import java.util.List;

public class CourseMembersResponse {
    List<CourseMember> members;

    public CourseMembersResponse(List<CourseMember> members) {
        this.members = members;
    }

    public List<CourseMember> getMembers() {
        return members;
    }

    public void setMembers(List<CourseMember> members) {
        this.members = members;
    }
}
