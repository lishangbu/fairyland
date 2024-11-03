package io.github.lishangbu.fairyland.account.util;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JwtUtilsTest {
    /**
     * token 过期时间, 单位: 秒. 这个值表示 30 天
     */
    private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60 * 1000L;

    private static final String DEFAULT_SUBJECT = "1";

    @Test
    void createToken() {
        String jwt = JwtUtils.createToken("1");
        Assertions.assertNotNull(jwt);
    }

    @Test
    void createTokenWithExpiredTime() {
        String jwt = JwtUtils.createToken(DEFAULT_SUBJECT, TOKEN_EXPIRED_TIME);
        Assertions.assertNotNull(jwt);
    }

    @Test
    void validateToken() {
        String jwt = JwtUtils.createToken(DEFAULT_SUBJECT);
        Assertions.assertTrue(JwtUtils.validateToken(jwt));

    }

    @Test
    void parseToken() {
        String jwt = JwtUtils.createToken(DEFAULT_SUBJECT, TOKEN_EXPIRED_TIME);
        Claims claims = JwtUtils.parseToken(jwt);
        Assertions.assertNotNull(claims);
        Assertions.assertTrue(claims.getExpiration().getTime() - claims.getIssuedAt().getTime() == TOKEN_EXPIRED_TIME);
        Assertions.assertEquals(DEFAULT_SUBJECT, claims.getSubject());
    }
}