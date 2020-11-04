package edu.uncc.itcs.lightbulblms.controller;

import edu.uncc.itcs.lightbulblms.controller.annotation.AdminOperation;
import edu.uncc.itcs.lightbulblms.controller.model.response.AllUsersResponse;
import edu.uncc.itcs.lightbulblms.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ApiOperation(value = "List all users", notes = "Retrieves all users for this application, separated by role", response = AllUsersResponse.class)
    @AdminOperation
    public ResponseEntity<AllUsersResponse> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
