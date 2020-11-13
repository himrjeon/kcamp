package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.GuestBook;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GuestBookResponseDto{
    private Long id;
    private String guestname;
    private String phonenum;
    private String email;
    private String content;
    private String title;
    private String password;
    private LocalDateTime modifiedDate;

    public GuestBookResponseDto(GuestBook entity) {
        this.id = entity.getId();
        this.guestname = entity.getGuestname();
        this.phonenum = entity.getPhonenum();
        this.email = entity.getEmail();
        this.content = entity.getContent();
        this.title = entity.getTitle();
        this.password = entity.getPassword();
        this.modifiedDate = entity.getModifiedDate();
    }
}