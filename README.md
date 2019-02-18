# 项目门户
管理组织或自己的项目，提供统一的项目入口，方便在任何地方进行访问
项目演示地址：[项目门户](http://47.106.230.123:83) 账号/密码:自行注册获取
## 项目组成结构
+ screenshots：截图
+ union-project-portal：项目门户主程序
+ union-commons-util：基础工具类
+ union-base-component：项目组件（包含邮件发送、OSS、短信、MQTT、OAuth）
## 项目技术
+ 后端技术
   + SpringBoot
   + Java Mail   
   + Thymeleaf
   + Mybatis
   + Mysql
+ 前端技术
   + Ace Admin页面框架
   + Jquery
   + Bootstrap
   + layui
## 项目运行
1.准备数据库,根据sql文件（/union-project-portal/文档/数据库/project_portal.sql）建立数据库   

2.编译打包项目union-commons-util  
`mvn clean install`  

3.编译打包项目union-base-component  
`mvn clean install`  

4.配置邮件发送信息  
/union-project-portal/src/main/resources/smtp.properties  

5.修改连接的数据库配置  
/union-project-portal/src/main/resources/application-dev.properties  
/union-project-portal/src/main/resources/application-prodproperties  

6.编译项目union-project-portal 
`mvn clean package`  

7.运行打好的jar,进入union-project-portal/target目录下执行  
`java -jar union-project-portal-2.0.6.RELEASE.jar --spring-profiles.active=dev`  

8.访问[http://localhost:83](http://localhost:83)  

## 使用讲解  
1.主界面  
![主界面](https://github.com/youpanpan/project_portal/blob/master/screenshots/%E9%A1%B9%E7%9B%AE%E9%97%A8%E6%88%B7%E4%B8%BB%E7%95%8C%E9%9D%A2.png?raw=true)  
主界面中如果用户未登录，则最上方有登录/注册链接  

2.登录/注册  
![登录](https://github.com/youpanpan/project_portal/blob/master/screenshots/%E7%99%BB%E5%BD%95.png?raw=true)   
![注册](https://github.com/youpanpan/project_portal/blob/master/screenshots/%E6%B3%A8%E5%86%8C.png?raw=true)   

3.管理自己的项目    
![管理项目](https://github.com/youpanpan/project_portal/blob/master/screenshots/%E7%99%BB%E5%BD%95%E5%90%8E.png?raw=true)  

在项目上鼠标右键，出现3个操作：查看、编辑、删除  
![鼠标右键](https://github.com/youpanpan/project_portal/blob/master/screenshots/%E9%BC%A0%E6%A0%87%E5%8F%B3%E9%94%AE.png?raw=true)   

4.管理员界面  
![管理员界面](https://github.com/youpanpan/project_portal/blob/master/screenshots/%E7%AE%A1%E7%90%86%E7%95%8C%E9%9D%A2.png?raw=true)  
5.项目管理  
管理系统已存在的项目  
6.权限管理  
- 用户管理  
管理系统用户
- 角色管理  
系统已有的角色：开发人员、系统管理员  
![角色管理](https://github.com/youpanpan/project_portal/blob/master/screenshots/%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86.png?raw=true)  
- URL管理  
URL包括系统菜单URL、功能URL  
![URL管理](https://github.com/youpanpan/project_portal/blob/master/screenshots/URL%E7%AE%A1%E7%90%86.png?raw=true)  
