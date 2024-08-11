package com.gongyuan.bookstore.dao;

import com.gongyuan.bookstore.model.po.UserPO;

/**
 * UserMapper
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:29
 */
public interface UserMapper {


    /**
     * selectById
     *
     * @param id
     * @return
     */
    UserPO selectById(long id);


    /**
     * selectByAccountNo
     *
     * @param accountNo
     * @return
     */
    UserPO selectByAccountNo(String accountNo);

    /**
     * updateById
     *
     * @param userPO
     * @return
     */
    int updateById(UserPO userPO);


    /**
     * selectByAccountNo
     *
     * @param userPO
     * @return
     */
    int insert(UserPO userPO);

}
