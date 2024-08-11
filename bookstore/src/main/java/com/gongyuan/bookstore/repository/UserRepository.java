package com.gongyuan.bookstore.repository;

import com.gongyuan.bookstore.model.bo.UserBO;

/**
 * UserRepository
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:19
 */
public interface UserRepository {

    /**
     * query user by id
     *
     * @param id
     * @return
     */
    UserBO queryUserById(long id);

    /**
     * query user by accountNo
     *
     * @param accountNo
     * @return
     */
    UserBO queryUserByAccountNo(String accountNo);

    /**
     * insertUser
     *
     * @param user
     * @return
     */
    boolean insertUser(UserBO user);


    /**
     * updateById
     *
     * @param user
     * @return
     */
    boolean updateById(UserBO user, long id);

}
