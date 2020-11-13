package kr.co.kcamp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactCommentRepository  extends JpaRepository<ContactComment, Long> {

    @Query("SELECT p FROM ContactComment p ORDER BY p.id DESC")
    List<ContactComment> findAllDesc();

    @Query("SELECT p FROM ContactComment p WHERE p.guestBook = :#{#id} ORDER BY p.createdDate DESC")
    List<ContactComment> findALLByGuestBook(@Param("id") GuestBook guestBook);
}