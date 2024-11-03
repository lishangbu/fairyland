package io.github.lishangbu.fairyland.account.util;

import com.iohao.game.common.kit.time.CacheTimeKit;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.experimental.UtilityClass;

import java.security.Key;
import java.util.Date;

/**
 * JWT创建器
 *
 * @author lishangbu
 * @since 2024/11/2
 */
@UtilityClass
public class JwtUtils {

    /**
     * 安全的密钥，用于加密和解密 JWT
     */
    private static final String SECRET_KEY = "9475449b92dbfde97d9db54f7d767c7837fb0a25eaec54e76f798b3903fd6b21";

    /**
     * 使用 HMAC SHA-256 算法生成密钥
     */
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * 创建 JWT 令牌
     *
     * @param subject 令牌的主题（通常是用户身份信息）
     * @return 生成的 JWT 字符串
     */
    public  String createToken(String subject) {
        return createToken(subject, 0L);
    }

    /**
     * 创建 JWT 令牌
     *
     * @param subject    令牌的主题（通常是用户身份信息）
     * @param millisTime 过期时间
     * @return 生成的 JWT 字符串
     */
    public  String createToken(String subject, long millisTime) {
        long nowMillis = CacheTimeKit.currentTimeMillis();//生成JWT的时间
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject) // 设置令牌的主题
                .setIssuedAt(new Date(nowMillis)) // 设置令牌的签发时间
                .signWith(KEY, SignatureAlgorithm.HS256);// 使用 HMAC-SHA256 算法进行签名

        if (millisTime > 0) {
            long expMillis = nowMillis + millisTime;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        // 生成 JWT 字符串
        return builder.compact();
    }

    /**
     * 验证 JWT 是否有效
     *
     * @param token 要验证的 JWT 字符串
     * @return 如果有效返回 true，否则返回 false
     */
    public  boolean validateToken(String token) {
        try {
            Claims claims = parseToken(token);
            // 检查过期时间
            if (claims.getExpiration() != null) {
                return !claims.getExpiration().before(new Date());
            }
            return true;
        } catch (SignatureException e) {
            // 签名验证失败
            return false;
        } catch (Exception e) {
            // 其他异常（如解析错误等）
            return false;
        }
    }

    /**
     * 解析 JWT，返回声明信息
     *
     * @param token 要解析的 JWT 字符串
     * @return Claims 对象，包含 JWT 中的声明信息
     */
    public  Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
