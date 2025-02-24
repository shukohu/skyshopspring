package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Product implements Searchable {
    private final UUID id;
    private final String name;

    public Product(UUID id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return name;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "product";
    }
}
