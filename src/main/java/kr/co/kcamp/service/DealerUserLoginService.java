package kr.co.kcamp.service;

import kr.co.kcamp.config.auth.dto.SessionUser;
import kr.co.kcamp.domain.DealerUser;
import kr.co.kcamp.domain.DealerUserRepository;
import kr.co.kcamp.service.cars.S3Service;
import kr.co.kcamp.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DealerUserLoginService {
    private DealerUserRepository dealerUserRepository;
    private final HttpSession httpSession;

    public void savePost(DealerUserDto dealerUserDto) {
        dealerUserRepository.save(dealerUserDto.toEntity());
    }

    public List<DealerUserDto> getList() {
        List<DealerUser> galleryEntityList = dealerUserRepository.findAll();
        List<DealerUserDto> galleryDtoList = new ArrayList<>();

        for (DealerUser galleryEntity : galleryEntityList) {
            galleryDtoList.add(convertEntityToDto(galleryEntity));
        }

        return galleryDtoList;
    }

    public DealerUserDto findById(Long id) {
        DealerUser entity = dealerUserRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        DealerUserDto dto = convertEntityToDto(entity);

        return dto;
    }

    @Transactional
    public Long update(Long id, DealerUserDto requestDto) {
        DealerUser entity = dealerUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        entity.update(requestDto.getName(), requestDto.getUserid(), requestDto.getPassword(), requestDto.getPhonenum(),  requestDto.getEmail(), requestDto.getRegicheck());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        DealerUser notice = dealerUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        dealerUserRepository.delete(notice);
    }

    @Transactional
    public void signIn(DealerUserDto dealerUserDto) {
        String userid = dealerUserDto.getUserid();
        String password = dealerUserDto.getPassword();
        DealerUser dealerUser = dealerUserRepository.findUser(userid, password);
        if(dealerUser != null) {
            httpSession.setAttribute("user1", dealerUser);
        }

    }


    private DealerUserDto convertEntityToDto(DealerUser galleryEntity) {
        return DealerUserDto.builder()
                .id(galleryEntity.getId())
                .name(galleryEntity.getName())
                .userid(galleryEntity.getUserid())
                .password(galleryEntity.getPassword())
                .phonenum(galleryEntity.getPhonenum())
                .email(galleryEntity.getEmail())
                .regicheck(galleryEntity.getRegicheck())
                .build();
    }

}
