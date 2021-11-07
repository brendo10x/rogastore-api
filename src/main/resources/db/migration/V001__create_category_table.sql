create SEQUENCE tb_category_id_seq;
create table tb_category (id int8 not null DEFAULT nextval('tb_category_id_seq'), name varchar(255) not null, primary key (id));