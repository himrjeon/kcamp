package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.ShowRoom;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ShowRoomDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private String filePath;
    private String imgFullPath;


    public ShowRoom toEntity(){
        ShowRoom build = ShowRoom.builder()
                .id(id)
                .title(title)
                .author(author)
                .content(content)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public ShowRoomDto(Long id, LocalDateTime createdDate, String title, String author, String content, String filePath, String imgFullPath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
    }
}
