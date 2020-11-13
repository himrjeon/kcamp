package kr.co.kcamp.domain;

import kr.co.kcamp.domain.user.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Entity
public class DealerUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String userid;

    @Column
    private String password;

    @Column
    private String phonenum;

    @Column
    private String email;

    @Column
    private String regicheck;

    @Builder
    public DealerUser(Long id, String name, String userid, String password,String phonenum, String email,  String regicheck) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.phonenum = phonenum;
        this.email = email;
        this.regicheck = regicheck;
    }

    public void update(String name, String userid, String password, String phonenum, String email,  String regicheck) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.phonenum = phonenum;
        this.email = email;
        this.regicheck = regicheck;
    }

}
