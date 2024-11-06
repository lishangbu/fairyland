package io.github.lishangbu.fairyland.account.service;

import com.mybatisflex.core.query.QueryCondition;
import io.github.lishangbu.fairyland.account.entity.Account;
import io.github.lishangbu.fairyland.account.mapper.AccountMapper;
import io.github.lishangbu.fairyland.account.util.BCrypt;
import io.github.lishangbu.fairyland.iogame.util.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.lishangbu.fairyland.account.entity.table.AccountTableDef.ACCOUNT;

/**
 * 账户服务
 *
 * @author lishangbu
 * @since 2024/10/31
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class AccountService {

    private final AccountMapper accountMapper;


    /**
     * 验证账户
     *
     * @param username 登录用的用户名
     * @param password 登录用的密码
     */
    public Account getAndValidateAccount(String username, String password) {
        Account account = getAccountByUsername(username);
        Assert.notNull(account, "用户名或密码错误");
        Assert.isTrue(BCrypt.checkpw(password, account.getPassword()), "用户名或密码错误");
        Assert.isTrue(account.getEnabled(), "账户未激活");
        Assert.isTrue(account.getAccountNonExpired(), "账户已过期");
        Assert.isTrue(account.getAccountNonLocked(), "账户已锁定");
        Assert.isTrue(account.getCredentialsNonExpired(), "密码已过期");
        return account;
    }

    /**
     * 执行登录
     *
     * @param username 登录用的用户名
     * @param password 登录用的密码
     */
    public Account register(String username, String password) {
        Assert.isNull(getAccountByUsername(username), "当前用户已经存在");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        account.setEnabled(true);
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setAccountNonExpired(true);
        account.setCredentialsNonExpired(true);
        account.setEmail("");
        account.setMobile("");
        accountMapper.insert(account);
        return account;
    }

    private Account getAccountByUsername(String username) {
        return accountMapper.selectOneByCondition(QueryCondition.create(ACCOUNT.USERNAME, username));
    }
}
