package de.hoomb.stockmanagement.service;

import de.hoomb.stockmanagement.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductServiceTest {

    @Resource
    private ProductService productService;

    private static final Long DEFAULT_PRODUCT_ID = 1L;

    @Test
    public void getProductStockTest() {
        final Optional<Product> productStock = productService.getProduct(DEFAULT_PRODUCT_ID);

        assertThat(productStock.isPresent()).isTrue();
        assertThat(productStock.get().getStock()).isEqualTo(100L);
    }

    @Test
    public void refillProductStockTest() {
        final Optional<Product> productStock = productService.refillProductStock(DEFAULT_PRODUCT_ID, 10);

        assertThat(productStock.isPresent()).isTrue();
        assertThat(productStock.get().getStock()).isEqualTo(110L);
    }

    @Test
    public void buyProductTest() {
        final Optional<Product> productStock = productService.buyProduct(DEFAULT_PRODUCT_ID, 30);

        assertThat(productStock.isPresent()).isTrue();
        assertThat(productStock.get().getStock()).isEqualTo(70L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyProductExceededStockTest() {
        productService.buyProduct(DEFAULT_PRODUCT_ID, 200);
    }
}
