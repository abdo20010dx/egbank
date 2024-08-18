package com.banking.egbank.modules.user.entities;

import java.util.Objects;
import java.util.Set;

import com.banking.egbank.modules.role.entities.RoleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "full_name", nullable = true, length = 50)
    private String full_name;

    @Column(name = "email", unique = true, nullable = false)
    public String email;

    @Column(name = "password", nullable = false)
    private String password;

    public UserEntity(long id, String full_name, String email, String password, Set<RoleEntity> roles) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity full_name(String full_name) {
        setFull_name(full_name);
        return this;
    }

    public UserEntity email(String email) {
        setEmail(email);
        return this;
    }

    public UserEntity password(String password) {
        setPassword(password);
        return this;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public void setFull_name(String fullName) {
        this.full_name = fullName;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(long id, String fullName, Set<RoleEntity> roles) {
        this.id = id;
        this.full_name = fullName;
        this.roles = roles;
    }

    public Set<RoleEntity> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public UserEntity id(long id) {
        setId(id);
        return this;
    }

    public UserEntity fullName(String fullName) {
        setFull_name(fullName);
        return this;
    }

    public UserEntity roles(Set<RoleEntity> roles) {
        setRoles(roles);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }
        UserEntity userEntity = (UserEntity) o;
        return id == userEntity.id && Objects.equals(full_name, userEntity.full_name) && Objects.equals(roles, userEntity.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, full_name, roles);
    }

    @Override
    public String toString() {
        return "{"
                + " id='" + getId() + "'"
                + ", fullName='" + getFull_name() + "'"
                + ", roles='" + getRoles() + "'"
                + "}";
    }

    public static UserEntity builder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'builder'");
    }

}
