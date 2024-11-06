package io.github.lishangbu.fairyland.account.model.vo;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author lishangbu
 * @since 2024/10/30
 */
@Data
@ProtobufClass(description = "令牌信息")
public class TokenInfo implements Serializable {

    @Protobuf(description = "令牌")
    private String token;

    /**
     * 用户ID
     */
    @Protobuf(description = "用户ID")
    private Long userId;

    /**
     * 用户名
     */
    @Protobuf(description = "用户名")
    private String username;
}
