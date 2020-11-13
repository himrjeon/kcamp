package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.ContactComment;
import lombok.Getter;

@Getter
public class ContactCommentResponseDto {
    private Long commentid;
    private String author;
    private String memo;
    private String password;

    public ContactCommentResponseDto(ContactComment entity) {
        this.commentid = entity.getCommentid();
        this.memo = entity.getMemo();
        this.password = entity.getPassword();
        this.author = entity.getAuthor();
    }
}