INSERT INTO tb_category (id, name) VALUES (1, 'Productivity') ON CONFLICT (id) DO NOTHING;
INSERT INTO tb_category (id, name) VALUES (2, 'Game') ON CONFLICT (id) DO NOTHING;

select setval('tb_category_id_seq', nextval('tb_category_id_seq') + 1);

INSERT INTO tb_app (id, description, name, price, category_id) VALUES (1,'Productivity app', 'Pomodoro', 12.50, 1) ON CONFLICT (id) DO NOTHING;;
INSERT INTO tb_app (id,description, name, price, category_id) VALUES (2,'Retro Super Mario App', 'Super Mário', 1.00, 2) ON CONFLICT (id) DO NOTHING;

select setval('tb_app_id_seq', nextval('tb_app_id_seq') + 1 );