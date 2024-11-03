package io.github.lishangbu.fairyland.server.autoconfiguration;

import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配Action配置
 *
 * @author lishangbu
 * @since 2024/11/1
 */
@Configuration(proxyBeanMethods = false)
public class ActionFactoryBeanForSpringAutoConfiguration {
    /**
     * 将业务框架交给 spring 管理
     *
     * @return Spring集成业务框架容器
     */
    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        return ActionFactoryBeanForSpring.me();
    }
}
