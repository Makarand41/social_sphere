package com.socialsphere.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String fullName;

    @Column(length = 500)
    private String bio;

    private String profilePictureUrl;

    private String mobileNumber; // optional

    @Builder.Default
    private boolean isPrivate = false;

    private LocalDateTime lastLoginAt;
}
