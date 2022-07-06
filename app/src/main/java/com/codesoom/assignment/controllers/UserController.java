package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.UserDeleteService;
import com.codesoom.assignment.application.UserRegisterService;
import com.codesoom.assignment.application.UserUpdateService;
import com.codesoom.assignment.domain.User;
import javassist.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRegisterService userRegisterService;
    private final UserUpdateService userUpdateService;
    private final UserDeleteService userDeleteService;

    public UserController(
            UserRegisterService userRegisterService,
            UserUpdateService userUpdateService,
            UserDeleteService userDeleteService
    ) {
        this.userRegisterService = userRegisterService;
        this.userUpdateService = userUpdateService;
        this.userDeleteService = userDeleteService;
    }

    @PostMapping
    public User register(@RequestBody @Valid User user) {
        return this.userRegisterService.execute(user.getName(), user.getEmail(), user.getPassword());
    }

    @PutMapping("/{id}")
    public User update(@RequestBody @Valid User user, @PathVariable @Valid Long id) throws NotFoundException {
        return this.userUpdateService.execute(id, user.getName(), user.getEmail(), user.getPassword());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Valid Long id) throws NotFoundException {
        this.userDeleteService.execute(id);
    }
}
