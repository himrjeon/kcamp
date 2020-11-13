package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.GuestBook;
import kr.co.kcamp.domain.News;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestBookSaveRequestDto {

    private String guestname;
    private String phonenum;
    private String email;
    private String content;
    private String title;
    private String password;
    private String rentday;

    @Builder
    public GuestBookSaveRequestDto(String guestname, String phonenum, String email, String content, String title, String password, String rentday) {
        this.guestname = guestname;
        this.phonenum = phonenum;
        this.email = email;
        this.content = content;
        this.title = title;
        this.password = password;
        this.rentday = rentday;
    }

    public GuestBook toEntity() {
        return GuestBook.builder()
                .guestname(guestname)
                .phonenum(phonenum)
                .email(email)
                .content(content)
                .title(title)
                .password(password)
                .rentday(rentday)
                .build();
    }
}
