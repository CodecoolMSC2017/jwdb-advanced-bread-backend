INSERT INTO address(street,city,postal_code,state,country) VALUES
    ('Szabó utca', 'Traktorda', 'H4011','SzSzB','Hungary'),
    ('Kovács utca', 'Talicskás', 'H4011','SzSzB','Hungary');

INSERT INTO owner(first_name ,last_name ,address_id ,email ,phone ,password) VALUES
    ('Pista','Lakatos',1, 'pista@pista.com', 123456789,'asdasd'), --1
    ('Sanyi','Lakatos',2, 'psanyi@sanyi.com', 987654321,'qwe');  --2

INSERT INTO restaurant(name,address_id,email,phone,owner_id) VALUES
    ('Pista faló', 1, 'falo@pista.com', 123456789,1),
    ('Pista ivó', 1, 'ivo@pista.com', 987654321,1),
    ('PSanyi faló', 2, 'falo@sanyi.com', 1354792468,2);