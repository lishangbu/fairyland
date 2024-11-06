package io.github.lishangbu.fairyland.account;

import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.internal.DebugInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import io.github.lishangbu.fairyland.account.action.AccountAction;
import io.github.lishangbu.fairyland.iogame.ShowErrorMessageActionAfter;

/**
 * 账户构建器
 *
 * @author lishangbu
 * @since 2023/8/11
 */
public class AccountBrokerClientStartup extends AbstractBrokerClientStartup {

    @Override
    public BarSkeleton createBarSkeleton() {
        // 业务框架构建器 配置
        BarSkeletonBuilderParamConfig config = new BarSkeletonBuilderParamConfig()
                .scanActionPackage(AccountAction.class);

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