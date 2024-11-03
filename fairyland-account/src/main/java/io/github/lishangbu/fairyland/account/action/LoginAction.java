package io.github.lishangbu.fairyland.account.action;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import io.github.lishangbu.common.core.constant.LoginModuleFunctionConstants;
import io.github.lishangbu.common.core.constant.ModuleConstants;
import io.github.lishangbu.fairyland.account.entity.Account;
import io.github.lishangbu.fairyland.account.mapstruct.TokenMapper;
import io.github.lishangbu.fairyland.account.model.LoginRequestParam;
import io.github.lishangbu.fairyland.account.model.RegisterRequestParam;
import io.github.lishangbu.fairyland.account.model.TokenInfo;
import io.github.lishangbu.fairyland.account.service.AccountService;
import io.github.lishangbu.fairyland.account.util.JwtUtils;
import io.github.lishangbu.fairyland.iogame.exception.IllegalArgumentMsgException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * 登录服务
 *
 * @author lishangbu
 * @since 2024/10/29
 */
@Component
@ActionController(ModuleConstants.LOGIN)
@RequiredArgsConstructor
public class LoginAction {
    private final AccountService accountService;

    /**
     * 处理用户登录请求的操作方法
     *
     * @param loginRequestParam 登录请求参数，包括用户名和密码
     * @param flowContext       流程上下文，包含请求的上下文信息
     * @return TokenInfo 令牌信息对象，包含登录成功后的用户名
     * @throws IllegalArgumentMsgException 可能抛出异常（例如，登录失败或无效凭证）
     */
    @ActionMethod(LoginModuleFunctionConstants.LOGIN)
    public TokenInfo login(LoginRequestParam loginRequestParam, FlowContext flowContext) {
        Account account = accountService.login(loginRequestParam.getUsername(), loginRequestParam.getPassword());
        final Long userId = account.getId();
        flowContext.setUserId(userId);
        TokenInfo tokenInfo = Mappers.getMapper(TokenMapper.class).toUserInfo(account, JwtUtils.createToken(String.valueOf(userId)));
        flowContext.updateAttachment(tokenInfo);
        return tokenInfo;
    }

    @ActionMethod(LoginModuleFunctionConstants.REGISTER)
    public Account register(RegisterRequestParam registerRequestParam) {
        return accountService.register(registerRequestParam.getUsername(), registerRequestParam.getPassword());
    }


    /**
     * 处理获取令牌信息的请求。
     *
     * @param flowContext 当前的流程上下文，包含请求头的元数据。
     * @return TokenInfo 对象，包含解码后的令牌信息。
     * <code>@ActionMethod</code> 注解指定此方法对应的操作类型为 GET_TOKEN_INFO，
     * 用于从流程上下文中提取并解码令牌信息。
     * <p>
     * 该方法从请求的头元数据中获取附加数据，并使用 DataCodecKit
     * 将其解码为 TokenInfo 对象
     */
    @ActionMethod(LoginModuleFunctionConstants.GET_TOKEN_INFO)
    public TokenInfo getTokenInfo(FlowContext flowContext) {
        return flowContext.getAttachment(TokenInfo.class);
    }
}