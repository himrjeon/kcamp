package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.ContactComment;
import kr.co.kcamp.domain.GuestBook;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactCommentSaveRequestDto {

    private String author;
    private String memo;
    private String password;
    private GuestBook guestBook;

    public void setPost(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    @Builder
    public ContactCommentSaveRequestDto(String password, String memo, String author, GuestBook guestBook) {
        this.memo = memo;
        this.password = password;
        this.author = author;
        this.guestBook = guestBook;
    }

    public ContactComment toEntity() {
        return ContactComment.builder()
                .memo(memo)
                .password(password)
                .author(author)
                .guestBook(guestBook)
                .build();
    }
}