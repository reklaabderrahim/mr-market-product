package fr.mr_market.mr_product.document;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "products")
public class Product extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Field(value = "product_number")
    @NotBlank
    private String productNumber;

    @Field(value = "description")
    private String description;

    public static Product create(Long id, String productNumber, String description) {
        Product product = new Product();
        product.setId(id);
        product.setUuid(UUID.randomUUID());
        product.setProductNumber(productNumber);
        product.setDescription(description);
        return product;
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]").add("productNumber=" + productNumber).add("description=" + description).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getUuid(), product.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}

