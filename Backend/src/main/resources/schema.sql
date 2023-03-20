DROP TABLE IF EXISTS categories, cuisines, users, recipes, ingredients, saved_recipes, CASCADE;

CREATE TABLE categories
(
	id_category integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name text NOT NULL UNIQUE
);

CREATE TABLE cuisines
(
	id_cuisine integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name text NOT NULL UNIQUE
);

CREATE TABLE users
(
	id_user integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	login text NOT NULL UNIQUE,
	password text NOT NULL,
	email text NOT NULL UNIQUE
);

CREATE TABLE recipes
(
	id_recipe integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	id_user integer NOT NULL REFERENCES users ON DELETE CASCADE ON UPDATE CASCADE,
	name text NOT NULL UNIQUE,
	image_url text NOT NULL UNIQUE,
	date_added date NOT NULL,
	cuisine integer NOT NULL REFERENCES cuisines ON DELETE CASCADE ON UPDATE CASCADE,
	category integer NOT NULL REFERENCES categories ON DELETE CASCADE ON UPDATE CASCADE,
	cooking_time integer NOT NULL,
	description text NOT NULL UNIQUE,
	recipe text NOT NULL UNIQUE
);

CREATE TABLE ingredients
(
	id_ingredient integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	id_recipe integer NOT NULL REFERENCES recipes ON DELETE CASCADE ON UPDATE CASCADE,
	name text NOT NULL,
	grams numeric NOT NULL
);

CREATE TABLE saved_recipes
(
	id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	id_user integer NOT NULL REFERENCES users ON DELETE CASCADE ON UPDATE CASCADE,
	id_recipe integer NOT NULL REFERENCES recipes ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO categories (name) VALUES
	('Бульоны'),
	('Выпечка и десерты'),
	('Завтраки'),
	('Закуски'),
	('Напитки'),
	('Основные блюда'),
	('Паста'),
	('Пиццы'),
	('Салаты'),
	('Супы'),
	('Соусы и маринады'),
	('Сэндвичи');
	
INSERT INTO cuisines (name) VALUES
	('Американская'),
	('Армянская'),
	('Белорусская'),
	('Британская'),
	('Вьетнамская'),
	('Греческая'),
	('Грузинская'),
	('Европейская'),
	('Индийская'),
	('Испанская'),
	('Итальянская'),
	('Китайская'),
	('Корейская'),
	('Мексиканская'),
	('Паназиатская'),
	('Русская'),
	('Средиземноморская'),
	('Тайская'),
	('Узбекская'),
	('Украинская'),
	('Французская'),
	('Японская');

INSERT INTO users (login, password, email) VALUES
    ('polinafomina', 'qwerty123', 'fominapolia2001@yandex.ru'),
    ('povarverona', 'veronaitaly', 'povar.verona@gmail.com');

INSERT INTO recipes (id_user, name, image_url, date_added, cuisine, category,
                     cooking_time, description, recipe) VALUES
    (1, 'Борщ', '140.png', '2023-03-10', 16, 10, 120,
        'Ароматный борщ с пампушками', 'Порезать овощи и сварить'),
    (1, 'Щи', '141.png', '2023-03-11', 16, 10, 90,
        'Вкусные щи с гренками', 'Поджарить гренки и сварить щи'),
    (2, 'Блины', '142.png', '2023-03-12', 16, 2, 20,
        'Тонкие блинчики с маслом', 'Приготовить тесто и пожарить блины'),
    (2, 'Вафли', '143.png', '2023-03-13', 8, 2, 45,
        'Пышные вафли с шоколадом', 'Приготовить тесто, растопить шоколад'),
    (2, 'Стейк', '144.png', '2023-03-14', 21, 6, 20,
        'Стейк Нью-Йорк', 'Посолить и поперчить мясо, пожарить на гриле');

INSERT INTO ingredients (id_recipe, name, grams) VALUES
    (1, 'Свекла', 200),
    (1, 'Морковь', 120),
    (1, 'Мясо', 400),
    (2, 'Капуста', 300),
    (2, 'Морковь', 100),
    (2, 'Мясо', 500),
    (3, 'Молоко', 250),
    (3, 'Яйцо', 120),
    (4, 'Молоко', 250),
    (4, 'Мука', 70),
    (4, 'Шоколад горький', 100),
    (5, 'Мясо', 320);

INSERT INTO saved_recipes (id_user, id_recipe) VALUES
    (1, 3),
    (2, 1),
    (2, 2);