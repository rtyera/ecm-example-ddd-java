package com.tycode.ecm.seller.product.infrastructure.persistense.hibernate;

import java.io.Serializable;

public record ProductReviewJson(String author, String message) implements Serializable {
    @Override
    public String toString() {
        return "ProductReviewEmbeddable{" +
                    "\"author\"=" + "\"" + author + "\"," +
                    "\"message\"=" + "\"" + message + "\"," +
                "}";
    }
}
