package kr.co.kcamp.service.posts;

import kr.co.kcamp.domain.Notice;
import kr.co.kcamp.domain.NoticeRepository;
import kr.co.kcamp.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequestDto requestDto) {
        return noticeRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public Long update(Long id, NoticeUpdateRequestDto requestDto) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        notice.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public NoticeResponseDto findById(Long id) {
        Notice entity = noticeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new NoticeResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<NoticeListResponseDto> findAllDesc() {
        return noticeRepository.findAllDesc().stream()
                .map(NoticeListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                // 결과로 넘어온 posts의 스트림을 map을 통해 listdto로 변환하고 list로 변환하는 메소드.
                .collect(Collectors.toList());
    }

    @Transactional
    public List<NoticeListResponseDto> getBoardList(Pageable pageable) {
        return noticeRepository.findAll(pageable).stream()
                .map(NoticeListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                // 결과로 넘어온 posts의 스트림을 map을 통해 listdto로 변환하고 list로 변환하는 메소드.
                .collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public List<NoticeListResponseDto> findTop3Desc() {
        return noticeRepository.findTop3Desc().stream()
                .map(NoticeListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                // 결과로 넘어온 posts의 스트림을 map을 통해 listdto로 변환하고 list로 변환하는 메소드.
                .collect(Collectors.toList());
    }



    @Transactional
    public void delete (Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        noticeRepository.delete(notice);
        // JpaRepository에서 이미 delete 메소드 지원하니 활용, id로 삭제할 수도 있고 파라미터로 삭제할 수도 있음. 존재하는 posts인지 엔티티 조회후 그대로 삭제한다.
    }
}
