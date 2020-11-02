package kr.co.kcamp.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookCarUpdateRequestDto {

    private String title;
    private String start;
    private String end;
    private String description;
    private String type;
    private String backgroundColor;
    private String allDay;

    @Builder
    public BookCarUpdateRequestDto(String title, String start, String end, String description, String type, String backgroundColor, String allDay) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.type = type;
        this.backgroundColor = backgroundColor;
        this.allDay = allDay;

    }
}
