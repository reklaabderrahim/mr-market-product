package fr.mr_market.mr_product.service;

import fr.mr_market.mr_product.document.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    UUID storeProduct(Product product);

    Product findProductByUuid(UUID uuid);

    List<Product> findProducts();
}
