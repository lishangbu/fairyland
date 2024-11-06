package io.github.lishangbu.fairyland.account.action;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import io.github.lishangbu.common.core.constant.route.ModuleCmdConstants;
import io.github.lishangbu.fairyland.account.entity.Account;
import io.github.lishangbu.fairyland.account.entity.Token;
import io.github.lishangbu.fairyland.account.mapstruct.TokenMapstruct;
import io.github.lishangbu.fairyland.account.model.dto.LoginRequestParam;
import io.github.lishangbu.fairyland.account.model.vo.TokenInfo;
import io.github.lishangbu.fairyland.account.service.AccountService;
import io.github.lishangbu.fairyland.account.service.TokenStorage;
import io.github.lishangbu.fairyland.iogame.exception.IllegalArgumentMsgException;
import io.github.lishangbu.fairyland.iogame.model.ExternalConnectionAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.github.lishangbu.common.core.constant.route.account.AccountTokenSubCmdConstants.*;

/**
 * 登录服务
 *
 * @author lishangbu
 * @since 2024/10/29
 */
@Component
@ActionController(ModuleCmdConstants.Account.TOKEN)
@RequiredArgsConstructor
public class TokenAction {

    /**
     * 账户服务，用于处理与账户相关的业务逻辑。
     */
    private final AccountService accountService;

    private final TokenStorage tokenStorage;

    private final TokenMapstruct tokenMapstruct;

    /**
     * 处理用户登录请求的操作方法
     *
     * @param loginRequestParam 登录请求参数，包括用户名和密码
     * @param flowContext       流程上下文，包含请求的上下文信息
     * @return Token 令牌信息对象，包含登录成功后的用户名
     * @throws IllegalArgumentMsgException 可能抛出异常（例如，登录失败或无效凭证）
     */
    @ActionMethod(LOGIN)
    public TokenInfo login(LoginRequestParam loginRequestParam, FlowContext flowContext) {
        Account account = accountService.getAndValidateAccount(loginRequestParam.getUsername(), loginRequestParam.getPassword());
        final Long userId = account.getId();
        flowContext.setUserId(userId);
        Token token = tokenStorage.getByUserId(userId);
        if (token == null) {
            token = new Token();
        }
        token.setUserId(account.getId());
        token.setUsername(account.getUsername());
        token.setTokenValue(UUID.randomUUID().toString());
        tokenStorage.save(token);
        flowContext.updateAttachment(tokenMapstruct.toTokenAttachment(token));
        return tokenMapstruct.toTokenInfo(token);
    }

    /**
     * 处理通过令牌登录的请求。
     *
     * @param flowContext 当前的流程上下文，包含请求头的元数据。
     * @return Token 对象，包含解码后的令牌信息。
     * <code>@ActionMethod</code> 注解指定此方法对应的操作类型为 GET_TOKEN_INFO，
     * 用于从流程上下文中提取并解码令牌信息。
     * <p>
     * 该方法从请求的头元数据中获取附加数据，并使用 DataCodecKit
     * 将其解码为 Token 对象
     */
    @ActionMethod(VALIDATE_TOKEN)
    public Boolean validateToken(FlowContext flowContext) {
        ExternalConnectionAttachment externalConnectionAttachment = flowContext.getAttachment(ExternalConnectionAttachment.class);
        if (externalConnectionAttachment == null || externalConnectionAttachment.getToken() == null || externalConnectionAttachment.getToken().isEmpty()) {
            // 令牌信息不存在
            return false;
        } else {
            Token token = tokenStorage.getByTokenValue(externalConnectionAttachment.getToken());
            if (token == null) {
                return false;
            } else {
                flowContext.setUserId(token.getUserId());
                flowContext.updateAttachment(tokenMapstruct.toTokenAttachment(token));
                return true;
            }
        }
    }

    @ActionMethod(LOGOUT)
    public void logout(FlowContext flowContext) {
        if (flowContext.getUserId() != 0L) {
            tokenStorage.removeByUserId(flowContext.getUserId());
        }
    }


}