package com.tycode.ecm.shop.product.infrastructure.persistence.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductEntityRepository extends JpaRepository<ProductEntity, String> {
}
