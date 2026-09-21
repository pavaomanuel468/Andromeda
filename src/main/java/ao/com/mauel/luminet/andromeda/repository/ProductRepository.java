package ao.com.mauel.luminet.andromeda.repository;

import ao.com.mauel.luminet.andromeda.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
