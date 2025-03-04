package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        List<SearchResult> productResults = storageService.getAllProducts().stream().filter(product -> product.getSearchTerm().toLowerCase().contains(pattern.toLowerCase())).map(SearchResult::fromSearchable).collect(Collectors.toList());
        List<SearchResult> articleResults = storageService.getAllArticles().stream().filter(article -> article.getSearchTerm().toLowerCase().contains(pattern.toLowerCase())).map(SearchResult::fromSearchable).collect(Collectors.toList());

        productResults.addAll(articleResults);
        return productResults;
    }
}
