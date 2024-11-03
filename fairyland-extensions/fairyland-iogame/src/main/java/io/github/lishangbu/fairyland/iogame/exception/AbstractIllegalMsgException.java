package io.github.lishangbu.fairyland.iogame.exception;

import com.iohao.game.action.skeleton.core.exception.ActionErrorEnum;
import com.iohao.game.action.skeleton.core.exception.MsgException;

/**
 * 非法信息异常
 *
 * @author lishangbu
 * @since 2024/10/31
 */
public abstract class AbstractIllegalMsgException extends MsgException {


    public AbstractIllegalMsgException() {
        super(ActionErrorEnum.validateErrCode.getCode(), ActionErrorEnum.validateErrCode.getMsg());
    }

    public AbstractIllegalMsgException(String message) {
        super(ActionErrorEnum.validateErrCode.getCode(), message);
    }

}
