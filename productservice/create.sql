create sequence hibernate_sequence start with 1 increment by 1
create table product (id bigint not null, name varchar(255) not null, primary key (id))
