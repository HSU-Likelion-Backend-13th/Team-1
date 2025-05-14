package com.likelion.demo.domain.post.repository;

import com.likelion.demo.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //어떤 엔티티인지 / 기본키의 타입
}
