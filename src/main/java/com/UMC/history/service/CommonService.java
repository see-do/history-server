package com.UMC.history.service;

import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.DTO.UserDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.entity.weekEntity.HashTagEntity;
import com.UMC.history.entity.weekEntity.ImageEntity;
import com.UMC.history.repository.HashTagRepository;
import com.UMC.history.repository.ImageReposotory;
import com.UMC.history.repository.PostRepository;
import com.UMC.history.repository.UserRepository;
import com.UMC.history.util.CategoryEnum;
import com.UMC.history.util.S3Uploader;
import com.amazonaws.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;
    private final ImageReposotory imageReposotory;
    private final S3Uploader s3Uploader;

    public CommonService(PostRepository postRepository, UserRepository userRepository, HashTagRepository hashTagRepository, S3Uploader s3Uploader, ImageReposotory imageReposotory) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.hashTagRepository = hashTagRepository;
        this.imageReposotory = imageReposotory;
        this.s3Uploader = s3Uploader;
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

    public List<PostEntity> selectByCategory(CategoryEnum category) {
        return postRepository.findByCategory(category);
    }

    public PostEntity selectById(Long postIdx) {
//        return postRepository.getById(postIdx);
        PostEntity post = postRepository.findById(postIdx).get();
        return post;
    }
}
