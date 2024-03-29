package mk.finki.ukim.diansproject.cultural_place_microservice.repository;


import mk.finki.ukim.diansproject.cultural_place_microservice.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<UserReview, Long> {
    UserReview findByCulturalPlace_IdAndUser_Username(Long id, String username);

    List<UserReview> findByUser_username(String username);

    List<UserReview> findByCulturalPlace_Id(Long id);
}
