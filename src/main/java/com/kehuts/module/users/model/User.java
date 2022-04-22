package com.kehuts.module.users.model;

import com.kehuts.module.utils.BaseEntity;
import com.kehuts.module.utils.UserType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    private String fullnames;

    @Column(unique = true)
    private Long phonenumber;

    @Column(unique = true)
    private String email;

    @Column(name = "national_id")
    private long nationalId;
    private String avatar;

    @Column(name = "id_path")
    private String idPath;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "refresh_token_validity")
    private LocalDateTime refreshTokenValidity;

    private String password;

    private UserType role;

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(LocalDateTime refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }
}
