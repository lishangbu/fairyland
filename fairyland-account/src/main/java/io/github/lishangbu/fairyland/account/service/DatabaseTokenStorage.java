package io.github.lishangbu.fairyland.account.service;

import com.mybatisflex.core.query.QueryCondition;
import io.github.lishangbu.fairyland.account.entity.Token;
import io.github.lishangbu.fairyland.account.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.lishangbu.fairyland.account.entity.table.TokenTableDef.TOKEN;

/**
 * 数据库存储
 *
 * @author lishangbu
 * @since 2024/11/4
 */

@Service
@RequiredArgsConstructor
public class DatabaseTokenStorage implements TokenStorage {
    private final TokenMapper tokenMapper;

    @Override
    public Token getByTokenValue(String tokenValue) {
        return tokenMapper.selectOneByCondition(QueryCondition.create(TOKEN.TOKEN_VALUE, tokenValue));
    }

    @Override
    public Token getByUserId(Long userId) {
        return tokenMapper.selectOneByCondition(QueryCondition.create(TOKEN.USER_ID, userId));
    }

    @Override
    public void save(Token token) {
        tokenMapper.insertOrUpdate(token);
    }

    @Override
    public void removeByUserId(Long userId) {
        tokenMapper.deleteByCondition(QueryCondition.create(TOKEN.USER_ID, userId));
    }


}
