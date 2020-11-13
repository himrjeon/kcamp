package kr.co.kcamp.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;

@Getter
@NoArgsConstructor
public class GuestBookUpdateRequestDto {
    private String guestname;
    private String phonenum;
    private String email;
    private String content;
    private String title;
    private String password;
    private String rentday;

    @Builder
    public GuestBookUpdateRequestDto(String guestname, String phonenum, String email, String content, String title, String password, String rentday) {
        this.guestname = guestname;
        this.phonenum = phonenum;
        this.email = email;
        this.content = content;
        this.title = title;
        this.password = password;
        this.rentday = rentday;
    }
}
