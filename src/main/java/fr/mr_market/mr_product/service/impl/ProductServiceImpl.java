package fr.mr_market.mr_product.service.impl;

import fr.mr_market.mr_product.document.Product;
import fr.mr_market.mr_product.exception.ProductNotFoundException;
import fr.mr_market.mr_product.repository.ProductRepository;
import fr.mr_market.mr_product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_WITH_UUID_CANNOT_BE_FOUND = "Product with uuid: {} cannot be found";

    private final ProductRepository productRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public UUID storeProduct(Product product) {
        Product productModel = Product.create(
                sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME),
                product.getProductNumber(),
                product.getDescription()
        );
        return productRepository.save(productModel).getUuid();
    }

    @Override
    public Product findProductByUuid(UUID uuid) {
        return productRepository.findProductByUuid(uuid).orElseThrow(() -> new ProductNotFoundException(PRODUCT_WITH_UUID_CANNOT_BE_FOUND, uuid.toString()));
    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }
}
