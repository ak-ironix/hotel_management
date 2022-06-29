
create database hotelmanagement;

use hotelmanagement;

create table customer(
c_id int primary key,
c_name varchar(20) not null,
ph_no int,
email varchar(30));


create table room(
r_id int primary key,
room_type varchar(10) check(room_type in ('1 bed','2 beds','3 beds')),
status varchar(20) check(status in ('available','not available')));


create table booking(
b_id int primary key,
c_id int,
r_id int,
foreign key(c_id) references customer(c_id),
foreign key(r_id) references room(r_id));


create table expense(
e_id int primary key,
e_name varchar(15),
price int);


create table payment(
p_id int primary key,
b_id int,
room_cost int,
expense_cost int,
total_cost int,
payment_type varchar(15) check(payment_type in('card','cash','upi')),
payment_status varchar(10) check(payment_status in('paid','not paid')),
foreign key(b_id) references booking(b_id));


insert into room values
(101, '1 bed', 'available'),
(102, '1 bed', 'available'),
(103, '2 beds', 'available'),
(104, '2 beds', 'available'),
(105, '3 beds', 'available'),
(106, '3 beds', 'available'),
(107, '3 beds', 'available');


delimiter //
create trigger roomunavail after insert on booking for each row
begin
update room set status = "not available" where r_id = new.r_id;
end
//

create trigger roomavail after delete on booking for each row
begin
update room set status = "available" where r_id = old.r_id;
end //

delimiter ;

delimiter //
create procedure tot_exp(in id int, out s1 int, out s2 int, out s3 int)
begin
select room_cost into s1 from payment where p_id = id;
select expense_cost into s2 from payment where p_id = id;
select s1+s2 into s3;
update payment set total_cost = s3 where p_id= id;
end
//

delimiter ;