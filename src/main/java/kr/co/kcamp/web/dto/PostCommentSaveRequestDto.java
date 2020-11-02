package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.PostComment;
import kr.co.kcamp.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentSaveRequestDto {

    private String author;
    private String memo;
    private String password;
    private Posts posts;

    public void setPost(Posts posts) {
        this.posts = posts;
    }

    @Builder
    public PostCommentSaveRequestDto(String password, String memo, String author, Posts posts) {
        this.memo = memo;
        this.password = password;
        this.author = author;
        this.posts = posts;
    }

    public PostComment toEntity() {
        return PostComment.builder()
                .memo(memo)
                .password(password)
                .author(author)
                .posts(posts)
                .build();
    }
}
