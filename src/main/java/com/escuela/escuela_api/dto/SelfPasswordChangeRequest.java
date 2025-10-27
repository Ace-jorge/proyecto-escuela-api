package com.escuela.escuela_api.dto;
import lombok.Data;
@Data
public class SelfPasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}