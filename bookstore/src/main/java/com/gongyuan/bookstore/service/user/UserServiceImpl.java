package com.gongyuan.bookstore.service.user;

import com.gongyuan.bookstore.controller.user.LoginType;
import com.gongyuan.bookstore.controller.user.TokenHelper;
import com.gongyuan.bookstore.controller.user.UserLoginRequest;
import com.gongyuan.bookstore.controller.user.UserManageRequest;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.repository.UserRepository;
import com.gongyuan.bookstore.util.SessionUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author: gongyuan
 * @date: 2024/8/10 12:12
 */
@Service
class UserServiceImpl implements UserService {

    private final Map<LoginType, UserLoginValidator> validatorMap;

    private final UserRepository userRepository;
    private final UserBO.Converter converter;

    private final TokenHelper tokenHelper;

    public UserServiceImpl(List<UserLoginValidator> loginValidators,
                           UserRepository userRepository,
                           UserBO.Converter converter,
                           TokenHelper tokenHelper
    ) {
        this.validatorMap = loginValidators.stream().collect(Collectors.toMap(
                UserLoginValidator::supportType,
                Function.identity(),
                (a, b) -> {
                    throw new IllegalStateException();
                }
                , () -> Maps.newEnumMap(LoginType.class)
        ));
        this.userRepository = userRepository;
        this.converter = converter;
        this.tokenHelper = tokenHelper;
    }

    /**
     * register user
     * use transaction to guarantee atomicity
     * register operator combines of two steps:
     * 1. query existing user info by accountNo
     * 2. register if there's no existing user
     *
     * @param request
     */
    @Override
    public boolean registerUser(UserManageRequest request) {
        String accountNo = request.getAccountNo();
        UserBO user = userRepository.queryUserByAccountNo(accountNo);
        checkArgument(Objects.isNull(user), "acountNo has been registered, please try another accountNo");
        user = converter.fromRequest(request);
        return userRepository.insertUser(user);
    }

    @Override
    public boolean updateUserInfo(UserManageRequest request) {
        UserBO currentUser = SessionUtil.currentUser();
        UserBO userBO = converter.fromRequest(request);
        return userRepository.updateById(userBO, currentUser.getId());
    }

    @Override
    public String login(UserLoginRequest request) {
        String userAccountNo = request.getUserAccountNo();
        String password = request.getPassword();
        String loginType = request.getLoginType();
        UserBO userBO = userRepository.queryUserByAccountNo(userAccountNo);
        if (Objects.isNull(userBO)) {
            return null;
        }
        boolean success = validatorMap.get(LoginType.valueOf(loginType)).validate(userAccountNo, password, userBO);
        if (!success) {
            return null;
        }
        return tokenHelper.generateToken(userBO.getId());
    }

}
