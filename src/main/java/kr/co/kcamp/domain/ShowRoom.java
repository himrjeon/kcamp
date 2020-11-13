package kr.co.kcamp.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "showroom")
public class ShowRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    @Builder
    public ShowRoom(Long id, String title, String filePath, String content, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.filePath = filePath;

    }


    public void update(String title, String filePath, String content) {
        this.title = title;
        this.content = content;
        this.filePath = filePath;

    }
}
