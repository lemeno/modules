<p align="center">
<a href="https://gitee.com/lemeno/modules"><img src="https://images.gitee.com/uploads/images/2020/1006/113826_ce36e1ec_8149964.png" width="60%"></a>
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
Modules是lemeno系列的一个组件库，面对各种组件每次都需要进行手动编写很多配置代码的问题，通过二次封装，提供 **开箱即用** 的组件，让你轻轻松松使用各种组件,不需要手动去编写任何代码。

modules中的组件或者方法来自于现网开源的代码，精于选，优于选，它既是大型项目开发中解决小问题的利器，也是小型项目中的效率担当；它节省了开发人员对项目中公用类和公用工具方法的封装时间，使开发专注于业务，同时可以最大限度的避免封装不完善带来的bug。

### modules-cache-redis介绍

#### 前置条件

1. 基于SpringBoot开发项目



#### 解决问题：

1. 统一Redis工厂配置，不用手动去配置

2. 提供标准化的RestTemplate<String,Object>对象，注入即可使用

3. 提供mybatis缓存对象MybatisRedisCache，配置即可实现数据库查询自动缓存


-------------------------------------------------------------------------------

### 如何使用

## 1. 安装

### Maven安装
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.github.lemeno</groupId>
    <artifactId>module-cache-redis</artifactId>
    <version>${module-cache-redis.version}</version>
</dependency>
```

### Gradle安装
```
compile 'com.github.lemeno:module-cache-redis:${module-cache-redis.version}'
```

### 非Maven项目安装

点击以下任一链接，下载`module-cache-redis-${module-cache-redis.version}.jar`即可：

- [Maven中央库1](https://repo1.maven.org/maven2/com/github/lemeno/module-cache-redis/${module-cache-redis.version}/)
- [Maven中央库2](http://repo2.maven.org/maven2/com/github/lemeno/module-cache-redis/${module-cache-redis.version}/)

> 注意
> lemeno 最低JDK版本为JDK8及以上，目前针对SpringBoot项目做主要适配对象

-------------------------------------------------------------------------------

### 2. 配置数据源


```
##################redis配置参考###############################
##redis地址
modules.cache.redis.host: 10.4.10.171
##redis端口号
modules.cache.redis.port: 6379
##redis密码,不用可不填
modules.cache.redis.password: Bsl@123456
##mybatis缓存到redis中的时间（时间：秒）,不填写默认300秒
modules.cache.redis.cache-time-out: 300
```

### 3. 启动类配置

在启动类的头上增加以下注解，扫描需要的包

```
@ComponentScan(basePackages = { "com.modules"})
```



### 4. 按需使用

#### 4.1. 使用redis缓存

```
// 直接注入对象即可使用
@Autowired
private RedisTemplate<String, Object> redisTemplate;
```

#### 4.2. 使用mybatis查询自动缓存

```
1. 在Mapper类上加入以下信息
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)

参考：
@Mapper
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface IDataDictDataDao extends BaseMapper<DataDictData> 

2. 在XML文件上加入缓存Mapper，避免缓存无效
<cache-ref namespace="xx.xxx.xxx.xxx.xx.IDataDictDataDao"/>

```

## 问题及反馈

在使用过程中有什么问题可通过modules上的主页，加入QQ群联系我