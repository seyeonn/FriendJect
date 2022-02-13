package com.ssafy.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.KakaoUserInfo;

@Repository
public interface KakaoUserRepository  extends JpaRepository<KakaoUserInfo, Long> {
    Optional<KakaoUserInfo> findByKakaoId(Long kakaoId);
}
