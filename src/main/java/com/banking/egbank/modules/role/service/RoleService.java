package com.banking.egbank.modules.role.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.egbank.modules.role.entities.RoleEntity;
import com.banking.egbank.modules.role.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleEntity> findAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public RoleEntity createRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    public RoleEntity updateRole(Long id, RoleEntity roleDetails) {
        return roleRepository.findById(id).map(role -> {
            role.setName(roleDetails.getName());
            return roleRepository.save(role);
        }).orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    public void deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Role not found with id " + id);
        }
    }
}
