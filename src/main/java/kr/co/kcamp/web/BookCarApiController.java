package kr.co.kcamp.web;

import kr.co.kcamp.domain.BookCarRepository;
import kr.co.kcamp.service.cars.BookCarService;
import kr.co.kcamp.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookCarApiController {
    private final BookCarService bookCarService;
    private final BookCarRepository bookCarRepository;

    @PostMapping("/api/v2/book")
    public Long save(@RequestBody BookCarSaveRequestDto requestDto) {
        return bookCarService.save(requestDto);
    }

    @PutMapping("/api/v2/book/{id}")
    public Long update(@PathVariable Long id, @RequestBody BookCarUpdateRequestDto requestDto) {
        return bookCarService.update(id, requestDto);
    }

    @GetMapping("/api/v2/book/{id}")
    public BookCarResponseDto findById (@PathVariable Long id) {
        return bookCarService.findById(id);
    }

    @PostMapping("/api/v2/book/list")
    public BookCarResponseDto findByDate (@RequestBody String startDate, String endDate) {
        return bookCarRepository.findDate(startDate, endDate);
    }

    @DeleteMapping("/api/v2/book/{id}")
    public Long delete(@PathVariable Long id) {
        bookCarService.delete(id);
        return id;
    }
}
