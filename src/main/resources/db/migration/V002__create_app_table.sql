create SEQUENCE tb_app_id_seq;
create table tb_app (id int8 not null DEFAULT nextval('tb_app_id_seq'), description varchar(255), name varchar(255) not null, price numeric(19, 2) not null, category_id int8 not null, primary key (id));
alter table if exists tb_app add constraint fk_app_category foreign key (category_id) references tb_category;
