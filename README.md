<p align="center">
<a href="https://gitee.com/lemeno/modules"><img src="https://images.gitee.com/uploads/images/2020/1006/113826_ce36e1ec_8149964.png" width="60%"></a>
</p>
<p align="center">
<strong>一个开箱即用的组件库</strong>
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

## 简介
Modules是lemeno系列的一个组件库，面对各种组件每次都需要进行手动编写很多配置代码的问题，通过二次封装，提供 **开箱即用** 的组件，让你轻轻松松使用各种组件，不需要手动去编写任何代码。

modules中的组件或者方法来自于现网开源的代码，精于选，优于选，它既是大型项目开发中解决小问题的利器，也是小型项目中的效率担当；它节省了开发人员对项目中公用类和公用工具方法的封装时间，使开发专注于业务，同时可以最大限度的避免封装不完善带来的bug。

### lemeno名称的由来

lemeno来源“01”的英文谐音，作为咱们开发者最底层的声音。

### Modules如何提高我们的效率

Modules的目标是提供一套 **开箱即用** 的方式，只需在配置文件简单配置，即可来解决很多现有SpringBoot项目代码需要进行手动编写配置类的问题。

-------------------------------------------------------------------------------

## 依赖组件

| 组件                                                                                                |     版本                                                                         |
| ----------------------------------------|------------------------------------|
| JDK                                     |   最低1.8.0_171                     |
| SpringBoot                              |   2.3.4.RELAESE                     |
| jasypt加密解密                                                                       |   3.0.2                            |
| hutool工具类                                                                            |   5.4.4                            |
| mybatis工具                                                                               |   3.5.4                            |
| mybatis-spring-boot-starter整合包                     |   2.1.2                            |
| druid-spring-boot-starter数据源整合包           |   1.1.21                           |
| mybatis-plus-boot-starter整合包                          |   3.4.0                            |
| p6spy自定义SQL工具                                                              |   3.9.1                            |
| oracle包                                                                                        |   10.2.0.4.0                       |
| mysql包                                                                                           |   8.0.21                           |


-------------------------------------------------------------------------------

## 包含组件

| 模块              |     介绍                                                                         |
| ------------------|---------------------------------------------------------------------------------|
| modules-bases     |   模块化组件中最基础的组件，集成最纯粹的现有JAVA组件，避免重复造轮子                          |
| modules-db        |   基于mybatis-plus二次封装组件， 拓展多数据源，多租户，ID自动化生成器，数据自动填充        |
| modules-cache-redis  |   对redis进行二次封装，提供最基础的restTemplate外，提供mybatis缓存插件MybatisRedisCache        |


使用说明：在项目中根据需要，每个模块单独引入，详细使用参考每个项目README.md


-------------------------------------------------------------------------------

## 安装

### Maven
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.github.lemeno</groupId>
    <artifactId>module-xxx</artifactId>
    <version>${lemeno.version}</version>
</dependency>
```

### Gradle
```
compile 'com.github.lemeno:module-xxx:${lemeno.version}'
```

### 非Maven项目

点击以下任一链接，下载`module-xxx-${lemeno.version}.jar`即可：

- [Maven中央库1](https://repo1.maven.org/maven2/com/github/lemeno/module-xxx/${lemeno.version}/)
- [Maven中央库2](http://repo2.maven.org/maven2/com/github/lemeno/module-xxx/${lemeno.version}/)

> 注意
> lemeno 最低JDK版本为JDK8及以上，目前针对SpringBoot项目做主要适配对象


-------------------------------------------------------------------------------

## 添砖加瓦

完善中，暂时不提供添砖加瓦，如有什么问题可随时留言...

-------------------------------------------------------------------------------



