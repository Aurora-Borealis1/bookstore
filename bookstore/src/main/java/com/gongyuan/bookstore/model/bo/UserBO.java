package com.gongyuan.bookstore.model.bo;

import com.gongyuan.bookstore.controller.user.UserGenderType;
import com.gongyuan.bookstore.controller.user.UserManageRequest;
import com.gongyuan.bookstore.model.dto.UserDTO;
import com.gongyuan.bookstore.model.po.UserPO;
import com.gongyuan.bookstore.util.mapstruct.CommonMapStructConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * user business object
 *
 * @author: gongyuan
 * @date: 2024/8/10 10:39
 */
@AllArgsConstructor
@Builder
@Getter
public class UserBO {

    /**
     * primary key
     */
    private final long id;

    /**
     * accountNo
     */
    private final String accountNo;


    /**
     * passwordDigest
     */
    private final String passwordDigest;

    /**
     * nickname
     */
    private final String nickname;

    /**
     * icon
     */
    private final String icon;

    /**
     * gender
     */
    private final UserGenderType gender;

    /**
     * email
     */
    private final String email;

    /**
     * phone
     */
    private final String phone;

    /**
     * birthDay
     */
    private final LocalDate birthDay;

    /**
     * address
     */
    private final String address;

    /**
     * interestingTags
     */
    private final List<String> interestingTags;

    /**
     * extInfo
     */
    private final Map<String, Object> extInfo;


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CommonMapStructConverter.class)
    public interface Converter {

        /**
         * po to bo
         *
         * @param po
         * @return bo
         */
        UserBO fromPO(UserPO po);

        /**
         * bo to po
         *
         * @param bo
         * @return po
         */
        UserPO toPO(UserBO bo);

        /**
         * bo to dto
         *
         * @param bo
         * @return dto
         */
        UserDTO toDTO(UserBO bo);

        /**
         * request -> bo
         *
         * @param request
         * @return
         */
        @Mapping(target = "passwordDigest", expression = "java(com.gongyuan.bookstore.util.MD5Util.generateMD5(request.getPassword()))")
        UserBO fromRequest(UserManageRequest request);

    }


}
