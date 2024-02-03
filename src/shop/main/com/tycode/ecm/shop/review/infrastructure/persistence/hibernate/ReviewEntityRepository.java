package com.tycode.ecm.shop.review.infrastructure.persistence.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

interface ReviewEntityRepository extends JpaRepository<ReviewEntity, String> {
}
