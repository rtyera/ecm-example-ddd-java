package com.tycode.ecm.seller.product.infrastructure.persistense.hibernate;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private float price;
    private int stockQuantity;
    @ElementCollection
    private List<String> images;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Set<ProductReviewJson> reviews = new HashSet<>();
}
