package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.PostComment;
import kr.co.kcamp.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostCommentListResponseDto {
    private Long commentid;
    private String memo;
    private String author;
    private LocalDateTime modifiedDate;

    public PostCommentListResponseDto(PostComment entity) {
        this.commentid = entity.getCommentid();
        this.memo = entity.getMemo();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
