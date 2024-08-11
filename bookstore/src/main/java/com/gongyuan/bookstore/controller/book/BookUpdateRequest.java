package com.gongyuan.bookstore.controller.book;

import com.gongyuan.bookstore.util.validation.ValidEnumName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: gongyuan
 * @date: 2024/8/11 14:55
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BookUpdateRequest extends BookAddRequest implements Serializable {

    @ValidEnumName(value = BookStatus.class, enableBlankStr = true)
    private String status;
}
