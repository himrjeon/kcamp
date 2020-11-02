package kr.co.kcamp.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String author;
    private String depositname;
    private String phonenum;
    private String startdate;
    private String enddate;
    private String selectcar;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String author, String depositname, String phonenum, String startdate, String enddate, String selectcar) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.depositname = depositname;
        this.phonenum = phonenum;
        this.startdate = startdate;
        this.enddate = enddate;
        this.selectcar = selectcar;

    }
}
