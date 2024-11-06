package io.github.lishangbu.fairyland.account.model.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 登陆参数
 *
 * @author lishangbu
 * @since 2024/10/30
 */
@Data
@ProtobufClass(description = "注册参数")
public class RegisterRequestParam {

    /**
     * 注册用的用户名
     */
    @Protobuf(description = "用户名")
    @NotEmpty(message = "用户名不可为空")
    private String username;
    /**
     * 注册用的密码
     */
    @NotEmpty(message = "密码不可为空")
    @Protobuf(description = "密码")
    private String password;
}
