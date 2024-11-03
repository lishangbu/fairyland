package io.github.lishangbu.fairyland.account.model;

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
@ProtobufClass(description = "登录参数")
public class LoginRequestParam {

    /**
     * 登录用的用户名
     */
    @Protobuf(description = "用户名")
    @NotEmpty(message = "用户名不可为空")
    private String username;
    /**
     * 登录用的密码
     */
    @NotEmpty(message = "密码不可为空")
    @Protobuf(description = "密码")
    private String password;
}
