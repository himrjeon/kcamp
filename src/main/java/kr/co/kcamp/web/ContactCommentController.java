package kr.co.kcamp.web;

import kr.co.kcamp.domain.*;
import kr.co.kcamp.service.posts.ContactCommentService;
import kr.co.kcamp.service.posts.PostCommentService;
import kr.co.kcamp.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ContactCommentController {

    private final ContactCommentService postCommentService;
    private final GuestBookRepository postsRepository;

    @PostMapping("/api/v1/contactcomment/{id}")
    public Long save(@PathVariable Long id, @RequestBody ContactCommentSaveRequestDto requestDto) {
        Optional<GuestBook> postItem = postsRepository.findById(id);
        requestDto.setPost(postItem.get());
        return postCommentService.save(requestDto);
    }

    @PutMapping("/api/v1/contactcomment/{id}/{commentid}")
    public Long update(@PathVariable Long id, @RequestBody ContactCommentUpdateRequestDto requestDto) {
        return postCommentService.update(id, requestDto);
    }

    @GetMapping("/api/v1/contactcomment/{id}")
    public ContactCommentResponseDto findById (@PathVariable Long id) {
        return postCommentService.findById(id);
    }

    @DeleteMapping("/api/v1/contactcomment/{id}")
    public Long delete(@PathVariable Long id) {
        postCommentService.delete(id);
        return id;
    }
}
