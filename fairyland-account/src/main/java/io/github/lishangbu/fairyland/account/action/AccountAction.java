package io.github.lishangbu.fairyland.account.action;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import io.github.lishangbu.common.core.constant.route.ModuleCmdConstants;
import io.github.lishangbu.common.core.constant.route.account.AccountUserSubCmdConstants;
import io.github.lishangbu.fairyland.account.mapstruct.AccountMapstruct;
import io.github.lishangbu.fairyland.account.model.dto.RegisterRequestParam;
import io.github.lishangbu.fairyland.account.model.vo.AccountInfo;
import io.github.lishangbu.fairyland.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 登录服务
 *
 * @author lishangbu
 * @since 2024/10/29
 */
@Component
@ActionController(ModuleCmdConstants.Account.USER)
@RequiredArgsConstructor
public class AccountAction {

    /**
     * 账户服务，用于处理与账户相关的业务逻辑。
     */
    private final AccountService accountService;

    /**
     * 账户映射结构，负责将账户数据对象与其 DTO（数据传输对象）之间进行转换。
     */
    private final AccountMapstruct accountMapstruct;

    /**
     * 注册一个新用户并返回其账户信息。
     *
     * @param registerRequestParam 包含注册所需的用户名和密码。
     * @return 返回包含新用户账户信息的 {@link AccountInfo} 对象。
     */
    @ActionMethod(AccountUserSubCmdConstants.REGISTER)
    public AccountInfo register(RegisterRequestParam registerRequestParam) {
        return accountMapstruct.toAccountInfo(accountService.register(registerRequestParam.getUsername(), registerRequestParam.getPassword()));
    }

}