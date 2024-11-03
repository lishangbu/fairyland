package io.github.lishangbu.fairyland.account;

import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.internal.DebugInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.common.kit.NetworkKit;
import io.github.lishangbu.fairyland.account.action.LoginAction;
import io.github.lishangbu.fairyland.iogame.ShowErrorMessageActionAfter;

/**
 * account
 *
 * @author lishangbu
 * @date 2023/8/11
 */
public class AccountBrokerClientStartup extends AbstractBrokerClientStartup {
    @Override
    public BrokerAddress createBrokerAddress() {
        // 类似 127.0.0.1 ，但这里是本机的 ip
        String localIp = NetworkKit.LOCAL_IP;
        // broker （游戏网关）默认端口
        int brokerPort = IoGameGlobalConfig.brokerPort;
        return new BrokerAddress(localIp, brokerPort);
    }

    @Override
    public BarSkeleton createBarSkeleton() {
        // 业务框架构建器 配置
        BarSkeletonBuilderParamConfig config = new BarSkeletonBuilderParamConfig()
                .scanActionPackage(LoginAction.class);

        // 业务框架构建器
        BarSkeletonBuilder builder = config.createBuilder();
        // 添加控制台输出插件
        builder.addInOut(new DebugInOut());
        builder.setActionAfter(new ShowErrorMessageActionAfter());
        // 开启 jsr 380 验证
        builder.getSetting().setValidator(true);
        return builder.build();

    }

    @Override
    public BrokerClientBuilder createBrokerClientBuilder() {
        BrokerClientBuilder builder = BrokerClient.newBuilder();
        builder.appName("账户服务");
        return builder;
    }
}