package com.UMC.history.controller;


import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.service.CommonService;
import com.UMC.history.util.CategoryEnum;
import com.UMC.history.util.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
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
    }

    @GetMapping(value = "/stories/{category}") // 이야기 카테고리에 따라서 10개씩 가져오기
   public CommonResponse<List> storyListByCategory(@PathVariable("category") CategoryEnum category){
        return new CommonResponse<List>(commonService.selectByCategory(category), HttpStatus.OK);
    }

    //user가 쓴 글
    @GetMapping(value = "/stories/byUser")
    public CommonResponse<List> storyListByUser(Principal principal){
        return new CommonResponse<List>(commonService.storyListByUser(principal), HttpStatus.OK);
    }

    //user가 좋아요 한 글
    // USER id 로 불러오는 것이 아니라 jwt 값을 제공하면 정보를 불러오는 경우로 변경
    @GetMapping(value = "/stories/Liking")
    public CommonResponse<List> LikingUser(Principal principal){
        return new CommonResponse<List>(commonService.postLike(principal), HttpStatus.OK);
    }

    //댓글
    @PostMapping(value = "story/comment/{postIdx}")
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Post success")
    public void postComment(@PathVariable("postIdx") Long postIdx, Principal principal, @RequestBody CommonDTO.Comment comment){
        commonService.postComment(postIdx, principal, comment);
    }

    //댓글 변경
    @PatchMapping(value = "story/comment/change/{commentIdx}")
    public CommonResponse<Boolean> patchComment(@PathVariable("commentIdx") Long commentIdx,
                             Principal principal, @RequestBody CommonDTO.Comment comment){
        return new CommonResponse<Boolean>(commonService.patchComment(commentIdx, principal, comment), HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping(value = "story/comment/delete/{commentIdx}")
    public CommonResponse<Boolean> deleteComment(@PathVariable("commentIdx") Long commentIdx, Principal principal){
        return new CommonResponse<Boolean>(commonService.deleteComment(commentIdx, principal), HttpStatus.ACCEPTED);
    }




}