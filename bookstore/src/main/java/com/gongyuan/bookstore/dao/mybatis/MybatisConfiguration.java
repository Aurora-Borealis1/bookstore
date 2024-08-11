package com.gongyuan.bookstore.dao.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * springboot autoconfiguration
 * <p>
 * sqlSessionFactory
 * sqlSessionTemplate
 *
 * @author: gongyuan
 * @date: 2024/8/10 14:50
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
 */
@Configuration
@MapperScan(
        value = "com.gongyuan.bookstore.dao",
        sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfiguration {
}
