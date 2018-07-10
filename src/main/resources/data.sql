INSERT INTO owner(first_name, last_name, email, password ) VALUES
	('Robert', 'King', 'robert.king@gmail.com', 'password'), --1
	('Nancy', 'Davolio', 'nancy.davolio@gmail.com', 'password'); --2

INSERT INTO address(street, city, postal_code, state, country) VALUES
	('4110 Old Redmond Rd.', 'Redmond', 98502, 'Washington', 'USA'); --1

INSERT INTO restaurant(owner_id, name, email, address_id, phone) VALUES
	(1, 'King Bistro', 'info@kingbistro.com', 1, '5558121'); --1