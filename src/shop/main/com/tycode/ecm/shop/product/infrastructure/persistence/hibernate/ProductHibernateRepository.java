package com.tycode.ecm.shop.product.infrastructure.persistence.hibernate;

import com.tycode.ecm.shared.domain.criteria.Criteria;
import com.tycode.ecm.shared.infrastructure.hibernate.HibernateCriteriaConverter;
import com.tycode.ecm.shop.product.domain.Product;
import com.tycode.ecm.shop.product.domain.ProductId;
import com.tycode.ecm.shop.product.domain.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductHibernateRepository implements ProductRepository{

    private final ProductEntityRepository productEntityRepository;
    private final EntityManager entityManager;

    @Override
    public Optional<Product> findById(ProductId productId) {
        return this.productEntityRepository.findById(productId.value()).map(ProductMapper::entityToDomain);
    }

    @Override
    public List<Product> findAll() {
        return this.productEntityRepository.findAll()
                .stream()
                .map(ProductMapper::entityToDomain)
                .toList();
    }

    @Override
    public void save(Product product) {
        var channelStatusEntity = ProductMapper.domainToEntity(product);
        this.productEntityRepository.save(channelStatusEntity);
    }

    @Override
    public void remove(Product product) {
        var channelStatusEntity = ProductMapper.domainToEntity(product);
        this.productEntityRepository.delete(channelStatusEntity);
    }

    @Override
    public List<Product> matching(Criteria criteria){
        var converter = new HibernateCriteriaConverter<ProductEntity>(this.entityManager.getCriteriaBuilder());
        var hibernateCriteria = converter.convert(criteria, ProductEntity.class);

        TypedQuery<ProductEntity> query = this.entityManager.createQuery(hibernateCriteria);

        if(criteria.limit().isPresent()){
            query.setMaxResults(criteria.limit().get());
        }
        if(criteria.offset().isPresent()){
            query.setFirstResult(criteria.offset().get());
        }

        var result = query.getResultList();

        return result.stream().map(ProductMapper::entityToDomain).toList();
    }
}
