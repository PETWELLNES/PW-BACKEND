package com.petwellnes.petwellnes_backend.infra.config.security;

public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private Long userId; // Agregar el campo userId

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}