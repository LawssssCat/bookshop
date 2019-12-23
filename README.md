# BookShop  - 书店项目

## 功能分析
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223085215525.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)

## 总体架构
MVC设计模式
+ Model：POJO（Plain Old Java Object）
+ Controller：Servlet
+ View：JSP+EL+JSTL

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223085731560.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)


## 技术选型
+ 数据库：MySQL
+ 数据源：C3P0
+ JDBC 工具：DBUtils
+ 事务解决方案：Filter + ThreadLocal
+ Ajax 解决方案：JQuery + JavaScript + JSON + google-gson
+ 层之间的解耦方案：工厂设计模式

## 难点分析
+ 通用的分页解决方案
+ 带查询条件的分页
+ 使用 Filter + ThreadLocal 解决事务

## 实体类设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223111432618.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)




## 数据表设计

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223113702856.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)

#### 创建表
+ **book_table**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223141835540.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)
```sql
# book_table
# mysql -uroot -proot --default-character-set=gbk 
# drop database if exists bookshop_db ; 
# create database bookshop_db charset utf8 ; 
# show databases ;
use bookshop_db ; 
# show  tables ; 
drop table if exists book_table ; 
create table  book_table(
	BOOK_ID INT PRIMARY KEY AUTO_INCREMENT , 
	AUTHOR VARCHAR(255),
	TITLE  VARCHAR(255),
	PRICE FLOAT , 
	PUBLISHING_DATE DATE , 
	SALES_AMOUNT INT , 
	STORE_NUMBER INT , 
	REMARK VARCHAR(255)
);
DESC  book_table ; 
```



+ **account_able**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223141925703.png)
```sql
# account_able
# mysql -uroot -proot --default-character-set=gbk 
# drop database if exists bookshop_db ; 
# create database bookshop_db charset utf8 ; 
# show databases ;
use bookshop_db ; 
# show  tables ; 
drop table if exists account_table ; 
create table account_table(
	ACCOUNT_ID INT PRIMARY KEY AUTO_INCREMENT , 
	BALANCE INT 
);
DESC account_table  ; 
```


+ **user_able**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223142258749.png)
```sql
# user_able
# mysql -uroot -proot --default-character-set=gbk 
# drop database if exists bookshop_db ; 
# create database bookshop_db charset utf8 ; 
# show databases ;
use bookshop_db ; 
# show  tables ; 
drop table if exists user_table ; 
create table user_table(
	USER_ID INT PRIMARY KEY AUTO_INCREMENT , 
	USERNAME VARCHAR(30),
	ACCOUNT_ID INT , 
	FOREIGN KEY(ACCOUNT_ID) REFERENCES account_table(ACCOUNT_ID)
);
DESC user_table  ;
```


+ **trade_able**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223142235709.png)
```sql
# trade_able
# mysql -uroot -proot --default-character-set=gbk 
# drop database if exists bookshop_db ; 
# create database bookshop_db charset utf8 ; 
# show databases ;
use bookshop_db ; 
# show  tables ; 
drop table if exists trade_table ; 
create table trade_table(
	TRADE_ID INT PRIMARY KEY AUTO_INCREMENT , 
	TRADE_TIME DATETIME, 
	USER_ID INT , 
	FOREIGN KEY(USER_ID) REFERENCES user_table(USER_ID)
);
DESC trade_table  ;
```



+ **trade_item_able**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223142706175.png)
```sql
# trade_item_able
# mysql -uroot -proot --default-character-set=gbk 
# drop database if exists bookshop_db ; 
# create database bookshop_db charset utf8 ; 
# show databases ;
use bookshop_db ; 
# show  tables ; 
drop table if exists trade_item_table ; 
create table trade_item_table(
	ITEM_ID INT PRIMARY KEY AUTO_INCREMENT , 
	QUANTITY INT , 
	BOOK_ID INT , 
	TRADE_ID INT ,
	FOREIGN KEY(TRADE_ID ) REFERENCES trade_table(TRADE_ID ) , 
	FOREIGN KEY(BOOK_ID ) REFERENCES book_table(BOOK_ID )
);
DESC trade_item_table ;
```



---
+ **show tables ;**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223142741595.png)

#### 插入 book_table 数据


```sql
# book_table  insert ..
# mysql -uroot -proot --default-character-set=gbk 
INSERT INTO book_table values(
	null , 	'伏尔泰' , 	'java基础' , 	30.3 , 	'2011-10-1', 	10 , 	20,  	'java基础 - 伏尔泰 - 无答案'
) , (
	null , 	'泰戈尔' , 	'javaWeb基础' , 	221.1 , 	'1999-11-2', 	2 , 	5,  	'javaWeb基础'
);
INSERT INTO book_table values(
	null , 	'伏特加' , 	'spring' , 	130.3 , 	'1998-5-12', 	12 , 	20,  	'spring - 伏特加'
) , (
	null , 	'马云' , 	'springMVC' , 	90.12 , 	'2016-1-3', 	2 , 	5,  	'springMVC基础 - 马云'
),(
	null , 	'伏特加' , 	'Mybatis' , 	99.0 , 	'2008-5-12', 	12 , 	20,  	'Mybatis - 伏特加'
) , (
	null , 	'奥斑马' , 	'log4j' , 	100.0 , 	'2016-1-3', 	2 , 	5,  	'log4j - 马云'
);
INSERT INTO book_table values(
	null , 	'lufy' , 	'git and gitHub' , 	90.3 , 	'2003-1-11', 	9 , 	5,  	'How to learn git and github ? I will tell you !'
) , (
	null , 	'韩梅梅' , 	'HelloWorld！' , 	1.1 , 	'1111-11-11', 	111 , 	11,  	'打印 HelloWorld 的各种姿势'
),(
	null , 	'李磊' , 	'QQ - 智多星' , 	33.0 , 	'2008-5-12', 	12 , 	20,  	'买吧，穹的话'
) , (
	null , 	'alan' , 	'数据结构与算法' , 	130.0, 	'2016-1-3', 	2 , 	5,  	'从链表到红黑树'
);
INSERT INTO book_table values(
	null , 	'monkey-D-Lufy' , 	'python' , 	190.3 , 	'2013-11-5', 	5 , 	15,  	'How to learn git and python ? I will tell you !'
) , (
	null , 	'张珊' , 	'I Love You' , 	122 , 	'1-1-1', 	1 , 	1,  	'各种姿势'
),(
	null , 	'李四' , 	'黑洞 - 编年史' , 	1.0 , 	'3333-1-23', 	3 , 	2,  	'傻逼才买'
) , (
	null , 	'alan' , 	'数据结构与算法2' , 	130.0, 	'2017-1-3', 	2 , 	5,  	'从红黑树到放弃'
);
select * from book_table ; 
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223172819889.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)

#### 插入 account_table 和 user_table 

```sql
INSERT INTO account_table values(
	null , 	3000
) ,(
	null , 	2000
) ;
INSERT INTO account_table values(
	null , 	5000
) ,(
	null , 	7000
) ;
select * from account_table ;

```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223173712448.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)



```sql
INSERT INTO user_table values(
	null , 	'AAA' , 1 
) ,(
	null , "BBB" , 2 
);
select * from user_table ;

```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019122317390310.png)

---


## 环境搭建
+ 加入C3P0数据源
+ 加入 dbutils 工具类
+ 加入 JTSL
+ 其他：使用随时加入

## Dao层设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191223133818883.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xhd3Nzc3NDYXQ=,size_16,color_FFFFFF,t_70)