package kr.co.kcamp.web.dto;


import kr.co.kcamp.domain.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String selectcar;
    private LocalDateTime modifiedDate;

    public NoticeListResponseDto(Notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.selectcar = entity.getSelectcar();
        this.modifiedDate = entity.getModifiedDate();
    }
}