package com.gongyuan.bookstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: gongyuan
 * @date: 2024/8/10 21:14
 */
@AllArgsConstructor
@Builder
@Getter
@ToString
public class BookDTO implements Serializable {
    /**
     * id
     */
    private final long id;

    /**
     * the cover of a book
     */
    private final String cover;
    /**
     * title
     */
    private final String title;
    /**
     * author
     */
    private final String author;
    /**
     * translator
     */
    private final String translator;
    /**
     * publishingHouse
     */
    private final String publishingHouse;
    /**
     * publicationDate
     */
    private final long publicationDate;
    /**
     * publish version
     */
    private final int version;
    /**
     * price
     */
    private final BigDecimal price;
    /**
     * currency
     */
    private final String currency;
    /**
     * stock
     */
    private final int stock;
    /**
     * tags,json schema
     */
    private final String tags;
    /**
     * briefIntroduction
     */
    private final String briefIntroduction;
    /**
     * catalogue
     */
    private final String catalogue;

    /**
     * language of the book
     */
    private final String language;

    /**
     * status
     */
    private final String status;
}
