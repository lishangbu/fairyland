package io.github.lishangbu.fairyland.account.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录账户
 *
 * @author lishangbu
 * @since 2019-02-17 23:53:08
 */
@NoArgsConstructor
@Data
@Table(value = "ACCOUNT")
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @Column(comment = "主键")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 账号激活状态,0代表未激活,1代表已经激活
     */
    private Boolean enabled;

    /**
     * 账号未过期状态0代表已经过期，1代表未过期
     */
    private Boolean accountNonExpired;

    /**
     * 密码未过期状态,0代表密码已经过期,1代表密码未过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 账户未锁定状态,0代表密码已经锁定,1代表账号未锁定
     */
    private Boolean accountNonLocked;

}