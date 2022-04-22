package com.kehuts.module.users.model.dto;

import com.kehuts.module.users.model.User;

public record UserDto(
        long id,
        String username,
        String email,
        String role,
        String avatar,
        String refreshToken,
        String token,
        long expiresAt
) {
    public UserDto withToken(String token, String refreshToken, long expiresAt){
        return new UserDto(id(),username(), email(),role(), avatar(), refreshToken, token, expiresAt);
    }

    public static UserDto getUserDTO(User user) {
        return  new UserDto(
                user.getId(),
                user.getFullnames(),
                user.getEmail(),
                user.getRole().toString(),
                user.getAvatar(),
                "",
                "",
                0L
        );
    }
}
