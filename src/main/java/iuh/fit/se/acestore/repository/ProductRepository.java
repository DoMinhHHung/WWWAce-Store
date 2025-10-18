package iuh.fit.se.acestore.repository;

import iuh.fit.se.acestore.entity.Product;
import iuh.fit.se.acestore.entity.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findAll();
    List<Product> findByType(ProductType type);

}
