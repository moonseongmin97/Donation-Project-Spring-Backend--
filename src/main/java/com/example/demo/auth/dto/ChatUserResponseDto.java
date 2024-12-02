package com.example.demo.auth.dto;

public class ChatUserResponseDto {

    private Long chatUserId; // 유저 고유 채팅 ID
    private Long userId; // Redis에서 가져온 유저 ID
    private String nickname; // 유저 닉네임
    private Boolean isActive; // 활성 상태
    private String modificationReason; // 마지막 수정 이유
    private Integer modificationCount; // 수정 횟수
    private String createdAt; // 생성 시간 (ISO 8601 형식으로 전달)
    private String updatedAt; // 수정 시간 (ISO 8601 형식으로 전달)

    // Getters and Setters
    public Long getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(Long chatUserId) {
        this.chatUserId = chatUserId;
    }

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

    public String getModificationReason() {
        return modificationReason;
    }

    public void setModificationReason(String modificationReason) {
        this.modificationReason = modificationReason;
    }

    public Integer getModificationCount() {
        return modificationCount;
    }

    public void setModificationCount(Integer modificationCount) {
        this.modificationCount = modificationCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
