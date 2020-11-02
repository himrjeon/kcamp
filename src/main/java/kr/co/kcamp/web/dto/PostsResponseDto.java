package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String depositname;

    private String phonenum;

    private String startdate;

    private String enddate;

    private String selectcar;


    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.depositname = entity.getDepositname();
        this.phonenum = entity.getPhonenum();
        this.startdate = entity.getStartdate();
        this.enddate = entity.getEnddate();
        this.selectcar = entity.getSelectcar();
    }
}
