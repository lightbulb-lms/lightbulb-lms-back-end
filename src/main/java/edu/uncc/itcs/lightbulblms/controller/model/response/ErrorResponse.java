package edu.uncc.itcs.lightbulblms.controller.model.response;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<ValidationError> errors = new ArrayList<>();

    public ErrorResponse() {
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
