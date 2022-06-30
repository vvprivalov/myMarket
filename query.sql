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

COMMIT;