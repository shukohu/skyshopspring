package org.skypro.skyshop.service;


import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isPresent()) {
            productBasket.addProduct(id);
        } else {
            throw new IllegalArgumentException("Товар не найден.");
        }
    }
    public UserBasket getUserBasket() {
        List<BasketItem> items = productBasket.getProducts().entrySet().stream().map(entry -> storageService.getProductById(entry.getKey()).map(product -> new BasketItem(product, entry.getValue()))).flatMap(java.util.Optional::stream).toList();
        return new UserBasket(items);
    }
}
