package com.gongyuan.bookstore.dao;

import com.gongyuan.bookstore.model.po.BookPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BookMapper
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:29
 */
public interface BookMapper {

    int insert(BookPO book);

    int updateStock(@Param("book") BookPO book, @Param("stockAmount") int stockAmount);

    BookPO selectById(long id);

    int updateById(BookPO book);

    int deleteById(long id);

    List<BookPO> select();

}
