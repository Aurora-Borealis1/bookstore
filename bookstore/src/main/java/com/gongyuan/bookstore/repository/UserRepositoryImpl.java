package com.gongyuan.bookstore.repository;

import com.gongyuan.bookstore.dao.UserMapper;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.model.po.UserPO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * UserRepository Implement
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:21
 */
@Repository
@AllArgsConstructor
class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    private final UserBO.Converter converter;

    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public UserBO queryUserById(long id) {
        UserPO userPO = userMapper.selectById(id);
        return converter.fromPO(userPO);
    }


    @Override
    @Cacheable(cacheNames = "user", key = "#accountNo")
    public UserBO queryUserByAccountNo(String accountNo) {
        checkArgument(StringUtils.isNotBlank(accountNo), "accountNo cannot be blank");
        UserPO userPO = userMapper.selectByAccountNo(accountNo);
        return converter.fromPO(userPO);
    }

    @Override
    public boolean insertUser(UserBO user) {
        UserPO userPO = converter.toPO(checkNotNull(user, "user cannot be null"));
        return userMapper.insert(userPO) == 1;
    }

    @Override
    @CacheEvict(cacheNames = "user", key = "#id")
    public boolean updateById(UserBO user, long id) {
        UserPO userPO = converter.toPO(checkNotNull(user, "user cannot be null"));
        userPO.setId(id);
        return userMapper.updateById(userPO) == 1;
    }

}
