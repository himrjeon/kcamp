package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private String depositname;

    private String phonenum;

    private String startdate;

    private String enddate;

    private String selectcar;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String depositname, String phonenum, String startdate, String enddate, String selectcar) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.depositname = depositname;
        this.phonenum = phonenum;
        this.startdate = startdate;
        this.enddate = enddate;
        this.selectcar = selectcar;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .depositname(depositname)
                .phonenum(phonenum)
                .startdate(startdate)
                .enddate(enddate)
                .selectcar(selectcar)
                .build();
    }
}
