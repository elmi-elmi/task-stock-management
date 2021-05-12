package de.hoomb.stockmanagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hoomb.stockmanagement.model.Product;
import de.hoomb.stockmanagement.repository.ProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class StockManagementApplication {

    @Resource
    private ProductRepository repository;

    @Resource
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(StockManagementApplication.class, args);
    }

    @Bean
    @Profile("!test")
    InitializingBean initDatabase() {
        return () -> {
            System.out.println(environment.getActiveProfiles());

            final ObjectMapper mapper = new ObjectMapper();
            final TypeReference<List<Product>> typeReference = new TypeReference<>() {
            };
            try (final InputStream inputStream = TypeReference.class.getResourceAsStream("/mock_data.json")) {
                final List<Product> products = mapper.readValue(inputStream, typeReference);

                repository.saveAll(products);
            }

            repository.save(new Product("Test Product", 100, new HashSet<>(), 0));
        };
    }

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilterRegistration() {
        final FilterRegistrationBean<ShallowEtagHeaderFilter> result = new FilterRegistrationBean<>();

        result.setFilter(this.shallowEtagHeaderFilter());
        result.addUrlPatterns("/api/*");
        result.setName("shallowEtagHeaderFilter");
        result.setOrder(1);

        return result;
    }
}
