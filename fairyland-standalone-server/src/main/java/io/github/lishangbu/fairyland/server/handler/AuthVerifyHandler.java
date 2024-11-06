package io.github.lishangbu.fairyland.server.handler;

import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.iohao.game.action.skeleton.protocol.RequestMessage;
import com.iohao.game.bolt.broker.core.aware.BrokerClientAware;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.external.core.netty.handler.ws.WebSocketVerifyHandler;
import com.iohao.game.external.core.netty.session.SocketUserSession;
import com.iohao.game.external.core.session.UserSessionOption;
import io.github.lishangbu.common.core.constant.route.ModuleCmdConstants;
import io.github.lishangbu.common.core.constant.route.account.AccountTokenSubCmdConstants;
import io.github.lishangbu.fairyland.iogame.model.ExternalConnectionAttachment;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * xxx
 *
 * @author lishangbu
 * @since 2024/11/1
 */
@Slf4j
public class AuthVerifyHandler extends WebSocketVerifyHandler implements BrokerClientAware {
    private BrokerClient brokerClient;


    /**
     * @param userSession ctx
     * @param params      params 验证参数，形如ws://127.0.0.1:10100/websocket?token=abc&name=aaaa能
     *                    取到参数 key:value 格式
     * @return 返回 true 表示验证通过，返回 false 框架会关闭连接,
     */
    @Override
    public boolean verify(SocketUserSession userSession, Map<String, String> params) {
        String token = params.getOrDefault("token", "");
        if (token.isEmpty()) {
            // 未登录时token可能不存在，此时放行验证
            return true;
        } else {
            // 存在值就直接放行，对外服不做用户验证工作
            try {
                // 请求游戏网关，在由网关转到具体的业务逻辑服
                ExternalConnectionAttachment attachment = new ExternalConnectionAttachment();
                attachment.setToken(token);
                userSession.option(UserSessionOption.attachment, DataCodecKit.encode(attachment));
                // 创建一个令牌验证请求
                CmdInfo cmdInfo = CmdInfo.of(ModuleCmdConstants.Account.TOKEN, AccountTokenSubCmdConstants.VALIDATE_TOKEN);
                RequestMessage requestMessage = userSession.ofRequestMessage(cmdInfo);
                brokerClient.oneway(requestMessage);
                return true;
            } catch (Exception e) {
                log.error("网关执行令牌校验操作出错:[{}]", e.getMessage());
                return false;
            }
        }
    }

    @Override
    public void setBrokerClient(BrokerClient brokerClient) {
        this.brokerClient = brokerClient;
    }
}
