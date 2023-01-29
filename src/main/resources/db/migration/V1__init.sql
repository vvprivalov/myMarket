BEGIN;
DROP SCHEMA IF EXISTS mymarket CASCADE;
CREATE SCHEMA IF NOT EXISTS mymarket;
SET SEARCH_PATH = mymarket;

CREATE TABLE categories ( id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(45),
                        created_at timestamp default current_timestamp,
                        updated_at timestamp default current_timestamp);

INSERT INTO categories(name) VALUES ('Овощи'),
                                    ('Фрукты'),
                                    ('Хлебобулочные изделия'),
                                    ('Молочная продукция'),
                                    ('Конфеты');

CREATE TABLE products ( id BIGSERIAL PRIMARY KEY,
                        category_id bigint references categories (id),
                        name VARCHAR(45),
                        price INT,
                        created_at timestamp default current_timestamp,
                        updated_at timestamp default current_timestamp);

INSERT INTO products (category_id, name, price) VALUES
                                        (4,'Молоко', 2), (4, 'Сметана', 5), (4, 'Сыр', 36), (4, 'Творог', 10),
                                        (4, 'Сыворотка', 6), (3, 'Хлеб', 3), (3, 'Батон', 5), (3, 'Кекс', 11),
                                        (1, 'Картофель', 14), (1, 'Помидоры', 16), (1, 'Огурцы', 15),
                                        (1, 'Баклажан', 22), (1, 'Капуста', 17), (2, 'Яблоки', 34), (2, 'Груши', 36),
                                        (2, 'Арбуз', 21), (2, 'Дыня', 28), (2, 'Слива', 41), (2, 'Абрикос', 44),
                                        (2, 'Черешня', 46), (2, 'Вишня', 39), (5, 'Конфеты', 61), (5, 'Шоколад', 64);

CREATE TABLE users (id BIGSERIAL PRIMARY KEY,
                    username VARCHAR(30) NOT NULL UNIQUE,
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