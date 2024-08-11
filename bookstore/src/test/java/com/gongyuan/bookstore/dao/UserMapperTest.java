package com.gongyuan.bookstore.dao;

import com.gongyuan.bookstore.BookstoreApplicationTests;
import com.gongyuan.bookstore.model.po.UserPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * @author: gongyuan
 * @date: 2024/8/10 16:12
 */
public class UserMapperTest extends BookstoreApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        Assertions.assertNotNull(userMapper);
    }


    @Test
    public void testInsert() {
        int insert = userMapper.insert(createUser("gongyuan"));
        Assertions.assertEquals(1, insert);
    }

    @Test
    public void testSelect() {

        String accountNo = "parker";
        userMapper.insert(createUser(accountNo));
        UserPO userPO = userMapper.selectByAccountNo(accountNo);
        Assertions.assertNotNull(userPO);
        Assertions.assertEquals(userPO.getAccountNo(), accountNo);
        userPO = userMapper.selectById(userPO.getId());
        Assertions.assertNotNull(userPO);
        Assertions.assertEquals(userPO.getAccountNo(), accountNo);
    }

    @Test
    public void testUpdate() {

        String accountNo = "peter";
        userMapper.insert(createUser(accountNo));

        UserPO userPO = userMapper.selectByAccountNo(accountNo);
        userPO.setNickname("peter");

        userMapper.updateById(userPO);
        userPO = userMapper.selectByAccountNo(accountNo);
        Assertions.assertEquals(userPO.getNickname(), "peter");
    }


    private UserPO createUser(String accountNo) {
        UserPO user = new UserPO();
        user.setAccountNo(accountNo);
        user.setPasswordDigest("hashed_password");
        user.setNickname("gongyuan");
        user.setIcon("https://example.com/avatar.jpg");
        user.setGender("MALE");
        user.setEmail("john.doe@example.com");
        user.setBirthDay(LocalDate.of(1994, 1, 1));
        user.setAddress("123 Main St, Anytown, USA");
        user.setInterestingTags("sports,travel,books");
        user.setExtInfo("{}");
        return user;
    }

}
