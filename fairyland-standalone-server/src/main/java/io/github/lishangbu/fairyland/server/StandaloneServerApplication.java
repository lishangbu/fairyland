package io.github.lishangbu.fairyland.server;

import com.iohao.game.external.core.config.ExternalGlobalConfig;
import com.iohao.game.external.core.config.ExternalJoinEnum;
import com.iohao.game.external.core.hook.internal.IdleProcessSetting;
import com.iohao.game.external.core.netty.DefaultExternalServer;
import com.iohao.game.external.core.netty.DefaultExternalServerBuilder;
import com.iohao.game.external.core.netty.handler.ws.WebSocketVerifyHandler;
import com.iohao.game.external.core.netty.micro.WebSocketMicroBootstrapFlow;
import com.iohao.game.external.core.netty.simple.NettyRunOne;
import io.github.lishangbu.common.core.constant.LoginModuleFunctionConstants;
import io.github.lishangbu.common.core.constant.ModuleConstants;
import io.github.lishangbu.fairyland.account.AccountBrokerClientStartup;
import io.github.lishangbu.fairyland.server.handler.AuthVerifyHandler;
import io.github.lishangbu.fairyland.server.hook.DefaultSocketIdleHook;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * 单机版服务应用
 *
 * @author lishangbu
 * @since 2024/8/31
 */
@MapperScan("io.github.lishangbu.fairyland.**.mapper")
@SpringBootApplication(scanBasePackages = {"io.github.lishangbu.fairyland"})
public class StandaloneServerApplication {
    public static void main(String[] args) {
        // 启动 spring boot
        SpringApplication.run(StandaloneServerApplication.class, args);

        DefaultExternalServerBuilder builder = DefaultExternalServer.newBuilder(10100);
        // 设置心跳
        builder.setting().setIdleProcessSetting(new IdleProcessSetting()
                // 添加心跳事件回调
                .setIdleHook(new DefaultSocketIdleHook()));
        // 设置 MicroBootstrapFlow 类，并重写 createVerifyHandler 方法
        builder.setting().setMicroBootstrapFlow(new WebSocketMicroBootstrapFlow() {
            @Override
            protected WebSocketVerifyHandler createVerifyHandler() {
                return new AuthVerifyHandler();
            }
        });
        builder.externalJoinEnum(ExternalJoinEnum.WEBSOCKET);
        var accessAuthenticationHook = ExternalGlobalConfig.accessAuthenticationHook;
        // 表示登录才能访问业务方法
        accessAuthenticationHook.setVerifyIdentity(true);
        // 添加不需要登录（身份验证）也能访问的业务方法 (action)
        accessAuthenticationHook.addIgnoreAuthCmd(ModuleConstants.LOGIN, LoginModuleFunctionConstants.LOGIN);
        accessAuthenticationHook.addIgnoreAuthCmd(ModuleConstants.LOGIN, LoginModuleFunctionConstants.REGISTER);

        new NettyRunOne()
                .setExternalServer(builder.build())
                .setLogicServerList(List.of(new AccountBrokerClientStartup()))
                .startup();

    }


}
