package com.gongyuan.bookstore.model.po;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: gongyuan
 * @date: 2024/8/10 21:16
 */
@Data
public class BookPO {

    /**
     * id
     */
    private long id;

    /**
     * create time
     */
    private LocalDateTime gmtCreateTime;

    /**
     * update time
     */
    private LocalDateTime gmtUpdateTime;

    /**
     * the cover of a book
     */
    private String cover;
    /**
     * title
     */
    private String title;
    /**
     * author
     */
    private String author;
    /**
     * translator
     */
    private String translator;
    /**
     * publishingHouse
     */
    private String publishingHouse;
    /**
     * publicationDate
     */
    private LocalDate publicationDate;
    /**
     * publish version
     */
    private Integer version;
    /**
     * price
     */
    private BigDecimal price;
    /**
     * currency
     */
    private String currency;
    /**
     * stock
     */
    private Integer stock;
    /**
     * tags,json schema
     */
    private String tags;
    /**
     * briefIntroduction
     */
    private String briefIntroduction;
    /**
     * catalogue
     */
    private String catalogue;

    /**
     * language of the book
     */
    private String language;

    /**
     * status
     */
    private String status;

    /**
     * extInfo
     */
    private String extInfo;
}
