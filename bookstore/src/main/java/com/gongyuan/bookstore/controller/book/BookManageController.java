package com.gongyuan.bookstore.controller.book;

import com.gongyuan.bookstore.controller.common.CommonResult;
import com.gongyuan.bookstore.controller.common.PageData;
import com.gongyuan.bookstore.controller.common.ResultCode;
import com.gongyuan.bookstore.model.bo.BookBO;
import com.gongyuan.bookstore.model.dto.BookDTO;
import com.gongyuan.bookstore.service.book.BookManageService;
import com.gongyuan.bookstore.util.validation.ValidEnumName;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * BookController
 *
 * @author: gongyuan
 * @date: 2024/8/8 21:36
 */
@AllArgsConstructor
@RestController
@RequestMapping("/book")
@Validated
public class BookManageController {

    private final BookManageService bookManageService;

    private final BookBO.Converter converter;

    @GetMapping("/{id}")
    public CommonResult<BookDTO> queryBookInfo(@PathVariable("id") @Positive(message = "wrong id") long id) {
        BookBO book = bookManageService.queryBookInfoById(id);
        if (Objects.isNull(book)) {
            return CommonResult.failed(ResultCode.BOOK_NOT_EXIST);
        }
        return CommonResult.successful(converter.toDTO(book));
    }

    @DeleteMapping("/{id}")
    public CommonResult<Boolean> deleteBook(@PathVariable("id") @Positive(message = "wrong id") long id) {
        boolean success = bookManageService.deleteById(id);
        return success ? CommonResult.successful() : CommonResult.failed(ResultCode.BOOK_DELETE_FAILED);
    }


    @PutMapping("/{id}")
    public CommonResult<Boolean> updateBook(@RequestBody BookUpdateRequest request, @PathVariable("id") @Positive(message = "wrong id") long id) {
        boolean success = bookManageService.updateBasicInfoById(request, id);
        return success ? CommonResult.successful() : CommonResult.failed(ResultCode.BOOK_UPDATE_FAILED);
    }


    @PostMapping
    public CommonResult<Long> addBook(@Valid @RequestBody BookAddRequest request) {
        long id = bookManageService.addBook(request);
        return id > 0 ? CommonResult.successful(id) : CommonResult.failed(ResultCode.BOOK_INSERT_FAILED);
    }

    @PutMapping("/{id}/{stockAmount}")
    public CommonResult<Boolean> updateStock(@PathVariable("id") @Positive(message = "wrong id") long id,
                                             @PathVariable("stockAmount") int stockAmount) {
        checkArgument(stockAmount != 0, "stockAmount cannot be zero");
        boolean success = bookManageService.updateStock(id, stockAmount);
        return success ? CommonResult.successful() : CommonResult.failed(ResultCode.BOOK_UPDATE_FAILED);
    }


    @PutMapping("/status/{id}/{event}")
    public CommonResult<Boolean> updateStatus(@PathVariable("id") @Positive(message = "wrong id") long id,
                                             @PathVariable("event") @ValidEnumName(BookEvent.class) String event) {
        boolean success = bookManageService.updateStatus(id, BookEvent.valueOf(event));
        return success ? CommonResult.successful() : CommonResult.failed(ResultCode.BOOK_UPDATE_FAILED);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public CommonResult<PageData<BookBO>> queryBookInfo(
            @PathVariable("pageNumber") @Positive(message = "wrong pageNumber") int pageNumber,
            @PathVariable("pageSize") @Positive(message = "wrong pageSize") @Max(value = 20, message = "pageSize is too large") int pageSize
    ) {
        PageData<BookBO> pageData = bookManageService.pageQuery(pageNumber, pageSize);
        if (Objects.isNull(pageData)) {
            return CommonResult.failed(ResultCode.BOOK_NOT_EXIST);
        }
        return CommonResult.successful(pageData);
    }
}
