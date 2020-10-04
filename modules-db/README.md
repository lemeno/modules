<p align="center">
<a href="https://gitee.com/smartDis/modules"><img src="https://images.gitee.com/uploads/images/2020/1003/205051_993b2055_1087477.png" width="45%"></a>
</p>
<p align="center">
<strong>一个开箱即用的组件库.</strong>
</p>

<p align="center">
	<a target="_blank" href="https://license.coscl.org.cn/MulanPSL2/">
		<img src="https://img.shields.io/:license-MulanPSL2-blue.svg" />
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
</p>

<br/>
<p align="center">
	<a href="https://qm.qq.com/cgi-bin/qm/qr?k=0wwldaU0E8r-ZzHl_wma33W7420zwXYi&jump_from=webapi"><img src="https://img.shields.io/badge/QQ%E7%BE%A4-868327279-orange"/></a>
</p>


-------------------------------------------------------------------------------

## 整体项目简介
Modules是Lemone系列的一个组件库，面对各种组件每次都需要进行手动编写很多配置代码的问题，通过二次封装，提供 **开箱即用** 的组件，让你轻轻松松使用各种组件,不需要手动去编写任何代码。

modules中的组件或者方法来自于现网开源的代码，精于选，优于选，它既是大型项目开发中解决小问题的利器，也是小型项目中的效率担当；它节省了开发人员对项目中公用类和公用工具方法的封装时间，使开发专注于业务，同时可以最大限度的避免封装不完善带来的bug。

### modules-boots-db介绍

基于Mybatis-Plus的二次封装,解决以下问题：

1. 解决使用MP都需要进行数据源配置，避免网上各类乱七八糟的配置，配置文件上约定好进行简单配置即可

2. 多数据源自动化，按照需要选择你需要的数据源，目前支持MYSQ，ORACLE，后续继续拓展其他数据源

3. 集成p6spy做SQL查询

4. 拓展多租户接口

| 接口                        |     介绍                                                                         |
| ----------------------------|---------------------------------------------------------------------------------|
| OracleTenantLineHandler     |   ORACLE多租户接口                                                               |
| MysqlTenantLineHandler      |   MYSQL多租户接口                                                                |
| XXXTenantLineHandler        |   其他数据源多租户接口，待拓展                                                     |

5. 拓展自动填充接口

| 接口                        |     介绍                                                                         |
| ----------------------------|---------------------------------------------------------------------------------|
| OracleMetaHandler           |   ORACLE自动填充接口                                                             |
| MysqlMetaHandler            |   MYSQL自动填充接口                                                              |
| XXXMetaHandler              |   其他数据源自动填充接口                                                          |

6. 拓展ID生成接口

| 接口                        |     介绍                                                                         |
| ----------------------------|---------------------------------------------------------------------------------|
| OracleIdHandler             |   ORACLE自动ID生成接口                                                           |
| MysqlIdHandler              |   MYSQL自动ID生成接口                                                            |
| XXXIdHandler                |   其他数据源自动ID生成接口                                                        |


-------------------------------------------------------------------------------

### 如何使用

## 1. 安装

### Maven安装
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.github.lemone</groupId>
    <artifactId>module-boots-db</artifactId>
    <version>${lemone.version}</version>
