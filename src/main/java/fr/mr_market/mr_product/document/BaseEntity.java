package fr.mr_market.mr_product.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Getter
@Setter
abstract class BaseEntity {

    @Id
    private Long id;

    @Indexed(unique = true)
    @Field
    private UUID uuid;
}

