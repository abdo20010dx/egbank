package com.banking.egbank.modules.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.egbank.modules.role.entities.RoleEntity;
import com.banking.egbank.modules.role.service.RoleService;
import com.banking.egbank.shared.common.apiResponse.ResStructure;
import com.banking.egbank.shared.common.translations.KeysMessages;
import com.banking.egbank.shared.common.translations.Langs;

@RestController
@RequestMapping("/admin/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        List<RoleEntity> roles = roleService.findAllRoles();
        return ResStructure.successResponse(Langs.EN, roles, KeysMessages.SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id) {
        return roleService.findRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity roleEntity) {
        return ResponseEntity.ok(roleService.createRole(roleEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody RoleEntity roleDetails) {
        RoleEntity updatedRole = roleService.updateRole(id, roleDetails);
        return ResStructure.successResponse(Langs.EN, updatedRole, KeysMessages.UPDATE, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResStructure.successResponse(Langs.EN, null, KeysMessages.DELETE, HttpStatus.OK);
    }
}
