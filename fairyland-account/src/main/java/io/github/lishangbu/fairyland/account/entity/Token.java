package io.github.lishangbu.fairyland.account.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author lishangbu
 * @since 2024/10/30
 */
@Table(value = "TOKEN")
@Data
public class Token implements Serializable {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 令牌
     */
    private String tokenValue;
}
