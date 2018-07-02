# Toasty
加通知权限判断的Toast

##### 不会做动态图、具体demo 请下载：[Toasty.apk](browse/Toasty.apk)</br>

## 使用步骤

#### Step 1.依赖Toasty
Gradle
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```groovy
dependencies{
   compile 'com.github.LiuDengJian1995:Toasty:v1.2.1'  //最新版本
}
```
或者引用本地lib
```groovy
compile project(':toastyLibrary')
```

## 使用方法
  ToastyShow中的方法有makeText（无图标）、normal（无图标）、successLong（有成功图标）、infoLong（有警告图标）、errorShort（有失败图标）
  其中有XXX（Context上下文，String吐司内容，int吐司时间）
        XXXLong（Context上下文，String吐司内容）
        XXXShort（Context上下文，String吐司内容）
        XXXLong（String吐司内容）PS:不转入上下文要在Application中初始化ToastyConfig.getInstance().initApplication(Application);
        XXXShort（String吐司内容）
```
  ToastyShow.normal(this, "This is Default Toast", Toasty.LENGTH_SHORT);
              /*  ToastyShow.normalLong(this,"This is Default Toast");
                ToastyShow.normalShort(this,"This is Default Toast");
                ToastyShow.normalLong("This is Default Toast");
                ToastyShow.normalShort("This is Default Toast");*/
```


|ToastyConfig方法|说明|
|:---|:---|
|initApplication(Application)|得到Application的上下文 |
|setToastyView(IToastyView)|修改吐司的显示控件 |
|getToastyView()| 获取吐司的显示控件 |
|setGravity（Context，int）|设置吐司显示的位置，默认居中显示传入Gravity.CENTER居中显示、Gravity.TOP顶部显示、Gravity.BOTTOM底部显示|
|setForceOpen(boolean)|当通知权限没有打开时是否强制跳转应用管理页面打开，true强制false不强制|
|setHintForceOpen(boolean)|当通知权限没有打开时上下文为Activity时 弹出对话框提示用户是否强制跳转应用管理页面打开，优先于setForceOpen方法|



### 关于作者
    刘登建<br>
    贵州遵义<br>
	QQ：1044946823<br>
	

