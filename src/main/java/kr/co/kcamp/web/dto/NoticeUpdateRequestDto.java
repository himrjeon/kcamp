package kr.co.kcamp.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public NoticeUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}