</dependency>
```

### Gradle安装
```
compile 'com.github.lemone:module-xxx:${lemone.version}'
```

### 非Maven项目安装

点击以下任一链接，下载`module-xxx-${lemone.version}.jar`即可：

- [Maven中央库1](https://repo1.maven.org/maven2/com/github/lemone/module-xxx/${lemone.version}/)
- [Maven中央库2](http://repo2.maven.org/maven2/com/github/lemone/module-xxx/${lemone.version}/)

> 注意
> Lemone 最低JDK版本为JDK8及以上，目前针对SpringBoot项目做主要适配对象

-------------------------------------------------------------------------------

### 2. 配置数据源


```
##################MYSQL配置参考###############################
###定义mysql启动###
modules.mysql.enabled: true
###定义mysql连接地址###
modules.mysql.url: jdbc:p6spy:mysql://127.0.0.1:3306/lemone?autoReconnect=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true
###定义 mysql账户名###
modules.mysql.username: root 
###定义mysql密码###
modules.mysql.password: 执行JasyptUtils.encryptMsg("mysql密码")
###定义给mysql密码进行加密的盐值###
modules.mysql.jasypt.password: xv021yb30741b2yc8y21b30y4x4n2c4x
###定义dao的路径###
modules.mysql.package-path: com.lemone.**.business.mysql.**.dao
###定义实体类查询对象的路径###
modules.mysql.model-path: com.lemone.**.business.model
###定义xml文件的路径###
modules.mysql.mapper-path: classpath:/mappers/*Mapper.xml

##################ORACLE配置参考###############################
###定义oracle启动###
modules.oracle.enabled: true
###定义oracle连接地址###
modules.oracle.url: jdbc:p6spy:oracle:thin:@10.102.30.253:11521/MDM
###定义 oracle账户名###
modules.oracle.username: mdms 
###定义oracle密码###
modules.oracle.password: 执行JasyptUtils.encryptMsg("oracle密码")
###定义给oracle密码进行加密的盐值###
modules.oracle.jasypt.password: by9xb8yrybr98b2cy498y8xrynq9
###定义dao的路径###
modules.oracle.package-path: com.lemone.**.business.oracle.**.dao
###定义实体类查询对象的路径###
modules.oracle.model-path: com.lemone.**.business.model
###定义xml文件的路径###
modules.oracle.mapper-path: classpath:/mappers/*Mapper.xml
```

>  **注意** 
>  **modules.oracle.enabled与modules.mysql.enabled必须有一个为true（或者都为true），不然SpringBoot会报错，因为采用官方的自动配置** 

### 3. 启动类配置

在启动类的头上增加以下注解，扫描需要的包

```
@ComponentScan(basePackages = { "com.modules"})
```



### 4. 按需拓展（以MYSQL为例，其余数据源类似）

#### 4.1. 拓展多租户

```
/**
 * MYSQL多租户注入
 * @author：林溪
 * @date：2020年10月3日
 */
@Component
public class TenantHandler implements MysqlTenantLineHandler {

    /**
     * 获取租户的值
     *
     * @author 林溪
     * @return 返回租户的值
     */
    @Override
    public Expression getTenantId() {
        return new LongValue(0000000000L);
    }

    /**
     * 判断表是否需要做多租户判断
     * @author 林溪
     * @param tableName
     * @return 某些表不需要多租户判断,做判断返回false
     */
    @Override
    public boolean ignoreTable(String tableName) {
        return false;
    }

    /**
     * 返回租户字段名称,如果不写默认就是tenant_id
     * @author 林溪
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }
}
```

#### 4.2. 拓展自动填充

```
/**
 * MYSQL字段填充
 * @author：林溪
 * @date：2020年10月3日
 */
@Slf4j
//@Component
public class FillingHandler implements MysqlMetaHandler {

    /**
     * 新增数据的时候,自动进行数据填充
     * @author 林溪
     * @param metaObject
     * @return
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始进行新增字段数据填充");

    }

    /**
     * 更新数据的时候,自动进行数据填充
     * @author 林溪
     * @param metaObject
     * @return
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始进行更新字段数据填充");
    }
}
```

#### 4.3. 拓展ID生成器

```
/**
 * 自定义ID生成器
 * @author：林溪
 * @date：2020年10月3日
 */
@Component
public class IdHandler implements MysqlIdHandler {

    /**
     * 获取自定义id
     * @author 溪云阁
     * @param entity
     * @return 返回数据库的主键ID
     */
    @Override
    public Number nextId(Object entity) {
        // 采用雪花算法获取id,时间回拨会存在重复,这里用随机数来减少重复的概率
        final Snowflake snowflake = IdUtil.createSnowflake(1, (int) (Math.random() * 20 + 1));
        return snowflake.nextId();
    }
}
```

## 问题及反馈

在使用过程中有什么问题可通过modules上的主页，加入QQ群联系我