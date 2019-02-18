# 项目门户
管理组织或自己的项目，提供统一的项目入口，方便在任何地方进行访问
项目演示地址：[项目门户](http://47.106.230.123:83) 账号/密码:自行注册登录获取
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

3. 编译打包项目union-base-component  
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
![主界面]()  
主界面中如果用户未登录，则最上方有登录/注册链接  

2.登录/注册  
![登录]()   
![注册]()   
3.管理自己的项目    
![管理项目]()  
在项目上鼠标右键，出现3个操作：查看、编辑、删除  
4.管理员界面  

5.项目管理  
![项目管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E9%A1%B9%E7%9B%AE%E7%AE%A1%E7%90%86.png?raw=true)  
6.权限管理  
- 用户管理  
管理系统用户，用户不允许注册，只能通过管理员添加  
- 角色管理  
系统已有的角色：项目管理员、开发人员、系统管理员  
![角色管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86.png?raw=true)  
- URL管理  
URL包括系统菜单URL、功能URL  
![URL管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/URL%E7%AE%A1%E7%90%86.png?raw=true)  
