INSERT INTO address(street, city, postal_code, state, country) VALUES
	('4110 Old Redmond Rd.', 'Redmond', 98502, 'Washington', 'USA'), --1
	('16551 NE 74th St.', 'Redmond', 98052, 'Washington', 'USA'), --2
	('41305 Kalahari Dr.', 'Wisconsin Dells', 53965, 'Wisconsin', 'USA'); --3

INSERT INTO owner(first_name, last_name,address_id ,email, password ) VALUES
	('Robert', 'King', 1,'robert.king@gmail.com', 'password'), --1
	('Nancy', 'Davolio',3, 'nancy.davolio@gmail.com', 'password'); --2


INSERT INTO restaurant(owner_id, name, email, address_id, phone) VALUES
	(1, 'King Bistro', 'info@kingbistro.com', 1, '5558121'), --1
	(1, 'Restaurant rex', 'contact@restaurantrex.com', 2, '5553119'), --2
	(2, 'Davolio Tavern', 'contact@davoliotavern.com', 3, '5553119'); --3

INSERT INTO employee(email,password,first_name,last_name,role,restaurant_id) VALUES
    ('pisti.isti@aol.com', 'asd123', 'Pisti','Isti', 'MANAGER', 1),
    ('johncruz@aol.com', 'asd123', 'John','Cruz', 'CHEF', 1),
    ('inezmartinez@aol.com', 'asd123', 'Inez','Martinez', 'WAITER', 1),
    ('johndoe@aol.com', 'asd123', 'John','Doe', 'BARTENDER', 1),
    ('jorgehuarez@aol.com', 'asd123', 'Jorge','Huarez', 'MANAGER', 2),
    ('johnmartinez@aol.com', 'asd123', 'John','martinez', 'CHEF', 2),
    ('mariadelasol@aol.com', 'asd123', 'Maria','De La Sol', 'WAITER', 2),
    ('juanramirez@aol.com', 'asd123', 'Juan','Martinez', 'BARTENDER', 2);

INSERT INTO item(name, price, category, restaurant_id) VALUES
    ('Chicken soup',1000,'SOUP', 1),
    ('Beef steak',1200,'BEEF_DISH', 1),
    ('Coors Light',1000,'ALCOHOLIC', 1),
    ('Taco de la maar',1000,'BEEF_DISH', 2),
    ('Chicken tortilla',1000,'CHICKEN_DISH', 2),
    ('Tequilla grande',1000,'ALCOHOLIC', 2);

