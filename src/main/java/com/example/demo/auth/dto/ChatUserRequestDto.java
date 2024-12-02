package com.example.demo.auth.dto;

public class ChatUserRequestDto {

    private Long userId; // Redis에서 가져오는 유저 ID
    private String nickname; // 유저 닉네임
    private Boolean isActive; // 활성 상태
    private Long modifiedBy; // 수정한 유저 ID
    private String modificationReason; // 수정 이유

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModificationReason() {
        return modificationReason;
    }

    public void setModificationReason(String modificationReason) {
        this.modificationReason = modificationReason;
    }
}
