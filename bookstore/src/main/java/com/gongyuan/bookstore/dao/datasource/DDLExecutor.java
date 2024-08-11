package com.gongyuan.bookstore.dao.datasource;

import com.gongyuan.bookstore.dao.TableCreatorMapper;
import org.springframework.stereotype.Component;

/**
 * @author: gongyuan
 * @date: 2024/8/10 16:32
 */
@Component
public class DDLExecutor {

    public DDLExecutor(TableCreatorMapper tableCreatorMapper) {
        tableCreatorMapper.createUserInfo();
        tableCreatorMapper.createBookInfo();
        tableCreatorMapper.createOrderInfo();
    }
}
