INSERT INTO users(username, password, enabled) VALUES
    ('robking', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --1
    ('davolio', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --2
    ('a.fuller', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --3
    ('leverling', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --4
    ('stan', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --5
    ('moreno', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --6
    ('manders', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --7
    ('hardy', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true); --8

INSERT INTO authorities(username, authority) VALUES
	('robking', 'ROLE_ADMIN'),
	('davolio', 'ROLE_ADMIN'),
	('a.fuller', 'ROLE_USER'),
	('leverling', 'ROLE_USER'),
	('stan', 'ROLE_USER'),
	('moreno', 'ROLE_USER'),
	('manders', 'ROLE_USER'),
	('hardy', 'ROLE_USER');

INSERT INTO address(street, city, postal_code, state, country) VALUES
	('4110 Old Redmond Rd.', 'Redmond', '98502', 'Washington', 'USA'), --1
	('16551 NE 74th St.', 'Redmond', '98052', 'Washington', 'USA'), --2
	('41305 Kalahari Dr.', 'Wisconsin Dells', '53965', 'Wisconsin', 'USA'), --3
	('908 W. Capital Way', 'Tacoma', '98401', 'Washington', 'USA'), --4
	('507 - 20th Ave. E. Apt. 2A', 'Seattle', '98122', 'Washington', 'USA'), --5
	('14 Garrett Hill', 'London', 'SW1 8JR', '', 'UK'), --6
	('Coventry House Miner Rd.', 'London', 'EC2 7JR', '', 'UK'),--7
    ('120 Hanover Sq.', 'Strasbourg', '67000', '', 'France'), --8
    ('Berkeley Gardens 12  Brewery', 'London', 'WX1 6LT', '', 'UK'), -- 9
    ('Berliner Platz 43', 'Berlin', '80805', '', 'Germany'); --10

INSERT INTO owner(user_id, first_name, last_name, address_id ,email) VALUES
	(1, 'Robert', 'King', 1,'robert.king@gmail.com'), --1
	(2, 'Nancy', 'Davolio', 6, 'nancy.davolio@gmail.com'); --2


INSERT INTO restaurant(owner_id, name, email, address_id, phone) VALUES
	(1, 'King Bistro', 'info@kingbistro.com', 1, '5558121'), --1
	(1, 'Restaurant Rex', 'contact@restaurantrex.com', 2, '5553119'), --2
	(2, 'Davolio Cafe', 'contact@davoliocafe.com', 7, '5553119'); --3

INSERT INTO employee(user_id, email, first_name, last_name, title,address_id, restaurant_id) VALUES
	(3, 'andrew.fuller@gmail.com', 'Andrew', 'Fuller', 'CHEF',5, 1), --1
	(4, 'janet.leverling@gmail.com', 'Janet', 'Leverling', 'WAITER',6, 1), --2
	(5, 'stan.super@gmail.com', 'Stan', 'Super', 'WAITER',7, 3), --3
	(6, 'maria.anders@gmail.com', 'Maria', 'Anders', 'WAITER',8, 1), --4
	(7, 'antonio.moreno@gmail.com', 'Antonio', 'Moreno', 'BARTENDER',9, 3), --5
    (8, 'thomas.hardy@gmail.com', 'Thomas', 'Hardy', 'BARTENDER',10, 1); --6

INSERT INTO restaurant_table(name, active, restaurant_id) VALUES
	('Table ONE', TRUE, 1), --1
	('Table TWO', TRUE, 1), --2
	('Table THREE', TRUE, 1), --3
	('Bar', TRUE, 1), --4
	('Table CORNER', TRUE, 1), --5
	('Table VIP', TRUE, 1), --6
	('Second restaurant First Table', TRUE, 3); --7

INSERT INTO seat(active, restaurant_table_id) VALUES
    (TRUE, 1), --1
    (TRUE, 1), --2
    (TRUE, 2), --3
    (TRUE, 2), --4
    (TRUE, 3), --5
    (TRUE, 3), --6
    (TRUE, 4), --7
    (TRUE, 4), --8
    (TRUE, 5), --9
    (TRUE, 5), --10
    (TRUE, 6), --11
    (TRUE, 6), --12
    (TRUE, 7), --13
    (TRUE, 7); --14

INSERT INTO ingredient(name) VALUES
    ('ketchup'), --1
    ('cheese'), --2
    ('oregano'), --3
    ('ham'), --4
    ('mushroom'), --5
    ('salami'), --6
    ('smoked paw'), --7
    ('cottage cheese'), --8
    ('bacon'), --9
    ('sea fruits'), --10
    ('lemon'), --11
    ('tuna'), --12
    ('corn'), --13
    ('sausage'), --14
    ('pea'), --15
    ('chicken breasts'), --16
    ('prosciutto'), --17
    ('onion'), --18
    ('beans'), --19
    ('smoked cheese'); --20

INSERT INTO item(name ,price ,category ,restaurant_id) VALUES
    ('Pizza Margareta', 1150,'PIZZA',1),
    ('Pizza Prosciutto',1350,'PIZZA',1),
    ('Pizza Funghi', 1150,'PIZZA',1),
    ('Pizza Salami', 1350,'PIZZA',1),
    ('Pizza Hawaii', 1350,'PIZZA',1),
    ('Tárkonyos vadmalac leves', 1290,'SOUP',1),
    ('Jókai bableves', 1100,'SOUP',1),
    ('Harcsapaprikás kapros túrós sztrapacskával', 2590,'FISH_MAIN',1),
    ('Rántott halfilé, rizs, tartármártás', 1900,'FISH_MAIN',1),
    ('Rántott csirkemell hasábburgonyával', 1750,'CHICKEN_DISH',1),
    ('Juhtúróval-baconnel töltött csirkemell parázs burgonya', 2200,'CHICKEN_DISH',1),
    ('Zöldséges marharagu óriás zsemlegombóccal', 1900,'BEEF_DISH',1),
    ('Zöldséges marharagu óriás zsemlegombóccal', 1900,'BEEF_DISH',1),
    ('Rántott szelet, hasábburgonyával', 1690,'PORK_DISH',1),
    ('Pizza kenyérben sült kemencés csülök lyoni hagymával', 2390,'PORK_DISH',1),
    ('Rántott gomba, rizs, tartármártás', 1690,'VEGETARIAN',1),
    ('Rántott sajt, hasábburgonya, tartár mártás', 1690,'VEGETARIAN',1),
    ('Coors Light Lager', 390, 'ALCOHOLIC',1),
    ('Chivas Regal', 790, 'ALCOHOLIC',1),
    ('Coca Cola', 390, 'BEVERAGE',1),
    ('Fanta', 390, 'BEVERAGE',1),
    ('Espresso', 290, 'COFFEE',1),
    ('Latte Machiato', 290, 'COFFEE',1),
    ('Earl Grey', 290, 'TEA',1),
    ('Rooibos', 290, 'TEA',1);

INSERT INTO item_ingredient (item_id, ingredient_id) VALUES
     (15, 20),
     (8, 4),
     (18, 19),
     (20, 13),
     (17, 11),
     (15, 19),
     (20, 20),
     (12, 10),
     (14, 19),
     (13, 4),
     (10, 19),
     (4, 18),
     (16, 17),
     (17, 19),
     (12, 4),
     (5, 11),
     (7, 20),
     (15, 10),
     (15, 16),
     (23, 12),
     (20, 15),
     (22, 9),
     (19, 19),
     (3, 16),
     (17, 19),
     (10, 17),
     (4, 4),
     (17, 18),
     (8, 3),
     (12, 15),
     (8, 14),
     (15, 5),
     (13, 12),
     (22, 3),
     (1, 15),
     (25, 14),
     (4, 20),
     (17, 18),
     (22, 8),
     (12, 6),
     (20, 5),
     (2, 19),
     (1, 11),
     (10, 4),
     (7, 8),
     (15, 9),
     (25, 13),
     (2, 10),
     (12, 7),
     (4, 17),
     (20, 19),
     (14, 1),
     (3, 14),
     (16, 7),
     (24, 10),
     (22, 16),
     (5, 3),
     (7, 14),
     (12, 12),
     (12, 6),
     (18, 13),
     (15, 17),
     (10, 8),
     (25, 13),
     (22, 11),
     (7, 4),
     (12, 16),
     (22, 17),
     (11, 13),
     (23, 12),
     (6, 4),
     (4, 3),
     (5, 17),
     (24, 20),
     (24, 10),
     (8, 3),
     (20, 8),
     (19, 6),
     (20, 5),
     (3, 9),
     (3, 13),
     (23, 8),
     (18, 7),
     (22, 7),
     (12, 2),
     (5, 12),
     (17, 4),
     (1, 13),
     (22, 8),
     (4, 1),
     (20, 7),
     (12, 17),
     (11, 18),
     (8, 13),
     (11, 20),
     (15, 20),
     (23, 3),
     (13, 17),
     (5, 12),
     (3, 20);

INSERT INTO order_item (item_id, quantity, comment) VALUES
    (1, 2, 'no chili');
