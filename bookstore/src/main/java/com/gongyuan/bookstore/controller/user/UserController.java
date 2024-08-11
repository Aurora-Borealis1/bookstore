package com.gongyuan.bookstore.controller.user;

import com.gongyuan.bookstore.controller.common.CommonResult;
import com.gongyuan.bookstore.controller.common.ResultCode;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.model.dto.UserDTO;
import com.gongyuan.bookstore.service.user.UserService;
import com.gongyuan.bookstore.util.SessionUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.Objects;

/**
 * UserController
 *
 * @author: gongyuan
 * @date: 2024/8/8 21:36
 */
@RestController
@RequestMapping("/user")
@Validated
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserBO.Converter converter;

    @PostMapping
    public CommonResult<Boolean> register(@Valid @RequestBody UserManageRequest request) {
        boolean success = userService.registerUser(request);
        return success ? CommonResult.successful() : CommonResult.failed(ResultCode.USER_REGISTER_FAILED);
    }

    @GetMapping
    public CommonResult<UserDTO> queryUserInfo() throws AuthenticationException {
        UserBO user = getSessionUser();
        return CommonResult.successful(converter.toDTO(user));
    }

    @PostMapping("/login")
    public CommonResult<String> login(@Valid @RequestBody UserLoginRequest request) {
        String token = userService.login(request);
        return StringUtils.isNotBlank(token) ? CommonResult.successful(token) : CommonResult.failed(ResultCode.LOGIN_FAILED);
    }

    @PutMapping
    public CommonResult<UserDTO> updateUserInfo(@RequestBody UserManageRequest request) throws AuthenticationException {
        getSessionUser();
        boolean success = userService.updateUserInfo(request);
        return success ? CommonResult.successful() : CommonResult.failed(ResultCode.USER_UPDATE_FAILED);
    }

    private UserBO getSessionUser() throws AuthenticationException {
        UserBO user = SessionUtil.currentUser();
        if (Objects.isNull(user)) {
            throw new AuthenticationException(ResultCode.NO_TOKEN.name());
        }
        return user;
    }

}
