package io.github.lishangbu.fairyland.account.model;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.action.skeleton.core.flow.UserAttachment;
import lombok.Data;

/**
 * 用户信息
 *
 * @author lishangbu
 * @since 2024/10/30
 */
@Data
@ProtobufClass(description = "令牌信息")
public class TokenInfo implements UserAttachment {

    /**
     * 用户名
     */
    @Protobuf(description = "用户名")
    private String username;


    /**
     * 令牌
     */
    @Protobuf(description = "令牌")
    private String token;

}
