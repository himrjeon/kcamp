package kr.co.kcamp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRoomRepository extends JpaRepository<ShowRoom, Long> {
    @Override
    List<ShowRoom> findAll();

    @Query("SELECT p FROM ShowRoom p ORDER BY p.id DESC")
    List<ShowRoom> findAllDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM showroom p ORDER BY p.id DESC LIMIT 6")
    List<ShowRoom> findTop6Desc();
}