package kr.co.kcamp.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactCommentUpdateRequestDto {
    private String memo;


    @Builder
    public ContactCommentUpdateRequestDto(String memo) {
        this.memo = memo;
    }
}