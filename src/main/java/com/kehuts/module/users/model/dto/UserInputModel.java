package com.kehuts.module.users.model.dto;

public record UserInputModel(
        String fullname,
        Long phoneNumber,
        String email,
        String password
        ) {}
