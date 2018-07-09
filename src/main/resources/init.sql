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
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS address;


CREATE TABLE address (
	address_id SERIAL PRIMARY KEY,
	street TEXT,
	city TEXT,
	postal_code TEXT,
	state TEXT,
	country TEXT
);

CREATE TABLE owner (
	owner_id SERIAL PRIMARY KEY,
	uuid UUID NOT NULL,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	address_id INTEGER NOT NULL,
	email TEXT NOT NULL,
	phone INTEGER NOT NULL,
	password TEXT NOT NULL,
	CONSTRAINT email_not_empty CHECK (email <> ''),
	CONSTRAINT first_name_not_empty CHECK (first_name <> ''),
	CONSTRAINT last_name_not_empty CHECK (last_name <> ''),
	FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE restaurant (
	restaurant_id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	address_id INTEGER NOT NULL,
	email TEXT NOT NULL,
	phone INTEGER NOT NULL,
	owner_id INTEGER NOT NULL,
	CONSTRAINT email_not_empty CHECK (email <> ''),
	FOREIGN KEY (owner_id) REFERENCES owner(owner_id),
	FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE employee (
	employee_id SERIAL PRIMARY KEY,
	email TEXT NOT NULL,
	password TEXT NOT NULL,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	role TEXT NOT NULL,
	restaurant_id INTEGER NOT NULL,
	hour_rate INTEGER DEFAULT NULL,
	CONSTRAINT email_not_empty CHECK (email <> ''),
	CONSTRAINT first_name_not_empty CHECK (first_name <> ''),
	CONSTRAINT last_name_not_empty CHECK (last_name <> ''),
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id)
);

CREATE TABLE ingredient (
	ingredient_id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	allergen TEXT
);

CREATE TABLE item (
	item_id SERIAL PRIMARY KEY,
	price NUMERIC NOT NULL,
	comment TEXT,
	category TEXT NOT NULL,
	restaurant_id INTEGER NOT NULL,
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id)
);

CREATE TABLE item_ingredient (
	item_id INTEGER,
	ingredient_id INTEGER,
	FOREIGN KEY (item_id) REFERENCES item(item_id),
	FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)
);

CREATE TABLE menu (
	menu_id SERIAL PRIMARY KEY,
	title TEXT NOT NULL,
	active BOOLEAN DEFAULT FALSE
);

CREATE TABLE menu_item (
	menu_id INTEGER,
	item_id INTEGER,
	FOREIGN KEY (menu_id) REFERENCES menu(menu_id),
	FOREIGN KEY (item_id) REFERENCES item(item_id)
);

CREATE TABLE restaurant_table (
	restaurant_table_id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	active BOOLEAN DEFAULT FALSE,
	restaurant_id INTEGER NOT NULL,
	employee_id INTEGER DEFAULT NULL,
	FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id),
	FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE seat (
	seat_id SERIAL PRIMARY KEY,
	active BOOLEAN,
	restaurant_table_id INTEGER NOT NULL,
	FOREIGN KEY (restaurant_table_id) REFERENCES restaurant_table(restaurant_table_id)
);

CREATE TABLE invoice (
	invoice_id SERIAL PRIMARY KEY,
	total NUMERIC NOT NULL,
	date DATE DEFAULT current_date
);

CREATE TABLE order_item (
	order_item_id SERIAL PRIMARY KEY,
	item_id SERIAL NOT NULL,
	quantity INTEGER NOT NULL,
	comment TEXT,
	FOREIGN KEY (item_id) REFERENCES item(item_id)
);

CREATE TABLE customer_order (
	customer_order_id SERIAL PRIMARY KEY,
	seat_id INTEGER NOT NULL,
	employee_id INTEGER NOT NULL,
	ordering_time DATE DEFAULT current_date,
	order_item_id INTEGER NOT NULL,
	invoice_id INTEGER DEFAULT NULL,
	FOREIGN KEY (seat_id) REFERENCES seat(seat_id),
	FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
	FOREIGN KEY (order_item_id) REFERENCES order_item(order_item_id),
	FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id)
);