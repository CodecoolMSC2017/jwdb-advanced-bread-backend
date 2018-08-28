DROP TABLE IF EXISTS customer_order;
DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS restaurant_table;
DROP TABLE IF EXISTS item_ingredient;
DROP TABLE IF EXISTS menu_item;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
	username VARCHAR(255) UNIQUE NOT NULL,
	password TEXT NOT NULL,
	enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
	username VARCHAR(255) NOT NULL,
	authority varchar(255) NOT NULL,
	CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username) ON UPDATE CASCADE
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);

CREATE TABLE address (
	id SERIAL PRIMARY KEY,
	street TEXT,
	city TEXT,
	postal_code TEXT,
	state TEXT,
	country TEXT,
	enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE restaurant (
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	address_id INTEGER NOT NULL,
	email TEXT NOT NULL,
	phone TEXT NOT NULL,
	enabled BOOLEAN default true,
	CONSTRAINT restaurant_email_not_empty CHECK (email <> ''),
	FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE employee (
	id SERIAL PRIMARY KEY,
	user_id INTEGER,
	email TEXT NOT NULL,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	title TEXT NOT NULL,
	address_id INTEGER NOT NULL,
	phone TEXT,
	restaurant_id INTEGER,
	hour_rate INTEGER DEFAULT NULL,
	enabled BOOLEAN DEFAULT FALSE,
	CONSTRAINT employee_email_not_empty CHECK (email <> ''),
	CONSTRAINT first_name_not_empty CHECK (first_name <> ''),
	CONSTRAINT last_name_not_empty CHECK (last_name <> ''),
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (address_id) REFERENCES address(id),
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE ingredient (
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	allergen TEXT DEFAULT NULL,
	enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE item (
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	price NUMERIC NOT NULL,
	category TEXT NOT NULL,
	subcategory TEXT,
	restaurant_id INTEGER NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE item_ingredient (
	item_id INTEGER REFERENCES item ON DELETE CASCADE,
	ingredient_id INTEGER REFERENCES ingredient ON DELETE CASCADE
);

CREATE TABLE menu (
	id SERIAL PRIMARY KEY,
	title TEXT NOT NULL,
	active BOOLEAN DEFAULT FALSE,
	enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE menu_item (
	menu_id INTEGER,
	item_id INTEGER,
	FOREIGN KEY (menu_id) REFERENCES menu(id),
	FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE restaurant_table (
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	active BOOLEAN DEFAULT FALSE,
	arrival_time TIMESTAMP,
	restaurant_id INTEGER NOT NULL,
	employee_id INTEGER DEFAULT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
	FOREIGN KEY (employee_id) REFERENCES employee(id)
);

CREATE TABLE seat (
	id SERIAL PRIMARY KEY,
	active BOOLEAN,
	restaurant_table_id INTEGER NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	FOREIGN KEY (restaurant_table_id) REFERENCES restaurant_table(id)
);

CREATE TABLE invoice (
	id SERIAL PRIMARY KEY,
	total NUMERIC NOT NULL,
	paid BOOLEAN DEFAULT FALSE,
	enabled BOOLEAN DEFAULT TRUE,
	date TIMESTAMP DEFAULT current_date
);

CREATE TABLE order_item (
	id SERIAL PRIMARY KEY,
	item_id INTEGER NOT NULL,
	quantity INTEGER NOT NULL,
	comment TEXT,
	enabled BOOLEAN DEFAULT TRUE,
	ready BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE customer_order (
	id SERIAL PRIMARY KEY,
	seat_id INTEGER NOT NULL,
	employee_id INTEGER NOT NULL,
	ordering_time TIMESTAMP DEFAULT current_date,
	order_item_id INTEGER NOT NULL,
	invoice_id INTEGER DEFAULT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	FOREIGN KEY (seat_id) REFERENCES seat(id),
	FOREIGN KEY (employee_id) REFERENCES employee(id),
	FOREIGN KEY (order_item_id) REFERENCES order_item(id),
	FOREIGN KEY (invoice_id) REFERENCES invoice(id)
);

ALTER TABLE restaurant
ADD COLUMN owner_id INTEGER;
ALTER TABLE restaurant
ADD CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES employee(id);


