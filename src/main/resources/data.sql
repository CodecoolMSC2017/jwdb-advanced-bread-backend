INSERT INTO users(username, password, enabled) VALUES
    ('robking', 'password', true),
    ('davolio', 'password', true);

INSERT INTO authorities(username, authority) VALUES
	('robking', 'ROLE_ADMIN'),
	('davolio', 'ROLE_ADMIN');

INSERT INTO address(street, city, postal_code, state, country) VALUES
	('4110 Old Redmond Rd.', 'Redmond', 98502, 'Washington', 'USA'), --1
	('16551 NE 74th St.', 'Redmond', 98052, 'Washington', 'USA'), --2
	('41305 Kalahari Dr.', 'Wisconsin Dells', 53965, 'Wisconsin', 'USA'); --3

INSERT INTO owner(username, first_name, last_name,address_id ,email) VALUES
	('robking', 'Robert', 'King', 1,'robert.king@gmail.com'), --1
	('davolio', 'Nancy', 'Davolio',3, 'nancy.davolio@gmail.com'); --2


INSERT INTO restaurant(owner_id, name, email, address_id, phone) VALUES
	(1, 'King Bistro', 'info@kingbistro.com', 1, '5558121'), --1
	(1, 'Restaurant rex', 'contact@restaurantrex.com', 2, '5553119'), --2
	(2, 'Davolio Tavern', 'contact@davoliotavern.com', 3, '5553119'); --3