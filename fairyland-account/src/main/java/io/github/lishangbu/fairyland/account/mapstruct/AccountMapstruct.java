package io.github.lishangbu.fairyland.account.mapstruct;

import io.github.lishangbu.fairyland.account.entity.Account;
import io.github.lishangbu.fairyland.account.model.vo.AccountInfo;
import org.mapstruct.Mapper;
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
public interface AccountMapstruct {

    /**
     * 将账户实体转换为账户信息对象
     *
     * @param account 需要转换的账户实体
     * @return 转换后的账户信息对象
     */
    AccountInfo toAccountInfo(Account account);
}