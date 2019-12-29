# 创建db -----------------------------
create database if not exists test_db charset utf8 ; 
use test_db ; 
drop table if exists test1  ; 
create table if not exists test1 (
	id int primary key auto_increment , 
    stock int check(stock > 0 ) 
) ; 
desc test1 ; 
# 插入数据、查看-------------------------------------------
insert into test1 (stock) value( 1 ) ; 
insert into test1 (stock) value( -2 ) ; 
select * from test1 ; 
update test1 set stock = stock-2 ; 
select * from test1 ; 
