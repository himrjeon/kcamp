package kr.co.kcamp.service.cars;

import kr.co.kcamp.domain.BookCar;
import kr.co.kcamp.domain.BookCarRepository;
import kr.co.kcamp.domain.Posts;
import kr.co.kcamp.domain.PostsRepository;
import kr.co.kcamp.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookCarService {
    private final BookCarRepository bookCarRepository;

    @Transactional
    public Long save(BookCarSaveRequestDto requestDto) {
        return bookCarRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public Long update(Long id, BookCarUpdateRequestDto requestDto) {
        BookCar bookCar = bookCarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        bookCar.update(requestDto.getTitle(), requestDto.getStart(), requestDto.getEnd(), requestDto.getDescription(), requestDto.getType(), requestDto.getBackgroundColor(), requestDto.getAllDay());

        return id;
    }

    public BookCarResponseDto findById(Long id) {
        BookCar entity = bookCarRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new BookCarResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BookCarListResponseDto> findAllDesc() {
        return bookCarRepository.findAll().stream()
                .map(BookCarListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                // 결과로 넘어온 posts의 스트림을 map을 통해 listdto로 변환하고 list로 변환하는 메소드.
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BookCarListResponseDto> getBoardList(Pageable pageable) {
        return bookCarRepository.findAll(pageable).stream()
                .map(BookCarListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                // 결과로 넘어온 posts의 스트림을 map을 통해 listdto로 변환하고 list로 변환하는 메소드.
                .collect(Collectors.toList());

    }



    @Transactional
    public void delete (Long id) {
        BookCar posts = bookCarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        bookCarRepository.delete(posts);
        // JpaRepository에서 이미 delete 메소드 지원하니 활용, id로 삭제할 수도 있고 파라미터로 삭제할 수도 있음. 존재하는 posts인지 엔티티 조회후 그대로 삭제한다.
    }
}
