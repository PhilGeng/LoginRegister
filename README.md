# LoginRegister
之前看到一个MD风格的Login界面，但是可自定义处不是很多，所以我就自己仿照了一个。
#### [![](https://jitpack.io/v/PhilGeng/LoginRegister.svg)](https://jitpack.io/#PhilGeng/LoginRegister)
# 效果图
![效果图](https://github.com/PhilGeng/LoginRegister/blob/master/screenshot/2.gif)

# 如何使用
## gradle配置
build.gradle 配置如下
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
然后dependency添加：
```java
dependencies {
	compile 'com.github.PhilGeng:LoginRegister:v1.0.0'
}
```

## 或者maven配置
```xml
<repositories>
	<repository>
		 <id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
然后添加 dependency
```xml
<dependency>
	<groupId>com.github.PhilGeng</groupId>
	<artifactId>LoginRegister</artifactId>
	<version>1.0.0</version>
</dependency>
```

## 布局中使用：
```xml
<club.tranch.loginregister.LoginRegister
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```


# 属性
```java
setLoginTitle(String)               //设置 登陆页面 标题
setLoginFirstText(String)           //设置 登陆页面 第一个输入框 提示文字
setLoginSecondText(String)          //设置 登陆页面 第二个输入框 提示文字
setLoginBtnText(String)             //设置 登陆页面 按钮 文字
setRegisterTitle(String)            //设置 注册页面 标题
setRegisterFirstText(String)        //设置 注册页面 第一个输入框 提示文字
setRegisterSecondText(String)       //设置 注册页面 第二个输入框 提示文字
setRegisterThridText(String)        //设置 注册页面 第三个输入框 提示文字
setRegisterBtnText(String)          //设置 注册页面 按钮 文字


getLoginTitle(String)               //获取 登陆页面 标题
getLoginFirstText(String)           //获取 登陆页面 第一个输入框 文字
getLoginSecondText(String)          //获取 登陆页面 第二个输入框 文字
getLoginBtnText(String)             //获取 登陆页面 按钮 文字
getRegisterTtitle(String)           //获取 注册页面 标题
getRegisterFirstText(String)        //获取 注册页面 第一个输入框 文字
getRegisterSecondText(String)       //获取 注册页面 第二个输入框 文字
getRegisterThridText(String)        //获取 注册页面 第三个输入框 文字
getRegisterBtnText(String)          //获取 注册页面 按钮 文字


``` 
