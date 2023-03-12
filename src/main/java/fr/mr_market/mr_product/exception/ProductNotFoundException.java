package fr.mr_market.mr_product.exception;

import org.apache.commons.lang3.StringUtils;

public class ProductNotFoundException extends GenericException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, String param) {
        super(StringUtils.replace(message, "{}", param));
    }
}
