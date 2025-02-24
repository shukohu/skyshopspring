package org.skypro.skyshop.model.article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;


public class Article implements Searchable {
    private final UUID id;
    private final String title;

    public Article(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title;
    }


}
