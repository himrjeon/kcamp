package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.PostComment;
import kr.co.kcamp.domain.Posts;
import lombok.Getter;

@Getter
public class PostCommentResponseDto {
    private Long commentid;
    private String author;
    private String memo;
    private String password;

    public PostCommentResponseDto(PostComment entity) {
        this.commentid = entity.getCommentid();
        this.memo = entity.getMemo();
        this.password = entity.getPassword();
        this.author = entity.getAuthor();
    }
}
