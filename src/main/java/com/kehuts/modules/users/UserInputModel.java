package com.kehuts.modules.users;

public record UserInputModel(
        String fullname,
        long phoneNumber,
        String address,
        String email
        ) {}
