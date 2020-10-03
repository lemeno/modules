![输入图片说明](https://images.gitee.com/uploads/images/2020/1003/205051_993b2055_1087477.png "字体.PNG")

-------------------------------------------------------------------------------

## 简介
Modules是Lemone系列的一个组件库，面对各种组件每次都需要进行手动编写很多配置代码的问题，通过二次封装，提供 **开箱即用** 的组件，让你轻轻松松使用各种组件,不需要手动去编写任何代码。

modules中的组件或者方法来自于现网开源的代码，精于选，优于选，它既是大型项目开发中解决小问题的利器，也是小型项目中的效率担当；它节省了开发人员对项目中公用类和公用工具方法的封装时间，使开发专注于业务，同时可以最大限度的避免封装不完善带来的bug。

### Lemone名称的由来

Lemone来源“01”的英文谐音,作为咱们开发者最底层的声音。

### Modules如何提高我们的效率

Modules的目标是提供一套 **开箱即用** 的方式,只需在配置文件简单配置,即可来解决很多现有SpringBoot项目代码需要进行手动编写配置类的问题。

-------------------------------------------------------------------------------

## 包含组件

| 模块              |     介绍                                                                         |
| ------------------|---------------------------------------------------------------------------------|
| [modules-bases](http://https://gitee.com/smartDis/modules/tree/master/modules-bases)     |   模块化组件中最基础的组件,在hutool的基础上进行了二次封装                            |
| [modules-boots-mp](http://https://gitee.com/smartDis/modules/tree/master/modules-boots-mp)  |   mybatis-plus二次封装组件, 拓展多数据源，多租户，ID自动化生成器，数据自动填充        |

在项目中根据需要，每个模块单独引入


-------------------------------------------------------------------------------

## 安装

### Maven
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.lemone</groupId>
    <artifactId>module-xxx</artifactId>
    <version>${lemone.version}</version>
</dependency>
```

### Gradle
```
compile 'com.lemone:module-xxx:${lemone.version}'
```

### 非Maven项目

点击以下任一链接，下载`module-xxx-${lemone.version}.jar`即可：

- [Maven中央库1](https://repo1.maven.org/maven2/com/lemone/module-xxx/${lemone.version}/)
- [Maven中央库2](http://repo2.maven.org/maven2/com/lemone/module-xxx/${lemone.version}/)

> 注意
> Lemone 最低JDK版本为JDK8及以上，目前针对SpringBoot项目做主要适配对象


-------------------------------------------------------------------------------

## 添砖加瓦

完善中，暂时不提供添砖加瓦，如有什么问题可随时留言...

-------------------------------------------------------------------------------


