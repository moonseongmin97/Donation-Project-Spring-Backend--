package com.example.demo.auth.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_users")
public class ChatUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // BIGSERIAL에 대응
    @Column(name = "chat_user_id")
    private Long chatUserId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "modification_reason", length = 255)
    private String modificationReason;

    @Column(name = "modification_count", nullable = false)
    private Integer modificationCount = 0;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public Integer getModificationCount() {
        return modificationCount;
    }

    public void setModificationCount(Integer modificationCount) {
        this.modificationCount = modificationCount;
    }
}
