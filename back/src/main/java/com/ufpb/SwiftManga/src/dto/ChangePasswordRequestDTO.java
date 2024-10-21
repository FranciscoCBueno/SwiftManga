package com.ufpb.SwiftManga.src.dto;

import lombok.Data;

@Data
public class ChangePasswordRequestDTO {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
