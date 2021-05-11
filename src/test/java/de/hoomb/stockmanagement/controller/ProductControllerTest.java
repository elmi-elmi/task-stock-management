package de.hoomb.stockmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hoomb.stockmanagement.model.Product;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductControllerTest {

    @Resource
    private MockMvc mockMvc;

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final String BASE_URL = "/api/product/";

    @Test
    public void getProductInitialStock() throws Exception {
        mockMvc.perform(get(BASE_URL + DEFAULT_PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"stock\":100")));
    }

    @Test
    public void refillProduct() throws Exception {
        mockMvc.perform(put(BASE_URL + DEFAULT_PRODUCT_ID + "/refill?amount=5"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"stock\":105")));
    }

    @Test
    public void buyProduct() throws Exception {
        mockMvc.perform(put(BASE_URL + DEFAULT_PRODUCT_ID + "/buy"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"stock\":99")));
    }

    @Test
    public void buyFiveProducts() throws Exception {
        mockMvc.perform(put(BASE_URL + DEFAULT_PRODUCT_ID + "/buy?amount=5"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"stock\":95")));
    }

    @Test
    public void buyProductsMoreThanStock() throws Exception {
        mockMvc.perform(put(BASE_URL + DEFAULT_PRODUCT_ID + "/buy?amount=200"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Not enough Product stock")));
    }
}
