/**
 * All rights Reserved, Designed By OprCalf
 * Copyright:    Copyright(C) 2016-2020
 * Company       BSL Ltd.
 */

package com.modules.boots.mp.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.modules.boots.mp.data.OracleData;
import com.modules.boots.mp.filling.OracleMetaHandler;
import com.modules.boots.mp.id.OracleIdHandler;
import com.modules.boots.mp.tenant.OracleTenantLineHandler;

/**
 * ORACLE工厂配置
 * @author：林溪
 * @date：2020年10月3日
 */
@ConditionalOnProperty(prefix = "modules", name = "oracle.enabled", havingValue = "true")
@MapperScan(basePackages = "${modules.oracle.package-path}", sqlSessionTemplateRef = "oracleTemplate")
@EnableTransactionManagement
@Configuration
public class OracleSessionFactory {

    @Autowired
    private OracleData oracleData;

    @Autowired(required = false)
    private OracleIdHandler oracleIdHandler;

    @Autowired(required = false)
    private OracleMetaHandler oracleFillingHandler;

    @Autowired(required = false)
    private OracleTenantLineHandler oracleTenantHandler;

    /**
     * 添加分页插件支持
     * @author 林溪
     * @return PaginationInterceptor
     */
    @Bean(name = "oraclePaginationInterceptor")
    public MybatisPlusInterceptor paginationInterceptor() {
        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 新增多租户插件
        if (oracleTenantHandler != null) {
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(oracleTenantHandler));
        }
        // 新增ORACLE分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
        return interceptor;
    }

    /**
     * mybatisplus全局配置
     * @author 林溪
     * @param dbConfig
     * @return GlobalConfig
     */
    @Bean(name = "oracleGlobalConfig")
    public GlobalConfig globalConfig() {
        final GlobalConfig globalConfig = new GlobalConfig();
        // mybatisplus数据层配置
        final DbConfig dbConfig = new DbConfig();
        // 设置ID的生成规则
        dbConfig.setIdType(IdType.ASSIGN_ID);
        // 设置表名是否使用下划线命名
        dbConfig.setTableUnderline(true);
        // 字段插入非空判断
        dbConfig.setInsertStrategy(FieldStrategy.NOT_EMPTY);
        // 字段更新非空判断
        dbConfig.setUpdateStrategy(FieldStrategy.NOT_EMPTY);
        // 字段查询非空判断
        dbConfig.setSelectStrategy(FieldStrategy.NOT_EMPTY);
        // 设置mybatisplus数据层配置
        globalConfig.setDbConfig(dbConfig);
        // 初始化SqlRunner
        globalConfig.setEnableSqlRunner(true);
        // 设置自定义主键ID的生成方式
        if (oracleIdHandler != null) {
            globalConfig.setIdentifierGenerator(oracleIdHandler);
        }
        // 加载字段自动填充器
        if (oracleFillingHandler != null) {
            globalConfig.setMetaObjectHandler(oracleFillingHandler);
        }
        return globalConfig;
    }

    /**
     * 配置mybatis
     * @author 林溪
     * @return MybatisConfiguration
     */
    @Bean(name = "oracleMybatisConfiguration")
    public MybatisConfiguration mybatisConfiguration() {
        final MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 设置为XML语言驱动
        mybatisConfiguration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        // 设置数据库字段值映射方式,例如：updatedId在进行DB操作的时候会被自动解析为updated_id
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        // 开启Mybatis的二级缓存
        mybatisConfiguration.setCacheEnabled(true);
        // 当查询的返回一行都是null的结果时，MyBatis会帮忙填充一个所有属性都是null的对象
        mybatisConfiguration.setCallSettersOnNulls(true);
        return mybatisConfiguration;
    }

    /**
     * 配置mybatis连接的session工厂
     * @author 林溪
     * @param globalConfig
     * @param mybatisConfiguration
     * @param paginationInterceptor
     * @return
     * @throws Exception MybatisSqlSessionFactoryBean
     */
    @Bean(name = "oracleSession")
    public MybatisSqlSessionFactoryBean sqlSessionFactory(@Qualifier("oracleGlobalConfig") GlobalConfig globalConfig,
            @Qualifier("oracleMybatisConfiguration") MybatisConfiguration mybatisConfiguration,
            @Qualifier("oraclePaginationInterceptor") MybatisPlusInterceptor paginationInterceptor,
            @Qualifier("oracleDataSource") DataSource oracleDataSource) throws Exception {
        final MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(oracleDataSource);
        // 设置XML的映射路径
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(oracleData.getMapperPath()));
        // 设置实体类扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(oracleData.getModelPath());
        // 设置mybatisplus全局配置
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        // 设置mybatis的配置
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        // 加载插件
        sqlSessionFactoryBean.setPlugins(paginationInterceptor);
        return sqlSessionFactoryBean;
    }

    /**
     * 配置声明式事务管理器
     * @author 林溪
     * @param oracleDataSource
     * @return PlatformTransactionManager
     */
    @Bean(name = "oracleManager")
    public PlatformTransactionManager oracleManager(@Qualifier("oracleDataSource") DataSource oracleDataSource) {
        return new DataSourceTransactionManager(oracleDataSource);
    }

    @Bean(name = "oracleTemplate")
    public SqlSessionTemplate oracleTemplate(@Qualifier("oracleSession") SqlSessionFactory oracleSession)
            throws Exception {
        return new SqlSessionTemplate(oracleSession);
    }

}
