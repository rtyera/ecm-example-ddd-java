package com.tycode.ecm.shop.review.infrastructure.persistence.hibernate;

import com.tycode.ecm.shared.domain.criteria.Criteria;
import com.tycode.ecm.shared.infrastructure.hibernate.HibernateCriteriaConverter;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewId;
import com.tycode.ecm.shop.review.domain.ReviewRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReviewHibernateRepository implements ReviewRepository {

    private final ReviewEntityRepository reviewEntityRepository;
    private final EntityManager entityManager;

    @Override
    public Optional<Review> findById(ReviewId reviewId) {
        return this.reviewEntityRepository.findById(reviewId.value()).map(ReviewMapper::entityToDomain);
    }

    @Override
    public List<Review> findAll() {
        return this.reviewEntityRepository.findAll()
                .stream()
                .map(ReviewMapper::entityToDomain)
                .toList();
    }

    @Override
    public void save(Review review) {
        var channelStatusEntity = ReviewMapper.domainToEntity(review);
        this.reviewEntityRepository.save(channelStatusEntity);
    }

    @Override
    public void remove(Review review) {
        var channelStatusEntity = ReviewMapper.domainToEntity(review);
        this.reviewEntityRepository.delete(channelStatusEntity);
    }

    @Override
    public List<Review> matching(Criteria criteria){
        var converter = new HibernateCriteriaConverter<ReviewEntity>(this.entityManager.getCriteriaBuilder());
        var hibernateCriteria = converter.convert(criteria, ReviewEntity.class);

        TypedQuery<ReviewEntity> query = this.entityManager.createQuery(hibernateCriteria);

        if(criteria.limit().isPresent()){
            query.setMaxResults(criteria.limit().get());
        }
        if(criteria.offset().isPresent()){
            query.setFirstResult(criteria.offset().get());
        }

        var result = query.getResultList();

        return result.stream().map(ReviewMapper::entityToDomain).toList();
    }
}
