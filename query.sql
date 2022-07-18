BEGIN;
DROP SCHEMA IF EXISTS mymarket CASCADE;
CREATE SCHEMA IF NOT EXISTS mymarket;
SET SEARCH_PATH = mymarket;
CREATE TABLE products (id BIGSERIAL PRIMARY KEY, name VARCHAR(45), price INT);
INSERT INTO products (name, price) VALUES ('Молоко', 2), ('Сметана', 5), ('Сыр', 36), ('Творог', 10),
                                          ('Сыворотка', 6), ('Хлеб', 3), ('Батон', 5), ('Кекс', 11),
                                          ('Картофель', 14), ('Помидоры', 16), ('Огурцы', 15),
                                          ('Баклажан', 22), ('Капуста', 17), ('Яблоки', 34), ('Груши', 36),
                                          ('Арбуз', 21), ('Дыня', 28), ('Слива', 41), ('Абрикос', 44),
                                          ('Черешня', 46), ('Вишня', 39), ('Конфеты', 61), ('Шоколад', 64);
CREATE TABLE users (id BIGSERIAL PRIMARY KEY,
                    username VARCHAR(30) NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    email VARCHAR(50) UNIQUE,
                    created_at TIMESTAMP DEFAULT current_timestamp,
                    updated_at TIMESTAMP DEFAULT current_timestamp
);
CREATE TABLE roles (id BIGSERIAL PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    created_at timestamp default current_timestamp,
                    updated_at timestamp default current_timestamp
);
CREATE TABLE users_roles (  user_id BIGINT NOT NULL REFERENCES users (id),
                            role_id BIGINT NOT NULL REFERENCES roles (id),
                            PRIMARY KEY (user_id, role_id)
);
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES ('user' , '$2a$12$yLU17RkJGpz2BA1j8ii2kuKd7nF44hRLUNrgotWZOIH0L8vND2HBO', 'user_bob@yandex.ru'),
       ('admin', '$2a$12$yLU17RkJGpz2BA1j8ii2kuKd7nF44hRLUNrgotWZOIH0L8vND2HBO', 'admin_bob@yandex.ru');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);
COMMIT;