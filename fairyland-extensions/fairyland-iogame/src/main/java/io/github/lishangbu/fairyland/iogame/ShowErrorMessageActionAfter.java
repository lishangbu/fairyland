package io.github.lishangbu.fairyland.iogame;

import com.iohao.game.action.skeleton.core.flow.ActionAfter;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.action.skeleton.core.flow.attr.FlowAttr;
import com.iohao.game.action.skeleton.core.flow.internal.DefaultActionAfter;
import com.iohao.game.action.skeleton.protocol.ResponseMessage;

/**
 * 在对外服传输协议中带上错误信息
 *
 * @author lishangbu
 * @since 2024/10/30
 */
public class ShowErrorMessageActionAfter implements ActionAfter {
    ActionAfter actionAfter = new DefaultActionAfter();

    @Override
    public void execute(final FlowContext flowContext) {
        final ResponseMessage response = flowContext.getResponse();
        if (response.hasError()) {
            // 得到异常消息
            String msg = flowContext.option(FlowAttr.msgException);
            // 将异常信息携带到请求端
            response.setValidatorMsg(msg);
        }

        actionAfter.execute(flowContext);
    }
}