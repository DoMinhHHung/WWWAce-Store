package iuh.fit.se.wwwacestore.repository;

import iuh.fit.se.wwwacestore.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
