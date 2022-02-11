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

    //최신순 이야기 카테고리에 따라서 10개씩 가져오기
    @GetMapping(value = "/stories/recent/{category}")
    public CommonResponse<List> storyListByCategoryOrderByDate(@PathVariable("category") CategoryEnum category){
        return new CommonResponse<List>(commonService.storyListByCategoryOrderByDate(category), HttpStatus.OK);
    }

    //좋아요 순 이야기 카테고리에 따라서 10개씩 가져오기
    @GetMapping(value = "/stories/liking/{category}")
    public CommonResponse<List> storyListByCategoryOrderByLike(@PathVariable("category") CategoryEnum category){
        return new CommonResponse<List>(commonService.storyListByCategoryOrderByLike(category), HttpStatus.OK);
    }

    //user가 쓴 글 최신순으로
    @GetMapping(value = "/stories/byUser")
    public CommonResponse<List> storyListByUser(Principal principal){
        return new CommonResponse<List>(commonService.storyListByUser(principal), HttpStatus.OK);
    }

    //user가 좋아요 한 글 최신순으로
    // USER id 로 불러오는 것이 아니라 jwt 값을 제공하면 정보를 불러오는 경우로 변경
    @GetMapping(value = "/stories/liking")
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

    //글 삭제
    @DeleteMapping(value = "story/delete/{postIdx}")
    public CommonResponse<Boolean> deleteStory(@PathVariable("postIdx") Long postIdx, Principal principal){
        return new CommonResponse<Boolean>(commonService.deleteStory(postIdx, principal), HttpStatus.OK);
    }


    //검색한 키워드를 내용에서 찾기
    @GetMapping(value = "story/content/search")
    public CommonResponse<List> searchInContents(@RequestParam(value = "keyword")String keyword){
        return new CommonResponse<List>(commonService.searchInContents(keyword),HttpStatus.OK);
    }

    //검색한 키워드를 제목에서 찾기
    @GetMapping(value = "story/title/search")
    public CommonResponse<List> searchInTitle(@RequestParam(value = "keyword")String keyword){
        return new CommonResponse<List>(commonService.searchInTitle(keyword),HttpStatus.OK);
    }

    //좋아요
    //TRUE: 좋아요 추가 FALSE: 좋아요 취소
    @PostMapping(value = "story/liking/{postIdx}")
    public CommonResponse<Boolean> likingPostUser(@PathVariable("postIdx") Long postIdx, Principal principal){
        return new CommonResponse<Boolean>(commonService.likingPostUser(postIdx, principal), HttpStatus.OK);
    }

    //퀴즈 - 전체에 해당
    @GetMapping(value = "quiz/all")
    public CommonResponse<List> quiz(){
        return new CommonResponse<List>(commonService.quizList(),HttpStatus.OK);
    }

    //퀴즈 - 카테코리별에 해당당
   @GetMapping(value = "quiz/{category}")
    public CommonResponse<List> quiz(@PathVariable("category") CategoryEnum category){
        return new CommonResponse<List>(commonService.quizListByCategory(category),HttpStatus.OK);
    }
}