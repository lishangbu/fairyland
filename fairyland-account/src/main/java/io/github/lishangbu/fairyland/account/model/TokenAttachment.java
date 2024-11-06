package io.github.lishangbu.fairyland.account.model;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.action.skeleton.core.flow.UserAttachment;
import lombok.Data;

/**
 * 令牌元数据
 *
 * @author lishangbu
 * @since 2024/11/4
 */
@Data
@ProtobufClass
public class TokenAttachment implements UserAttachment {
    @Protobuf(description = "令牌")
    private String token;
    /**
     * 用户名
     */
    @Protobuf(description = "用户名")
    private String username;
}
