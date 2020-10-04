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



### 如何使用

-------------------------------------------------------------------------------

## 安装

# Maven安装
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.github.lemone</groupId>
    <artifactId>module-boots-db</artifactId>
    <version>${lemone.version}</version>
</dependency>
```

# Gradle安装
```
compile 'com.github.lemone:module-xxx:${lemone.version}'
```

# 非Maven项目安装

点击以下任一链接，下载`module-xxx-${lemone.version}.jar`即可：

- [Maven中央库1](https://repo1.maven.org/maven2/com/github/lemone/module-xxx/${lemone.version}/)
- [Maven中央库2](http://repo2.maven.org/maven2/com/github/lemone/module-xxx/${lemone.version}/)

> 注意
> Lemone 最低JDK版本为JDK8及以上，目前针对SpringBoot项目做主要适配对象


-------------------------------------------------------------------------------



-------------------------------------------------------------------------------



