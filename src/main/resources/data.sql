INSERT INTO users(username, password, enabled) VALUES
    ('robking', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --1
    ('davolio', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --2
    ('fuller', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --3
    ('leverling', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --4
    ('stan', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --5
    ('anders', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --6
    ('moreno', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --7
    ('hardy', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true), --8
    ('murvai', '$2a$04$mrVOIvQZKlIPW0BYXwtaWuV1sZDsoSLSixtxYUhRZh3jpjfKDf736', true); --9

INSERT INTO authorities(username, authority) VALUES
	('robking', 'ROLE_ADMIN'),
	('davolio', 'ROLE_ADMIN'),
	('fuller', 'ROLE_USER'),
	('leverling', 'ROLE_USER'),
	('stan', 'ROLE_USER'),
	('moreno', 'ROLE_USER'),
	('anders', 'ROLE_USER'),
	('hardy', 'ROLE_USER'),
	('murvai', 'ROLE_MANAGER');

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
    ('Berliner Platz 43', 'Berlin', '80805', '', 'Germany'), --10
    ('Barna Street 2','Debrecen', '4025', '','Hungary'); --11

INSERT INTO restaurant(name, email, address_id, phone) VALUES
	('King Bistro', 'info@kingbistro.com', 1, '5558121'), --1
	('Restaurant Rex', 'contact@restaurantrex.com', 2, '5553119'), --2
	('Davolio Cafe', 'contact@davoliocafe.com', 7, '5553119'); --3

INSERT INTO employee(user_id, email, first_name, last_name, title, address_id, restaurant_id) VALUES
	(1, 'robert.king@gmail.com', 'Robert', 'King', 'OWNER', 1, 1), --1
	(2, 'nancy.davolio@gmail.com', 'Nancy', 'Davolio', 'OWNER', 6, 3), --2
	(3, 'andrew.fuller@gmail.com', 'Andrew', 'Fuller', 'CHEF',5, 1), --3
	(4, 'janet.leverling@gmail.com', 'Janet', 'Leverling', 'WAITER',6, 1), --4
	(6, 'maria.anders@gmail.com', 'Maria', 'Anders', 'WAITER',8, 1),--5
	(8, 'thomas.hardy@gmail.com', 'Thomas', 'Hardy', 'BARTENDER',10, 1),--6
	(7, 'antonio.moreno@gmail.com', 'Antonio', 'Moreno', 'BARTENDER',9, 1), --7
    (5, 'stan.super@gmail.com', 'Stan', 'Super', 'WAITER',7, 3), --8
    (9, 'murvai.gergely@gmail.com', 'Murvai', 'Gergely', 'MANAGER', 11, 1);

UPDATE restaurant SET owner_id = 1 WHERE restaurant.id = 1;

UPDATE restaurant SET owner_id = 1 WHERE restaurant.id = 2;

UPDATE restaurant SET owner_id = 2 WHERE restaurant.id = 3;


INSERT INTO restaurant_table(name, active, restaurant_id, employee_id) VALUES
	('Table ONE', TRUE, 1, 3), --1
	('Table TWO', TRUE, 1, 3), --2
	('Table THREE', TRUE, 1, 5); --3

INSERT INTO restaurant_table(name, active, restaurant_id) VALUES
	('Bar', TRUE, 1), --4
	('Table CORNER', TRUE, 1), --5
	('Table VIP', TRUE, 1), --6
	('Table ONE', TRUE, 3); --7

INSERT INTO seat(active, restaurant_table_id) VALUES
    (TRUE, 1), --1
    (TRUE, 1), --2
    (TRUE, 1), --3
    (TRUE, 1), --4
    (TRUE, 2), --5
    (TRUE, 2), --6
    (TRUE, 2), --7
    (TRUE, 2), --8
    (TRUE, 3), --9
    (TRUE, 3), --10
    (TRUE, 4), --11
    (TRUE, 4), --12
    (TRUE, 5), --13
    (TRUE, 5); --14

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

INSERT INTO item(name ,price ,category ,subcategory ,restaurant_id) VALUES
    ('Pizza Margareta', 1150,'FOOD','PIZZA',1),
    ('Pizza Prosciutto',1350,'FOOD','PIZZA',1),
    ('Pizza Funghi', 1150,'FOOD','PIZZA',1),
    ('Pizza Salami', 1350,'FOOD','PIZZA',1),
    ('Pizza Hawaii', 1350,'FOOD','PIZZA',1),
    ('Tárkonyos vadmalac leves', 1290,'FOOD','SOUP',1),
    ('Jókai bableves', 1100,'FOOD','SOUP',1),
    ('Harcsapaprikás kapros túrós sztrapacskával', 2590,'FOOD','FISH_MAIN',1),
    ('Rántott halfilé, rizs, tartármártás', 1900,'FOOD','FISH_MAIN',1),
    ('Rántott csirkemell hasábburgonyával', 1750,'FOOD','CHICKEN_DISH',1),
    ('Juhtúróval-baconnel töltött csirkemell parázs burgonya', 2200,'FOOD','CHICKEN_DISH',1),
    ('Zöldséges marharagu óriás zsemlegombóccal', 1900,'FOOD','BEEF_DISH',1),
    ('Zöldséges marharagu óriás zsemlegombóccal', 1900,'FOOD','BEEF_DISH',1),
    ('Rántott szelet, hasábburgonyával', 1690,'FOOD','PORK_DISH',1),
    ('Pizza kenyérben sült kemencés csülök lyoni hagymával', 2390,'FOOD','PORK_DISH',1),
    ('Rántott gomba, rizs, tartármártás', 1690,'FOOD','VEGETARIAN',1),
    ('Rántott sajt, hasábburgonya, tartár mártás', 1690,'FOOD','VEGETARIAN',1),
    ('Coors Light Lager', 390,'DRINK', 'ALCOHOLIC',1),
    ('Chivas Regal', 790,'DRINK', 'ALCOHOLIC',1),
    ('Coca Cola', 390,'DRINK', 'BEVERAGE',1),
    ('Fanta', 390,'DRINK', 'BEVERAGE',1),
    ('Espresso', 290,'DRINK', 'COFFEE',1),
    ('Latte Machiato', 290,'DRINK', 'COFFEE',1),
    ('Earl Grey', 290,'DRINK', 'TEA',1),
    ('Rooibos', 290,'DRINK', 'TEA',1);

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
    (1, 1, 'no chili'),
    (1, 1,null),
    (25, 3,null),
    (1, 2,null),
    (24, 3,null),
    (15, 1,null),
    (13, 1,null),
    (15, 1,null),
    (18, 1,null),
    (5, 1,null),
    (13, 1,null),
    (13, 1,null),
    (21, 1,null),
    (14, 1,null),
    (22, 2,null),
    (16, 1,null),
    (23, 1,null),
    (19, 2,null),
    (8, 1,null),
    (19, 3,null),
    (1, 1,null),
    (14, 2,null),
    (25, 1,null),
    (11, 1,null),
    (15, 1,null),
    (17, 1,null),
    (20, 2,null),
    (24, 1,null),
    (13, 1,null),
    (9, 1,null),
    (3, 1,null),
    (10, 1,null),
    (2, 2,null),
    (2, 1,null),
    (6, 1,null),
    (6, 1,null),
    (15, 1,null),
    (7, 1,null),
    (24, 1,null),
    (18, 1,null),
    (14, 1,null),
    (18, 2,null),
    (20, 1,null),
    (13, 1,null),
    (23, 1,null),
    (18, 1,null),
    (7, 1,null),
    (2, 1,null),
    (12, 1,null),
    (2, 1,null),
    (16, 1,null);

INSERT INTO customer_order (seat_id, employee_id, ordering_time , order_item_id) VALUES
    (1, 3, '2018-03-05 20:22:49', 7),
    (1, 6, '2018-03-05 20:32:49', 38),
    (2, 4, '2018-03-05 20:23:49', 11),
    (2, 6, '2018-03-05 20:24:49', 3),
    (3, 3, '2018-03-05 20:25:49', 9),
    (3, 5, '2018-03-05 20:26:49', 3),
    (4, 5, '2018-03-05 20:26:49', 1),
    (4, 5, '2018-03-05 20:27:49', 20),
    (5, 5, '2018-01-05 04:29:38', 9),
    (5, 5, '2018-02-02 15:35:40', 3),
    (7, 4, '2018-03-14 04:02:55', 39),
    (12, 5, '2018-06-12 12:38:49', 16),
    (5, 5, '2018-03-25 04:56:18', 29),
    (12, 3, '2018-04-30 00:20:34', 37),
    (1, 4, '2018-03-05 20:52:49', 12),
    (8, 6, '2018-03-24 03:42:56', 12),
    (10, 3, '2018-01-05 14:37:54', 9),
    (12, 5, '2018-06-20 17:25:16', 34),
    (14, 6, '2018-02-13 02:16:24', 36),
    (12, 5, '2018-07-15 07:04:50', 47);

INSERT INTO menu (title) VALUES
    ('Monday'), --1
    ('Tuesday'), --2
    ('Wednesday'), --3
    ('Thursday'), --4
    ('Friday'), --5
    ('Saturday'), --6
    ('Sunday'); --7

INSERT INTO menu_item (menu_id, item_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 5),
    (2, 6),
    (3, 7),
    (3, 8),
    (4, 9),
    (4, 10),
    (4, 11),
    (5, 12),
    (5, 13),
    (5, 14),
    (6, 15),
    (6, 16),
    (6, 17),
    (7, 18),
    (7, 19),
    (7, 20);