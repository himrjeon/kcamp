package kr.co.kcamp.web.dto;

import kr.co.kcamp.domain.DealerUser;
import kr.co.kcamp.domain.ImportCars;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DealerUserDto {
    private Long id;
    private String name;
    private String userid;
    private String password;
    private String phonenum;
    private String email;
    private String regicheck;

    public DealerUser toEntity(){
        DealerUser build = DealerUser.builder()
                .id(id)
                .name(name)
                .userid(userid)
                .password(password)
                .phonenum(phonenum)
                .email(email)
                .regicheck(regicheck)
                .build();
        return build;
    }
    @Builder
    public DealerUserDto(Long id, String name, String userid, String password,  String phonenum, String email,  String regicheck) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.phonenum = phonenum;
        this.email = email;
        this.regicheck = regicheck;
    }

}
