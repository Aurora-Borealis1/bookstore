package com.gongyuan.bookstore.controller.book;

import com.gongyuan.bookstore.util.validation.ValidEnumName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: gongyuan
 * @date: 2024/8/10 23:39
 */
@Data
public class BookAddRequest implements Serializable {

    private long id;
    /**
     * the cover of a book
     */
    @NotBlank(message = "cover cannot be blank")
    private String cover;
    /**
     * title
     */
    @NotBlank(message = "title cannot be blank")
    private String title;
    /**
     * author
     */
    @NotBlank(message = "author cannot be blank")
    private String author;
    /**
     * translator
     */
    private String translator;
    /**
     * publishingHouse
     */
    @NotBlank(message = "publishingHouse cannot be blank")
    private String publishingHouse;
    /**
     * publicationDate
     */
    @NotNull(message = "publicationDate cannot be null")
    @Positive(message = "publicationDate must be positive")
    private Long publicationDate;
    /**
     * publish version
     */
    @NotNull(message = "version cannot be null")
    @Positive(message = "version must be positive")
    private Integer version;
    /**
     * price
     */
    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be positive")
    private BigDecimal price;
    /**
     * currency
     */
    @ValidEnumName(value = CurrencyType.class, message = "currency is wrong type")
    private String currency;
    /**
     * stock
     */
    @NotNull(message = "stock cannot be null")
    @Positive(message = "stock must be positive")
    private Integer stock;
    /**
     * tags,json schema
     */
    private String tags;
    /**
     * briefIntroduction
     */
    @NotBlank(message = "briefIntroduction cannot be blank")
    private String briefIntroduction;
    /**
     * catalogue
     */
    @NotBlank(message = "catalogue cannot be blank")
    private String catalogue;

    /**
     * language of the book
     */
    @ValidEnumName(value = LanguageType.class, message = "language is wrong type")
    private String language;
}
