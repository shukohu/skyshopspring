package org.skypro.skyshop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;
    @InjectMocks
    private BasketService basketService;

    @Test
    void whenProductDoesNotExist_thenExceptionIsThrown() {
        UUID id = UUID.randomUUID();

        Mockito.doReturn(Optional.empty()).when(storageService).getProductById(ArgumentMatchers.eq(id));
        Assertions.assertThrows(NoSuchProductException.class, () -> {
            basketService.addProduct(id);
        });
        Mockito.verifyNoInteractions(productBasket);
    }

    @Test
    void whenProductExist_thenItIsAddedToBasket() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Чай", 20);

        Mockito.doReturn(Optional.of(product)).when(storageService).getProductById(ArgumentMatchers.eq(id));
        basketService.addProduct(id);
        Mockito.verify(productBasket).addProduct(id);
    }

    @Test
    void whenBasketIsEmpty_thenProductBasketIsEmpty() {
        Mockito.doReturn(Collections.emptyMap()).when(productBasket).getProducts();
        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertTrue(userBasket.getItems().isEmpty());
        Assertions.assertEquals(0, userBasket.getTotal());
    }

    @Test
    void whenProductBasketHasProducts_thenReturnCorrectBasket() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Пицца", 320);
        Map<UUID, Integer> basketContents = Map.of(id, 2);
        Mockito.doReturn(basketContents).when(productBasket).getProducts();
        Mockito.doReturn(Optional.of(product)).when(storageService).getProductById(ArgumentMatchers.eq(id));
        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertFalse(userBasket.getItems().isEmpty());
        Assertions.assertEquals(100, userBasket.getTotal());
    }
}
