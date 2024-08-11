package com.gongyuan.bookstore.model.bo;

import com.gongyuan.bookstore.controller.book.BookAddRequest;
import com.gongyuan.bookstore.controller.book.BookStatus;
import com.gongyuan.bookstore.controller.book.BookUpdateRequest;
import com.gongyuan.bookstore.controller.book.CurrencyType;
import com.gongyuan.bookstore.controller.book.LanguageType;
import com.gongyuan.bookstore.model.dto.BookDTO;
import com.gongyuan.bookstore.model.po.BookPO;
import com.gongyuan.bookstore.util.mapstruct.CommonMapStructConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/10 21:24
 */
@AllArgsConstructor
@Builder
@Getter
public class BookBO {

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
    private final LocalDate publicationDate;
    /**
     * publish version
     */
    private final Integer version;
    /**
     * price
     */
    private final BigDecimal price;
    /**
     * currency
     */
    private final CurrencyType currency;
    /**
     * stock
     */
    private final Integer stock;
    /**
     * tags,json schema
     */
    private final List<String> tags;
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
    private final LanguageType language;
    /**
     * status
     */
    private final BookStatus status;

    /**
     * extInfo
     */
    private final Map<String, Object> extInfo;


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CommonMapStructConverter.class)
    public interface Converter {

        /**
         * po to bo
         *
         * @param po
         * @return bo
         */
        BookBO fromPO(BookPO po);


        /**
         * po to bo
         *
         * @param po
         * @return bo
         */
        List<BookBO> fromPO(List<BookPO> po);

        /**
         * requet to bo
         *
         * @param request
         * @return
         */
        @Mapping(target = "status", constant = "OFFLINE")
        BookBO fromRequest(BookAddRequest request);

        /**
         * requet to bo
         *
         * @param request
         * @return
         */
        BookBO fromRequest(BookUpdateRequest request);

        /**
         * bo to po
         *
         * @param bo
         * @return po
         */
        BookPO toPO(BookBO bo);


        /**
         * bo to dto
         *
         * @param bo
         * @return dto
         */
        BookDTO toDTO(BookBO bo);


    }
}
