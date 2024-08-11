package com.gongyuan.bookstore.util.mapstruct;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.gongyuan.bookstore.util.LocalDateTimeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: gongyuan
 * @date: 2024/8/10 15:14
 */
@Component
public class CommonMapStructConverter {

    /**
     * strToMap
     *
     * @param str
     * @return
     */
    public Map<String, Object> jsonStrToMap(String str) {
        if (StringUtils.isBlank(str)) {
            return Maps.newHashMap();
        }
        return JSON.parseObject(str, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * mapToStr
     *
     * @param map
     * @return
     */
    public String mapToJsonStr(Map<String, Object> map) {
        if (Objects.isNull(map)) {
            map = Collections.emptyMap();
        }
        return JSON.toJSONString(map);
    }

    public LocalDate longToLocalDate(Long timestamp) {
        return LocalDateTimeUtil.longToLocalDate(timestamp);
    }

    public Long localDateToLong(LocalDate date) {
        return LocalDateTimeUtil.localDateToLong(date);
    }

    public LocalDateTime longToLocalDateTime(Long timestamp) {
        return LocalDateTimeUtil.longToLocalDateTime(timestamp);
    }

    public Long localDateTimeTolong(LocalDateTime dateTime) {
        return LocalDateTimeUtil.localDateTimeToLong(dateTime);
    }


    public String listToJsonStr(List<String> list) {
        if (Objects.isNull(list)) {
            list = Collections.emptyList();
        }
        return JSON.toJSONString(list);
    }

    public List<String> jsonStrTolist(String str) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        return JSON.parseObject(str, new TypeReference<List<String>>() {
        });
    }


}
