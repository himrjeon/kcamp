package kr.co.kcamp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT p FROM News p ORDER BY p.id DESC")
    List<News> findAllDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM news p ORDER BY p.id DESC LIMIT 3")
    List<News> findTop3Desc();
}