package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.News;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public NewsListResponseDto(News entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
