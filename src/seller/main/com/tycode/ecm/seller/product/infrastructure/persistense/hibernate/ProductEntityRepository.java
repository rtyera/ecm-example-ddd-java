package com.tycode.ecm.seller.product.infrastructure.persistense.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductEntityRepository extends JpaRepository<ProductEntity, String> {
}
