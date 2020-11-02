package kr.co.kcamp.web.dto;


import kr.co.kcamp.domain.BookCar;
import lombok.Getter;

@Getter
public class BookCarResponseDto {
    private Long id;
    private String title;
    private String start;
    private String end;
    private String description;
    private String type;
    private String username;
    private String backgroundColor;
    private String textColor;
    private String allDay;

    public BookCarResponseDto(BookCar entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.start = entity.getStart();
        this.end = entity.getEnd();
        this.description = entity.getDescription();
        this.type = entity.getType();
        this.username = entity.getUsername();
        this.backgroundColor = entity.getBackgroundColor();
        this.textColor = entity.getTextColor();
        this.allDay = entity.getAllDay();
    }
}
