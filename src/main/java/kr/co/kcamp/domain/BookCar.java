package kr.co.kcamp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class BookCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String start;

    @Column
    private String end;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private String username;

    @Column
    private String backgroundColor;

    @Column
    private String textColor;

    @Column
    private String allDay;

    @Builder
    public BookCar(String title, String start, String end, String description, String type, String username, String backgroundColor, String textColor, String allDay) {
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

    public void update(String title, String start, String end, String description, String type,  String backgroundColor, String allDay) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.type = type;
        this.backgroundColor = backgroundColor;
        this.allDay = allDay;
    }

}
