-- 1
INSERT into Players (id, name) values (default, 'T. Fadeev');
-- 2
INSERT into Players (id, name) values (default, 'A. Saraeva');
-- 3
INSERT into Players (id, name) values (default, 'A. Kulakov');
-- 4
INSERT into Players (id, name) values (default, 'S. Zhukov');
-- 5
INSERT into Players (id, name) values (default, 'V. Levchik');
-- 6
INSERT into Players (id, name) values (default, 'F. Buzz');



INSERT into Matches (id, first_player_id, second_player_id, winner_id) values (default, 1, 2, 2 );
INSERT into Matches (id, first_player_id, second_player_id, winner_id) values (default, 3, 4, 4 );
INSERT into Matches (id, first_player_id, second_player_id, winner_id) values (default, 5, 1, 1 );
INSERT into Matches (id, first_player_id, second_player_id, winner_id) values (default, 5, 4, 5 );
INSERT into Matches (id, first_player_id, second_player_id, winner_id) values (default, 1, 6, 6 );
INSERT into Matches (id, first_player_id, second_player_id, winner_id) values (default, 6, 3, 3 );

