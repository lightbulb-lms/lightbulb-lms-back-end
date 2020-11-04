package edu.uncc.itcs.lightbulblms.controller.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private List<ValidationError> errors = new ArrayList<>();
}
