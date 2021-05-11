package de.hoomb.stockmanagement.controller;

import de.hoomb.stockmanagement.exception.ProductNotFoundException;
import de.hoomb.stockmanagement.model.Product;
import de.hoomb.stockmanagement.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Product Rest Controller
 *
 * @author Hooman Behmanesh
 */

@RestController
@RequestMapping(value = "/api/product")
@CrossOrigin
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private ProductService productService;

    /**
     * returns the product for given Id.
     *
     * @param id Product Id which will be searched for.
     * @return found product
     */
    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") final Long id) {
        return productService.getProduct(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping
    public Iterable<Product> getProducts() {
        return productService.getProducts();
    }

    /**
     * returns the product stock for given Id.
     *
     * @param id Product Id which the stock will be searched for.
     * @return current product stock
     */
    @GetMapping(value = "/{id}/stock")
    public long getProductStock(@PathVariable("id") final Long id) {
        return productService.getProduct(id)
                .map(Product::getStock)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * refills (increase) the product stock for given Id.
     *
     * @param id     Product Id which the stock will be increased.
     * @param amount number of products that must be added
     * @return changed product stock
     */
    @PutMapping(value = "/{id}/refill")
    public Product refillProductStock(@PathVariable("id") final Long id, @RequestParam(value = "amount") final long amount) {
        return productService.refillProductStock(id, amount)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * decreases the product stock for given Id.
     * If there are less products than available stocks, this method
     * returns an Error.
     *
     * @param id     Product Id which the stock will be increased. Default value is "1"
     * @param amount number of products that a customer buys
     * @return changed product stock
     */
    @PutMapping(value = "/{id}/buy")
    public Product buyProduct(@PathVariable("id") final Long id, @RequestParam(value = "amount", defaultValue = "1") final long amount) {
        return productService.buyProduct(id, amount)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping(value = "/{id}/reserve")
    public Product reserveProduct(@PathVariable("id") final Long id, @RequestParam(value = "amount", defaultValue = "1") final long amount) {
        return productService.reserveProduct(id, amount)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PatchMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody final Product product) throws Exception {
        return productService.updateProduct(product);
    }
}
