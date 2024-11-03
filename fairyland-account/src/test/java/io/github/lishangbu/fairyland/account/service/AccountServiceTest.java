package io.github.lishangbu.fairyland.account.service;

import io.github.lishangbu.fairyland.account.SpringTestAppConfig;
import io.github.lishangbu.fairyland.account.entity.Account;
import io.github.lishangbu.fairyland.iogame.exception.IllegalArgumentMsgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringTestAppConfig.class)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    /**
     * 数据库中已经有一个用户名为admin,密码为123456的账号
     */
    @Test
    void login() {
        Account account = accountService.login("admin", "123456");
        Assertions.assertEquals("admin", account.getUsername());
        Assertions.assertEquals("", account.getEmail());
        Assertions.assertEquals("", account.getMobile());
        Assertions.assertTrue(account.getEnabled());
        Assertions.assertTrue(account.getAccountNonExpired());
        Assertions.assertTrue(account.getAccountNonLocked());
        Assertions.assertTrue(account.getCredentialsNonExpired());
    }

    @Test
    void register() {
        Account account = accountService.register("admin1", "123456");
        Assertions.assertEquals("admin1", account.getUsername());
        Assertions.assertEquals("", account.getEmail());
        Assertions.assertEquals("", account.getMobile());
        Assertions.assertTrue(account.getEnabled());
        Assertions.assertTrue(account.getAccountNonExpired());
        Assertions.assertTrue(account.getAccountNonLocked());
        Assertions.assertTrue(account.getCredentialsNonExpired());
    }

    @Test
    void repeatRegister() {
        Assertions.assertThrows(IllegalArgumentMsgException.class, () -> accountService.register("admin", "123456"));
    }
}