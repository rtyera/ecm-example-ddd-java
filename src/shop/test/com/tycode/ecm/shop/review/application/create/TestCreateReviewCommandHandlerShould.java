package com.tycode.ecm.shop.review.application.create;

import com.tycode.ecm.shop.product.application.search_by_id.ProductNotExistException;
import com.tycode.ecm.shop.product.application.search_by_id.SearchByIdProduct;
import com.tycode.ecm.shop.product.domain.Product;
import com.tycode.ecm.shop.product.domain.ProductId;
import com.tycode.ecm.shop.product.domain.ProductMother;
import com.tycode.ecm.shop.product.domain.ProductRepository;
import com.tycode.ecm.shop.review.ReviewUnitTestCase;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

final class TestCreateReviewCommandHandlerShould extends ReviewUnitTestCase {
    private CreateReviewHandler reviewHandler;
    private ProductRepository productRepository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        productRepository= mock(ProductRepository.class);
        reviewHandler = new CreateReviewHandler(
                new CreateReview(reviewRepository, new SearchByIdProduct(productRepository))
        );
    }

    @Test
    void create_a_valid_review() {

        CommandCreateReview command = CommandCreateReviewMother.random();
        Review review      = ReviewMother.fromRequest(command);
        Product product = ProductMother.fromId(command.productId());

        shouldFindProduct(command.productId(), product);
        reviewHandler.handle(command);

        shouldHaveSaved(review);
    }

    @Test
    void not_exist_product_for_review() {
        CommandCreateReview command = CommandCreateReviewMother.random();
        shouldNotFindProduct(command.productId());

        Exception exception = Assertions.assertThrows(ProductNotExistException.class, () -> {
            reviewHandler.handle(command);
        });

        String expectedMessage = String.format("The Product with id %s does not exist", command.productId());
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    private void shouldFindProduct(String id, Product product){
       Mockito.when(productRepository.findById(new ProductId(id))).thenReturn(Optional.of(product));
    }

    private void shouldNotFindProduct(String id){
        Mockito.when(productRepository.findById(new ProductId(id))).thenReturn(Optional.empty());
    }
}
