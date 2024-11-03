package io.github.lishangbu.fairyland.server.handler;

import com.iohao.game.external.core.netty.handler.ws.WebSocketVerifyHandler;
import com.iohao.game.external.core.netty.session.SocketUserSession;
import io.github.lishangbu.fairyland.account.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * xxx
 *
 * @author lishangbu
 * @since 2024/11/1
 */
@Slf4j
public class AuthVerifyHandler extends WebSocketVerifyHandler {


    @Override
    public boolean verify(SocketUserSession userSession
            , Map<String, String> params) {
        /*
         * 测试方法，ws://127.0.0.1:10100/websocket?token=abc&name=aaaa
         * 之后可以在 params 中得到参数 key:value 格式
         */
        String token = params.get("token");
        // 返回 true 表示验证通过，返回 false 框架会关闭连接,
        // 未登录时token可能不存在，此时放行验证
        return token == null || token.isEmpty() || JwtUtils.validateToken(token);
    }

}