\c hellodb;
CREATE TABLE test (my_int int);
INSERT INTO test VALUES (1000);
INSERT INTO test VALUES (1001);
INSERT INTO test VALUES (1002);
INSERT INTO test VALUES (1003);
INSERT INTO test VALUES (1004);
CREATE USER hello;
GRANT ALL ON test TO hello;
ALTER USER hello WITH PASSWORD 'password';
