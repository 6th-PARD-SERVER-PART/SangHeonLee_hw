package pard.server.com.seminar6.repository;
import pard.server.com.seminar6.entity.Product;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAllByColor(String color);

    int countAllByColor(String color);
}
