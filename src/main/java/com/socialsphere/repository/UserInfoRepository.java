package com.socialsphere.repository;

import com.socialsphere.entity.User;
import com.socialsphere.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    // Fetch profile by User
    Optional<UserInfo> findByUser(User user);

    // Fetch profile by userId (most commonly used)
    Optional<UserInfo> findByUserId(Long userId);

    // Check if profile exists for a user
    boolean existsByUser(User user);

    boolean existsByUserId(Long userId);
}
