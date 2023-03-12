package fr.mr_market.mr_product.repository;

import fr.mr_market.mr_product.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
    Optional<Product> findProductByUuid(UUID uuid);
}
