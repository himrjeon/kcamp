package kr.co.kcamp.service.cars;

import kr.co.kcamp.domain.ImportCars;
import kr.co.kcamp.domain.ShowRoom;
import kr.co.kcamp.domain.ShowRoomRepository;
import kr.co.kcamp.web.dto.ImportCarsDto;
import kr.co.kcamp.web.dto.ShowRoomDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShowRoomS3UploadService {
    private ShowRoomRepository showRoomRepository;
    private S3Service s3Service;

    public void savePost(ShowRoomDto showRoomDto) {
        showRoomRepository.save(showRoomDto.toEntity());
    }

    @Transactional
    public List<ShowRoomDto> getBoardList(Pageable pageable) {
        Page<ShowRoom> galleryEntityList = showRoomRepository.findAll(pageable);
        List<ShowRoomDto> galleryDtoList = new ArrayList<>();;

        for (ShowRoom galleryEntity : galleryEntityList) {
            galleryDtoList.add(convertEntityToDto(galleryEntity));
        }

        return galleryDtoList;

    }

    public List<ShowRoomDto> getList() {
        List<ShowRoom> galleryEntityList = showRoomRepository.findAllDesc();
        List<ShowRoomDto> galleryDtoList = new ArrayList<>();

        for (ShowRoom galleryEntity : galleryEntityList) {
            galleryDtoList.add(convertEntityToDto(galleryEntity));
        }

        return galleryDtoList;
    }

    public ShowRoomDto findById(Long id) {
        ShowRoom entity = showRoomRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        ShowRoomDto dto = convertEntityToDto(entity);

        return dto;
    }

    @Transactional
    public Long update(Long id, ShowRoomDto requestDto) {
        ShowRoom entity = showRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        entity.update(requestDto.getTitle(), requestDto.getFilePath(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        ShowRoom notice = showRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        showRoomRepository.delete(notice);
    }

    @Transactional(readOnly = true)
    public List<ShowRoomDto> findTop6Desc() {
        List<ShowRoom> galleryEntityList = showRoomRepository.findTop6Desc();
        List<ShowRoomDto> galleryDtoList = new ArrayList<>();

        for (ShowRoom galleryEntity : galleryEntityList) {
            galleryDtoList.add(convertEntityToDto(galleryEntity));
        }

        return galleryDtoList;
    }

    private ShowRoomDto convertEntityToDto(ShowRoom galleryEntity) {
        return ShowRoomDto.builder()
                .id(galleryEntity.getId())
                .title(galleryEntity.getTitle())
                .author(galleryEntity.getAuthor())
                .content(galleryEntity.getContent())
                .createdDate(galleryEntity.getCreatedDate())
                .filePath(galleryEntity.getFilePath())
                .imgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + galleryEntity.getFilePath())
                .build();
    }
}
