-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS PropertyPlayerLink;
DROP TABLE IF EXISTS PlayerGameLink;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Property;
DROP TABLE IF EXISTS Player;

-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp,
    inProgress boolean
);

CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
);

CREATE TABLE PlayerGameLink (
    ID integer PRIMARY KEY,
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	score integer,
    houseCount integer,
    hotelCount integer,
    cash integer,
    pieceLocation integer
);

CREATE TABLE Property (
    ID integer PRIMARY KEY,
    propertyName varchar(50)
);

CREATE TABLE PropertyPlayerLink (
    ID integer PRIMARY KEY,
    playerID integer REFERENCES Player(ID),
    propertyID integer REFERENCES Property(ID)
);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGameLink TO PUBLIC;
GRANT SELECT ON Property TO PUBLIC;
GRANT SELECT ON PropertyPlayerLink TO PUBLIC;

-- Add Game ----------ID, timestamp, inProgress
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00', false);
-- INSERT INTO Game VALUES (2, '2006-06-28 13:20:00', true);
-- INSERT INTO Game VALUES (3, '2006-06-29 18:41:00', true);

-- Add Player ----------Id, email, name
INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

-- Add playerGameLink -----------iD, gameID, playerID, score, houseCount, hotelCount, cash, pieceLocation
INSERT INTO PlayerGameLink VALUES (1, 1, 1, 0.00, 2, 1, 100, 30);
INSERT INTO PlayerGameLink VALUES (2, 1, 2, 0.00, 5, 0, 450, 10);
INSERT INTO PlayerGameLink VALUES (3, 1, 3, 2350.00, 7, 1, 600, 11);

-- Add properties -----------ID, name
INSERT INTO Property VALUES (1, 'property1');
INSERT INTO Property VALUES (2, 'property2');
INSERT INTO Property VALUES (3, 'property3');
INSERT INTO Property VALUES (4, 'property4');
INSERT INTO Property VALUES (5, 'property5');
INSERT INTO Property VALUES (6, 'property6');

-- Add PropertyPlayerLinks ------- ID, playerID, propertyID
INSERT INTO PropertyPlayerLink VALUES (1, 1, 1);
INSERT INTO PropertyPlayerLink VALUES (2, 1, 2);
INSERT INTO PropertyPlayerLink VALUES (3, 1, 4);
INSERT INTO PropertyPlayerLink VALUES (4, 2, 6);