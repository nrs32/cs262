------ Single Table Querys ------
---------------------------------
-- Retrieve a list of all the games, ordered by date with the most recent game coming first.
SELECT *
FROM Game
ORDER BY time;

-- Retrieve all the games that occurred in the past week.
SELECT * 
FROM Game 
WHERE  time >= date_trunc('week', CURRENT_TIMESTAMP - interval '1 week');

-- Retrieve a list of players who have (non-NULL) names.
SELECT *
FROM Player
WHERE name is not NULL;

-- Retrieve a list of IDs for players who have some game score larger than 2000.
SELECT playerID
FROM PlayerGameLink 
WHERE score > 2000;

-- Retrieve a list of players who have GMail accounts.
SELECT *
FROM Player
WHERE emailAddress LIKE '%gmail%';


------ Multi Table Querys ------
---------------------------------
-- Retrieve all “The King”’s game scores in decreasing order.
SELECT PlayerGameLink.score
FROM PlayerGameLink, Player
WHERE PlayerGameLink.playerID = Player.ID
AND Player.name = 'The King'
ORDER BY score DESC;

-- Retrieve the name of the winner of the game played on 2006-06-28 13:20:00.
SELECT Player.name
FROM  Player, PlayerGameLink, Game
WHERE Player.ID = PlayerGameLink.playerID
AND Game.ID = PlayerGameLink.gameID
AND Game.time = '2006-06-28 13:20:00'
ORDER BY PlayerGameLink.score DESC -- highest score first
LIMIT 1; -- only show winner

----------- So what does that P1.ID < P2.ID clause do in the last example query? -----------
-- This makes sure the players are not the same and that the list of results does not repeat values


----------- The query that joined the Player table to itself seems rather contrived. ------------------------
----------- Can you think of a realistic situation in which you’d want to join a table to itself? -----------
-- In cases where we need to connect a value in a table to another also in the table.
-- For example, if we have a parts table and need to find all the subparts that make up a part. We can link the table to itself in order to loop through all the parts
-- and find all of the subparts by looping through the table again.