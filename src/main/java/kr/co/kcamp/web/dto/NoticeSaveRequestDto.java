package kr.co.kcamp.web.dto;


import kr.co.kcamp.domain.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private String selectcar;

    @Builder
    public NoticeSaveRequestDto(String title, String content, String author, String selectcar) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.selectcar = selectcar;
    }

    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .author(author)
                .selectcar(selectcar)
                .build();
    }
}