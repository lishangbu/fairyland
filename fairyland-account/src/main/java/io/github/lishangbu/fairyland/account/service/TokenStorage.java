package io.github.lishangbu.fairyland.account.service;

import io.github.lishangbu.fairyland.account.entity.Token;

/**
 * TokenStorage 接口定义了用于存储和管理 Token 信息的基本操作。
 * 此接口用于提供 Token 的存取功能，常用于身份验证和会话管理。 *
 *
 * @author lishangbu
 * @since 2024/11/4
 */

public interface TokenStorage {

    /**
     * 根据指定的键获取存储的 Token 信息。
     *
     * @param tokenValue 用于标识 Token 的唯一键,就是token值
     * @return 返回与指定键关联的 Token 对象，如果未找到则返回 null。
     */
    Token getByTokenValue(String tokenValue);

    /**
     * 根据userId获取存储的 Token 信息。
     *
     * @param userId 要查询的userId
     * @return 返回与指定键关联的 Token 对象，如果未找到则返回 null。
     */
    Token getByUserId(Long userId);

    /**
     * 将指定的 Token 信息存储到存储中。
     *
     * @param token 要存储的 Token 信息，包括 Token 的值和其他相关数据。
     */
    void save(Token token);

    /**
     * 从存储中移除指定的 Token 信息
     *
     * @param userId 要移除的 userId
     */
    void removeByUserId(Long  userId);

}
