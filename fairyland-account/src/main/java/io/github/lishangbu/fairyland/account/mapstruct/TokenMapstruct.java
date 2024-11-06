package io.github.lishangbu.fairyland.account.mapstruct;

import io.github.lishangbu.fairyland.account.entity.Token;
import io.github.lishangbu.fairyland.account.model.TokenAttachment;
import io.github.lishangbu.fairyland.account.model.vo.TokenInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * 账户映射接口，负责将账户实体与数据传输对象（DTO）之间进行转换。
 * <p>
 * 该接口使用 MapStruct 框架进行自动映射，适配 Spring 组件模型。
 *
 * @author lishangbu
 * @since 2024/11/3
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TokenMapstruct {

    /**
     * 将令牌实体转换为令牌信息对象
     *
     * @param token 需要的令牌实体
     * @return 转换后的令牌信息对象
     */
    @Mapping(source = "token.tokenValue", target = "token")
    TokenInfo toTokenInfo(Token token);

    /**
     * 将令牌实体转换为令牌信息对象
     *
     * @param tokenAttachment 需要的令牌实体
     * @return 转换后的令牌信息对象
     */
    TokenInfo toTokenInfo(TokenAttachment tokenAttachment);

    /**
     * 将令牌实体转换为令牌附加数据
     *
     * @param token 需要的令牌实体
     * @return 转换后的令牌附加数据
     */
    @Mapping(source = "token.tokenValue", target = "token")
    TokenAttachment toTokenAttachment(Token token);
}