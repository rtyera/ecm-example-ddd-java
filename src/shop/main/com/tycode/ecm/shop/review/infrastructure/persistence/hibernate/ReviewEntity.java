package com.tycode.ecm.shop.review.infrastructure.persistence.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "review")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    private String id;
    private String productId;
    private String author;
    private String message;
    private LocalDateTime createOn;
    private boolean deliver;
    private boolean checker;

}
