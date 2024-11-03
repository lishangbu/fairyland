package io.github.lishangbu.fairyland.account;

import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.FlexSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 测试需要提供的一些辅助工具
 *
 * @author lishangbu
 * @since 2024/11/3
 */
@Configuration
@ComponentScan("io.github.lishangbu.fairyland.account")
@MapperScan("io.github.lishangbu.fairyland.account.mapper")
public class SpringTestAppConfig implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 手动创建数据源
     * @return H2数据源
     */
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema-h2.sql")
                .addScript("data-h2.sql").setScriptEncoding("UTF-8")
                .build();
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new FlexSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        FlexConfiguration configuration = new FlexConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    @EventListener(classes = {ContextStartedEvent.class})
    public void handleContextStartedEvent() {
        System.out.println("handleContextStartedEvent listener invoked!");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("onApplicationEvent");
    }

}