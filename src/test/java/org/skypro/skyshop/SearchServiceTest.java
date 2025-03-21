package org.skypro.skyshop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void whenStorageIsEmpty_thenSearchReturnsEmptyList() {
        Mockito.doReturn(Collections.emptyList()).when(storageService).getAllProducts();
        List<Product> result = searchService.searchByName("Тест");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void whenMatchingProductExist_thenSearchReturnsEmptyList() {
        Product product1 = new Product(UUID.randomUUID(), "Чай", 20);
        Product product2 = new Product(UUID.randomUUID(), "Пицца", 320);
        Mockito.doReturn(List.of(product1, product2)).when(storageService).getAllProducts();
        List<Product> result = searchService.searchByName("Кофе");

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void whenMatchingProductExists_thenSearchReturnsCorrectProduct() {
        Product product1 = new Product(UUID.randomUUID(), "Чай", 20);
        Product product2 = new Product(UUID.randomUUID(), "Пицца", 320);
        Mockito.doReturn(List.of(product1, product2)).when(storageService).getAllProducts();
        List<Product> result = searchService.searchByName("Пицца");
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Пицца", result.get(0).getName());
    }
}
