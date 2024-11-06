package io.github.lishangbu.fairyland.account.model.vo;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import io.github.lishangbu.fairyland.account.entity.Account;
import lombok.Data;

/**
 * 注册或查询时返回的账户信息
 * <p>
 * 对比{@link Account}就去掉了几个密码和表示数据库账户状态的字段
 *
 * @author lishangbu
 * @see io.github.lishangbu.fairyland.account.entity.Account
 * @since 2024/11/3
 */
@Data
@ProtobufClass(description = "账户信息")
public class AccountInfo {
    /**
     * 账户ID
     */
    @Protobuf(description = "账户ID")
    private Long id;

    /**
     * 用户名
     */
    @Protobuf(description = "用户名")
    private String username;

    /**
     * 邮箱
     */
    @Protobuf(description = "邮箱")
    private String email;

    /**
     * 手机
     */
    @Protobuf(description = "手机")
    private String mobile;

}
