INSERT INTO tb_category (id, name) VALUES (1, 'Produtividade') ON CONFLICT (id) DO NOTHING;
INSERT INTO tb_category (id, name) VALUES (2, 'Jogo') ON CONFLICT (id) DO NOTHING;

INSERT INTO tb_app (id_app, description, name, price, category_id) VALUES (1, 'App para gestão de tempo', 'Florest', 16.00, 1) ON CONFLICT (id_app) DO NOTHING;;
INSERT INTO tb_app (id_app, description, name, price, category_id) VALUES (2, 'App retrô do Super Mário', 'Super Mário', 1.00, 2) ON CONFLICT (id_app) DO NOTHING;