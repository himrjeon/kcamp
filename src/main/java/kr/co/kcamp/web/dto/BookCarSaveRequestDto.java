package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.BookCar;
import kr.co.kcamp.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookCarSaveRequestDto {

    private String title;
    private String start;
    private String end;
    private String description;
    private String type;
    private String username;
    private String backgroundColor;
    private String textColor;
    private String allDay;

    @Builder
    public BookCarSaveRequestDto(String title, String start, String end, String description, String type, String username, String backgroundColor, String textColor, String allDay) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.type = type;
        this.username = username;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.allDay = allDay;
    }

    public BookCar toEntity() {
        return BookCar.builder()
                .title(title)
                .start(start)
                .end(end)
                .description(description)
                .type(type)
                .username(username)
                .backgroundColor(backgroundColor)
                .textColor(textColor)
                .allDay(allDay)
                .build();
    }

}
