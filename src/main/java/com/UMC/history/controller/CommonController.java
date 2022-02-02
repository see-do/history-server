package com.UMC.history.controller;


import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.service.CommonService;
import com.UMC.history.util.CategoryEnum;
import com.UMC.history.util.CommonResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/common")
public class CommonController {

    private CommonService commonService;

    public CommonController(CommonService commonService){
        this.commonService = commonService;
    }

    @PostMapping(value = "/story") //이야기 글쓰기
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Post success")
    public void savePost(@RequestPart List<MultipartFile> imageList, @RequestPart("postData") CommonDTO.Post jsonData){
        jsonData.setImageList(imageList);
        commonService.savePost(jsonData);
    }

    @GetMapping(value = "/story/{postIdx}") // post index로 글 하나만 가져오기
    public CommonResponse<PostEntity> storyByPostId(@PathVariable("postIdx") Long postIdx){
       return new CommonResponse<PostEntity>(commonService.selectById(postIdx), HttpStatus.OK);
//        return commonService.selectById(postIdx);
    }

    @GetMapping(value = "/stories/{category}") // 이야기 카테고리에 따라서 10개씩 가져오기
   public CommonResponse<List> storyListByCategory(@PathVariable("category") CategoryEnum category){
        return new CommonResponse<List>(commonService.selectByCategory(category), HttpStatus.OK);
    }
}
