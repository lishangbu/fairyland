package io.github.lishangbu.fairyland.server.hook;

import com.iohao.game.action.skeleton.core.exception.ActionErrorEnum;
import com.iohao.game.action.skeleton.protocol.BarMessage;
import com.iohao.game.action.skeleton.protocol.wrapper.LongValue;
import com.iohao.game.common.kit.time.CacheTimeKit;
import com.iohao.game.external.core.message.ExternalCodecKit;
import com.iohao.game.external.core.netty.hook.SocketIdleHook;
import com.iohao.game.external.core.session.UserSession;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳设置
 *
 * @author lishangbu
 * @since 2024/11/1
 */
@Slf4j
public class DefaultSocketIdleHook implements SocketIdleHook {
    @Override
    public boolean callback(UserSession userSession, IdleStateEvent event) {
        IdleState state = event.state();
        if (state == IdleState.READER_IDLE) {
            /* 读超时 */
            log.debug("READER_IDLE 读超时");
        } else if (state == IdleState.WRITER_IDLE) {
            /* 写超时 */
            log.debug("WRITER_IDLE 写超时");
        } else if (state == IdleState.ALL_IDLE) {
            /* 总超时 */
            log.debug("ALL_IDLE 总超时");
        }

        BarMessage message = ExternalCodecKit.createErrorIdleMessage(ActionErrorEnum.idleErrorCode);
        // 错误消息
        message.setValidatorMsg(ActionErrorEnum.idleErrorCode.getMsg() + " : " + state.name());

        // 通知客户端，触发了心跳事件
        userSession.writeAndFlush(message);

        // 返回 true 表示通知框架将当前的用户（玩家）连接关闭
        return true;
    }

    /**
     * 心跳响应前的回调
     * <pre>
     *     开发者可以给心跳消息添加一些额外信息，比如当前时间之类的。
     * </pre>
     * example
     * <pre>{@code
     *     @Override
     *     public void pongBefore(BarMessage idleMessage) {
     *         // 把当前时间戳给到心跳接收端
     *         LongValue data = LongValue.of(TimeKit.currentTimeMillis());
     *         idleMessage.setData(data);
     *     }
     * }
     * </pre>
     *
     * @param idleMessage 心跳消息
     */
    @Override
    public void pongBefore(BarMessage idleMessage) {     // 把当前时间戳给到心跳接收端
        LongValue data = LongValue.of(CacheTimeKit.currentTimeMillis());
        idleMessage.setData(data);
    }
}
