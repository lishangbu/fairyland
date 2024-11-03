package io.github.lishangbu.fairyland.account.mapstruct;

import io.github.lishangbu.fairyland.account.entity.Account;
import io.github.lishangbu.fairyland.account.model.TokenInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 令牌映射
 *
 * @author lishangbu
 * @since 2024/10/31
 */
@Mapper
public interface TokenMapper {

    @Mapping(source = "account.username", target = "username")
    TokenInfo toUserInfo(Account account, String token);

}
