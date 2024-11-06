package io.github.lishangbu.fairyland.iogame.model;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.action.skeleton.core.flow.UserAttachment;
import lombok.Data;

/**
 * 外部连接信息元数据
 *
 * @author lishangbu
 * @since 2024/11/5
 */
@Data
@ProtobufClass
public class ExternalConnectionAttachment implements UserAttachment {
    @Protobuf(description = "令牌值")
    private String token;
}
