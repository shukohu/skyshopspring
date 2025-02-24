package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StorageService {
    private final Map<UUID, Product> productStorage = new HashMap<>();
    private final Map<UUID, Article> articleStorage = new HashMap<>();

    public StorageService() {
        initializeStorage();
    }

    private void initializeStorage() {
        Product product1 = new Product(UUID.randomUUID(), "Пицца");
        Product product2 = new Product(UUID.randomUUID(), "Чай");
        productStorage.put(product1.getId(), product1);
        productStorage.put(product2.getId(), product2);

        Article article1 = new Article(UUID.randomUUID(), "Статья о пицце");
        Article article2 = new Article(UUID.randomUUID(), "Статья о чае");
        articleStorage.put(article1.getId(), article1);
        articleStorage.put(article2.getId(), article2);
    }

    public Collection<Product> getAllProducts() {
        return productStorage.values();
    }

    public Collection<Article> getAllArticles() {
        return articleStorage.values();
    }
}
