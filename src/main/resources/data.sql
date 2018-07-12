INSERT INTO users(username, password, enabled) VALUES
    ('robking', 'password', true),
    ('davolio', 'password', true),
    ('a.fuller', 'password', true),
    ('leverling', 'password', true);

INSERT INTO authorities(username, authority) VALUES
	('robking', 'ROLE_ADMIN'),
	('davolio', 'ROLE_ADMIN'),
	('a.fuller', 'ROLE_USER'),
	('leverling', 'ROLE_USER');

INSERT INTO address(street, city, postal_code, state, country) VALUES
	('4110 Old Redmond Rd.', 'Redmond', '98502', 'Washington', 'USA'), --1
	('16551 NE 74th St.', 'Redmond', '98052', 'Washington', 'USA'), --2
	('41305 Kalahari Dr.', 'Wisconsin Dells', '53965', 'Wisconsin', 'USA'), --3
	('908 W. Capital Way', 'Tacoma', '98401', 'Washington', 'USA'), --4
	('507 - 20th Ave. E. Apt. 2A', 'Seattle', '98122', 'Washington', 'USA'), --5
	('14 Garrett Hill', 'London', 'SW1 8JR', '', 'UK'), --6
	('Coventry House Miner Rd.', 'London', 'EC2 7JR', '', 'UK'); --7

INSERT INTO owner(username, first_name, last_name, address_id ,email) VALUES
	('robking', 'Robert', 'King', 1,'robert.king@gmail.com'), --1
	('davolio', 'Nancy', 'Davolio', 6, 'nancy.davolio@gmail.com'); --2


INSERT INTO restaurant(owner_id, name, email, address_id, phone) VALUES
	(1, 'King Bistro', 'info@kingbistro.com', 1, '5558121'), --1
	(1, 'Restaurant Rex', 'contact@restaurantrex.com', 2, '5553119'), --2
	(2, 'Davolio Tavern', 'contact@davoliotavern.com', 7, '5553119'); --3

INSERT INTO employee(username, email, first_name, last_name, title, restaurant_id) VALUES
	('a.fuller', 'andrew.fuller@gmail.com', 'Andrew', 'Fuller', 'CHEF', 1), --1
	('leverling', 'janet.leverling@gmail.com', 'Janet', 'Leverling', 'WAITER', 1); --2

INSERT INTO restaurant_table(name, active, restaurant_id) VALUES
	('Table ONE', TRUE, 1), --1
	('Table TWO', TRUE, 1), --2
	('Table THREE', TRUE, 1), --3
	('Bar', TRUE, 1), --4
	('Table CORNER', TRUE, 1), --5
	('Table VIP', TRUE, 1); --6