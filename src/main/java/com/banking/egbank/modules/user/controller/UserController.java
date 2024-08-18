package com.banking.egbank.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.egbank.modules.user.entities.UserEntity;
import com.banking.egbank.modules.user.service.UserService;
import com.banking.egbank.shared.common.apiResponse.ResStructure;
import com.banking.egbank.shared.common.translations.KeysMessages;
import com.banking.egbank.shared.common.translations.Langs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "user", description = "the user Api")
@RestController
// @ResponseBody
@RequestMapping("/admin/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Fetch all users", description = "fetches all users entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return ResStructure.successResponse(Langs.EN, userService.findAll(), KeysMessages.SUCCESS, HttpStatus.OK);
    }

    @Operation(summary = "Fetch one element", description = "fetches one  element and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findOneById(@PathVariable int id) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            ResStructure.notFoundRes(Langs.EN, KeysMessages.NOTFOUND);
        }
        return ResStructure.successResponse(Langs.EN, user, KeysMessages.SUCCESS, HttpStatus.OK);
    }

    @Operation(summary = "Fetch all users", description = "fetches all users entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> create(@Validated @RequestBody UserEntity user) {
        userService.create(user);
        return ResStructure.successResponse(Langs.EN, "", KeysMessages.CREATE, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> updateById(@PathVariable int id, @Validated @RequestBody UserEntity user) {
        UserEntity currentUser = userService.findById(id);
        if (currentUser == null) {
            ResStructure.notFoundRes(Langs.EN, KeysMessages.NOTFOUND);
        }
        userService.updateById(id, user);
        return ResStructure.successResponse(Langs.EN, user, KeysMessages.UPDATE, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        System.out.println("______________________-" + id);
        UserEntity user = userService.findById(id);
        if (user == null) {
            ResStructure.notFoundRes(Langs.EN, KeysMessages.NOTFOUND);
        }

        userService.deleteById(id);
        return ResStructure.successResponse(Langs.EN, user, KeysMessages.DELETE, HttpStatus.OK);
    }

    // @PostMapping(produces = "application/json", consumes = "application/json")
    // public ResponseEntity<String> create(
    // @RequestBody(description = "add new user", required = true, content =
    // @Content(schema = @Schema(implementation = UserEntity.class))) @Valid
    // UserEntity user) {
    // userService.create(user);
    // return ResponseEntity.ok("created");
    // }
}
