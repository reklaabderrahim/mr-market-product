package fr.mr_market.mr_product.controller;

import com.github.dozermapper.core.Mapper;
import fr.mr_market.mr_market_product.swagger.ProductsApi;
import fr.mr_market.mr_market_product.swagger.model.product.*;
import fr.mr_market.mr_product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1")
public class ProductController implements ProductsApi {

    private final ProductService productService;
    private final Mapper mapper;

    public ProductController(ProductService productService, Mapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DeleteProductByUuid200Response> deleteProductByUuid(String productUuid) {
        return ProductsApi.super.deleteProductByUuid(productUuid);
    }

    @Override
    public ResponseEntity<Product> findProductByUuid(String productUuid) {
        fr.mr_market.mr_product.document.Product product =
                productService.findProductByUuid(UUID.fromString(productUuid));

        return new ResponseEntity<>(mapper.map(product, Product.class), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<Product>> findProducts() {
        List<Product> products = productService.findProducts().stream()
                .map(product -> mapper.map(product, Product.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ProductCreate201Response> productCreate(Product product) {
        log.info("controller:: create product: {}", product);
        fr.mr_market.mr_product.document.Product productModel =
                mapper.map(product, fr.mr_market.mr_product.document.Product.class);
        ProductCreate201Response response = new ProductCreate201Response();
        response.setProductIdentifier(productService.storeProduct(productModel));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductUpdate201Response> productUpdate(Product product) {
        return ProductsApi.super.productUpdate(product);
    }

    @Override
    public ResponseEntity<ProductSearchResponse> searchProducts(ProductSearchCriteria productSearchCriteria) {
        return ProductsApi.super.searchProducts(productSearchCriteria);
    }
}
