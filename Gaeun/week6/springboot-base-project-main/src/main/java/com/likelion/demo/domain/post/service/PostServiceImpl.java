package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.domain.post.web.dto.PostSummaryRes.PostSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public CreatePostRes createOne(CreatePostReq createPostReq) {
        //1. createPostReq가지고 Post Entity 생성
        Post post = Post.builder()
                .title(createPostReq.getTitle())
                .content(createPostReq.getContent())
                .username(createPostReq.getUsername())
                .password(createPostReq.getPassword())
                .state(PostState.PROGRESS)
                .build();

        //2. repository Post 저장 (postRepository 사용)
        Post savedPost = postRepository.save(post); //저장한 포스터의 엔티티를 넘김

        //3. CreatePostRes postId 반환
        return new CreatePostRes(savedPost.getId());//이거는 JPA덕분인건가?
    }

    @Override
    public PostDetailRes getById(Long postId) {
        //1. postId에 해당하는 Post - DB에서 조회
        Post post = postRepository.findById(postId)
                // 404 - postId에 대해 처리
                .orElseThrow(PostNotFoundException::new);

        //2. PostDetailRes 반환
        return new PostDetailRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUsername(),
                post.getPassword(),
                post.getState(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    //리스트 형식으로 반환
    @Override
    public PostSummaryRes getAll() {
        //1. DB에서 모든 Post 조회(postRespository)
        List<Post> posts = postRepository.findAll();
        //만일 저장된 게시글이 없을떄 빈 리스트 반환
        //빈 리스트를 반환때도 처리 로직을 작성 해야하는지? 아니면 프론트 파트에서 따로 처리 로직을 만드는걸까?

        //2. posts -> PostSummaryRes로 변환
        List<PostSummary> postSummaryList = new ArrayList<>();

        for(Post post : posts) {
            PostSummary postSummary = new PostSummary(
                    post.getId(),
                    post.getTitle(),
                    post.getUsername(),
                    post.getCreatedAt()
            );
            postSummaryList.add(postSummary);
        }
        //3. 반환
        return new PostSummaryRes(postSummaryList);
    }

    //게시글 수정
    //@Transactional
    //JPA가 수정한 것을 자동으로 감지해서 DB에 업데이트
    @Override
    public PostDetailRes modfiyOne(Long postId, ModifyPostReq modifyPostReq) {
        //1. DB에서 postId로 Post 찾기
        //404 - 게시글 없음
        Post foundpost = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        //2.비밀번호 검즘
        //403 - 비밀번호 불일치
        if(!foundpost.getPassword().equals(modifyPostReq.getPassword())) {
            throw new InvalidPasswordException();
        }

        //3.post 수정
        //해당 부분은 post라는 객체의 변동사항 로직들 모음
        //그러니 post객체 내부의 변경사항 로직은 post 엔티티에 작성 추천
        foundpost.modify(modifyPostReq.getTitle(), modifyPostReq.getContent());

        //PostDetailRes 반환
        return new PostDetailRes(
                foundpost.getId(),
                foundpost.getTitle(),
                foundpost.getContent(),
                foundpost.getUsername(),
                foundpost.getPassword(),
                foundpost.getState(),
                foundpost.getCreatedAt(),
                foundpost.getUpdatedAt()
        );
    }

    //게시글 삭제
    //@Transactional
    @Override
    public void deleteOne(Long postId, DeletePostReq deletePostReq) {
        //1.게시글 존재 확인
        //404 - 게시글 존재하지 않음
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        //2.비밀번호 검증
        //403 - 비밀번호 불일치
        if(!post.getPassword().equals(deletePostReq.getPassword())) {
            throw new InvalidPasswordException();
        }

        //3. 삭제
        postRepository.delete(post);

    }
}
