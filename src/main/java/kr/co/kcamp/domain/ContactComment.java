package kr.co.kcamp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드 자동생성
@NoArgsConstructor // 기본 생성자 자동 추가 public posts() {}와 같은 효과
@Entity //  테이블과 링크될 클래스임을 나타냄.언더스코어네이밍으로 테이블이름매칭
public class ContactComment extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙. indentity를 추가해야만 자동증가가능.
    private Long commentid;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String memo;

    @Column
    private String author;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "guestbook_id")
    private GuestBook guestBook;

    @Builder // 해당 클래스 빌더패턴 클래스 생성, 생성자 상단 선언시 생성자 포함된 필드만 빌더 포함
    public ContactComment(String memo, String author, String password, GuestBook guestBook) {
        this.memo = memo;
        this.author = author;
        this.password = password;
        this.guestBook = guestBook;

    }

    public void update(String memo) {
        this.memo = memo;
    }
}