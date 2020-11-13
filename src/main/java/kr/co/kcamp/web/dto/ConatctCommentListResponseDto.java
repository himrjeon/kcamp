package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.ContactComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConatctCommentListResponseDto {
    private Long commentid;
    private String memo;
    private String author;
    private LocalDateTime modifiedDate;

    public ConatctCommentListResponseDto(ContactComment entity) {
        this.commentid = entity.getCommentid();
        this.memo = entity.getMemo();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}