package com.UMC.history.service;

import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.entity.weekEntity.CommentEntity;
import com.UMC.history.entity.weekEntity.HashTagEntity;
import com.UMC.history.entity.weekEntity.ImageEntity;
import com.UMC.history.entity.weekEntity.LikeEntity;
import com.UMC.history.repository.*;
import com.UMC.history.util.CategoryEnum;
import com.UMC.history.util.S3Uploader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CommonService {

    private final int plusOne = 1;
    private final int deleteOne = -1;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;
    private final ImageReposotory imageReposotory;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;

    public CommonService(PostRepository postRepository, UserRepository userRepository, HashTagRepository hashTagRepository, S3Uploader s3Uploader, ImageReposotory imageReposotory, LikeRepository likeRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.hashTagRepository = hashTagRepository;
        this.imageReposotory = imageReposotory;
        this.s3Uploader = s3Uploader;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    public void savePost(@RequestBody CommonDTO.Post post) {
        UserEntity writer = userRepository.findByUserId(post.getUserId());
        PostEntity postEntity = PostEntity.builder()
                .user(writer)
                .category(post.getCategory())
                .contents(post.getContents())
                .title(post.getTitle())
                .build();

        postRepository.save(postEntity);

        List<HashTagEntity> hashTagEntityList = new ArrayList<>();
        int hashTagSize = post.getHashTags().size();
        int imageSize = post.getImageList().size();
        for (int i = 0; i < hashTagSize; i++) { // 추후 map함수로 바꿔야 할 듯
            HashTagEntity hashTag = HashTagEntity.builder().tag(post.getHashTags().get(i)).post(postEntity).build();
            hashTagEntityList.add(hashTag);
        }

        for (int i = 0; i < imageSize; i++) {
            try {
                String imageUrl = s3Uploader.upload(post.getImageList().get(i), "static");
                ImageEntity imageEntity = ImageEntity.builder().imgUrl(imageUrl).post(postEntity).build();
                imageReposotory.save(imageEntity);
            } catch (Exception e) {
                System.out.println(e);
            }
        }


        hashTagRepository.saveAll(hashTagEntityList);

    }

    public List<PostEntity> storyListByCategoryOrderByDate(CategoryEnum category) {
        return postRepository.findByCategoryOrderByCreatedDateDesc(category);
    }

    public List<PostEntity> storyListByOrderByDate() {
        return postRepository.findByOrderByCreatedDateDesc();
    }

    public List<PostEntity> storyListByCategoryOrderByLike(CategoryEnum category) {
        return postRepository.findByCategoryOrderByTotalLikeDesc(category);
    }

    public List<PostEntity> storyListByOrderByLike() {
        return postRepository.findByOrderByTotalLikeDesc();
    }

    public PostEntity selectById(Long postIdx) {
//        return postRepository.getById(postIdx);
        PostEntity post = postRepository.findById(postIdx).get();
        return post;
    }

    public CommonDTO.LockPost randomById(){
        PostEntity post = postRepository.findByOrderByRand();
        CommonDTO.LockPost lockPost= new CommonDTO.LockPost();
        lockPost.setTitle(post.getTitle());
        lockPost.setContents(post.getContents().substring(0,50)+" ...");//내용은 50자까지
        return lockPost;
    }

    public List<CommonDTO.UserProtected> storyListByUser(Principal principal) {
        UserEntity userEntity = userRepository.findByUserId(principal.getName());
        return postRepository.findByUserOrderByCreatedDateDesc(userEntity);
    }

    public List<CommonDTO.LikeUserProtected> postLike(Principal principal){
        //COMMENTS 개수 쿼리는 따로 작성
        UserEntity userEntity = userRepository.findByUserId(principal.getName());
        return likeRepository.findByUserOrderByCreatedDateDesc(userEntity);
    }

    public void postComment(Long postIdx, Principal principal, @RequestBody CommonDTO.Comment comment) {

        UserEntity userEntity = userRepository.findByUserId(principal.getName());
        PostEntity postEntity = postRepository.findById(postIdx).get();
        CommentEntity commentEntity = CommentEntity.builder()
                .user(userEntity)
                .post(postEntity)
                .contents(comment.getContents())
                .build();
        postEntity.createComment(plusOne);
        commentRepository.save(commentEntity);
    }

    public boolean patchComment(Long commentIdx, Principal principal, CommonDTO.Comment comment) {
        CommentEntity commentEntity = commentRepository.findById(commentIdx).get();
        UserEntity userEntity = userRepository.findByUserId(principal.getName());
        if(commentEntity.getUser().equals(userEntity)){//comment userIdx와 jwt 의 user가 같다면?
            commentEntity.changeContents(comment.getContents());
            commentRepository.save(commentEntity);
            return true;
        }else return false;
    }

    public boolean deleteComment(Long commentIdx, Principal principal) {
        CommentEntity commentEntity = commentRepository.findById(commentIdx).get();
        UserEntity userEntity = userRepository.findByUserId(principal.getName());
        if(commentEntity.getUser().equals(userEntity)){
            commentRepository.deleteById(commentIdx);
            PostEntity post = commentEntity.getPost();
            post.createComment(deleteOne);
            postRepository.save(post);
            return true;
        }else return false;
    }

    public boolean deleteStory(Long postIdx, Principal principal) {
        PostEntity postEntity = postRepository.findById(postIdx).get();
        UserEntity userEntity = userRepository.findByUserId(principal.getName());
        if(postEntity.getUser().equals(userEntity)){
            postRepository.deleteById(postIdx);
            return true;
        }else return false;
    }


    public List<CommonDTO.UserProtected> searchInContents(String keyword){
        return postRepository.findByContentsContains(keyword);
    }

    public List<CommonDTO.UserProtected> searchInTitle(String keyword){
        return postRepository.findByTitleContains(keyword);
    }

    public boolean likingPostUser(Long postIdx, Principal principal) {
        PostEntity postEntity = postRepository.findById(postIdx).get(); // post 정보 불러오기
        UserEntity userEntity = userRepository.findByUserId(principal.getName()); // user 정보 불러오기
        LikeEntity likeEntity = likeRepository.findByPostAndUser(postEntity, userEntity);
        if(likeEntity == null){
            LikeEntity likeUser = LikeEntity.builder()
                    .user(userEntity)
                    .post(postEntity)
                    .build();
            likeRepository.save(likeUser);

            postEntity.createLike(plusOne);
            postRepository.save(postEntity);
            return true;
        }else{
            likeRepository.deleteById(likeEntity.getLikeIdx());
            postEntity.createLike(deleteOne);
            postRepository.save(postEntity);
            return false;
        }
    }

}
