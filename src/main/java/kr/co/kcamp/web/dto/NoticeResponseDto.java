package kr.co.kcamp.web.dto;


import kr.co.kcamp.domain.Notice;
import lombok.Getter;

@Getter
public class NoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String selectcar;

    public NoticeResponseDto(Notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.selectcar = entity.getSelectcar();
    }
}