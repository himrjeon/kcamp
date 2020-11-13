package kr.co.kcamp.service.posts;

import kr.co.kcamp.domain.*;
import kr.co.kcamp.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContactCommentService {
    private final ContactCommentRepository contactCommentRepository;

    @Transactional
    public Long save(ContactCommentSaveRequestDto requestDto) {
        return contactCommentRepository.save(requestDto.toEntity()).getCommentid();

    }

    @Transactional
    public Long update(Long id, ContactCommentUpdateRequestDto requestDto) {
        ContactComment postComment = contactCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        postComment.update(requestDto.getMemo());

        return id;
    }

    public ContactCommentResponseDto findById(Long id) {
        ContactComment entity = contactCommentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new ContactCommentResponseDto(entity);
    }

    @Transactional
    public List<ConatctCommentListResponseDto> findALLByPost(GuestBook guestBook) {
        return contactCommentRepository.findALLByGuestBook(guestBook).stream()
                .map(ConatctCommentListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                // 결과로 넘어온 posts의 스트림을 map을 통해 listdto로 변환하고 list로 변환하는 메소드.
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConatctCommentListResponseDto> findAllDesc() {
        return contactCommentRepository.findAllDesc().stream()
                .map(ConatctCommentListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                // 결과로 넘어온 posts의 스트림을 map을 통해 listdto로 변환하고 list로 변환하는 메소드.
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        ContactComment postComment = contactCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        contactCommentRepository.delete(postComment);
        // JpaRepository에서 이미 delete 메소드 지원하니 활용, id로 삭제할 수도 있고 파라미터로 삭제할 수도 있음. 존재하는 posts인지 엔티티 조회후 그대로 삭제한다.
    }
}