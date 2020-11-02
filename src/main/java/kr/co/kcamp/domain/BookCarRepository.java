package kr.co.kcamp.domain;

import kr.co.kcamp.web.dto.BookCarResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookCarRepository extends JpaRepository<BookCar, Long> {

    @Query(nativeQuery = true, value ="select * from book_car m where m.start between :startDate and :endDate")
    BookCarResponseDto findDate(String startDate, String endDate);
}
